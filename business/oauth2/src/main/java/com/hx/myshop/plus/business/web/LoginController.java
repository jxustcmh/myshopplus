package com.hx.myshop.plus.business.web;

import java.util.Date;

import com.hx.myshop.plus.business.dto.LoginInfo;
import com.hx.myshop.plus.business.dto.LoginParam;
import com.hx.myshop.plus.business.feign.ProfileFeign;
import com.hx.myshop.plus.cloud.api.MessageService;
import com.hx.myshop.plus.cloud.dto.UmsAdminLoginLogDTO;
import com.hx.myshop.plus.commons.ResponseResult;
import com.hx.myshop.plus.commons.utils.MapperUtil;
import com.hx.myshop.plus.commons.utils.OkHttpClientUtil;
import com.hx.myshop.plus.commons.utils.UserAgentUtil;
import com.hx.myshop.plus.provider.api.UmsAdminService;
import com.hx.myshop.plus.provider.model.UmsAdmin;
import eu.bitwalker.useragentutils.Browser;
import okhttp3.Response;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jxlgcmh
 * @date 2019-09-15 14:54
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    public static final String LOGIN_URL = "http://localhost:9001/oauth/token";

    @Value("${business.oauth2.granttype}")
    public String grantType;
    @Value("${business.oauth2.clientid}")
    public String clientId;
    @Value("${business.oauth2.clientsecret}")
    public String clientSecret;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private TokenStore tokenStore;

    @Resource
    private ProfileFeign profileFeign;

    @Reference(version = "1.0.1")
    private UmsAdminService umsAdminService;

    @Reference(version = "1.0.1")
    private MessageService messageService;


    @PostMapping("/login")
    public ResponseResult<Map<String, Object>> login(@RequestBody LoginParam loginParam, HttpServletRequest request) throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getUsername());
        // 特别注意密码的匹配方式
        if (userDetails == null || !passwordEncoder.matches(loginParam.getPassword(), userDetails.getPassword())) {
            return new ResponseResult<>(ResponseResult.CodeStatus.FAILURE, "用户名或密码错误", null);
        }
        Map<String, String> map = new HashMap<>(5);
        map.put("username", loginParam.getUsername());
        map.put("password", loginParam.getPassword());
        map.put("grant_type", grantType);
        map.put("client_id", clientId);
        map.put("client_secret", clientSecret);
        Response response = OkHttpClientUtil.getInstance().postData(LOGIN_URL, map);
        //断言其不为空
        assert response.body() != null;
        Map<String, Object> tempMap = MapperUtil.json2map(response.body().string());
        String token = (String) tempMap.get("access_token");
        Map<String, Object> result = new HashMap<>(1);
        result.put("token", token);
        // 发送消息
        sendAdminLogMessage(userDetails.getUsername(), request);
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "请求成功", result);
    }

    @GetMapping("/info")
    public ResponseResult<LoginInfo> getUserInfo() throws Exception {
        // 获取头像和用户名
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 通过feign客户端获取用户信息，再进行json转换
        String userInfoJson = profileFeign.getUserInfo(authentication.getName());
        UmsAdmin umsAdmin = MapperUtil.json2pojoByTree(userInfoJson, "data", UmsAdmin.class);

        // 按照熔断器给到的结果，此时 umsAdmin 为空，我们需要直接将熔断结果返回给客户端
        if (umsAdmin == null) {
            return MapperUtil.json2pojo(userInfoJson, ResponseResult.class);
        }

        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setName(umsAdmin.getUsername());
        loginInfo.setAvatar(umsAdmin.getIcon());

        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "获取用户信息成功", loginInfo);
    }

    @PostMapping("/logout")
    public ResponseResult<Void> logout(HttpServletRequest request) {
        String access_token = request.getParameter("access_token");
        OAuth2AccessToken auth2AccessToken = tokenStore.readAccessToken(access_token);
        tokenStore.removeAccessToken(auth2AccessToken);
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "用户注销登录", null);
    }

    /**
     * 发送登录日志的方法
     *
     * @param userName
     * @param request
     */
    public void sendAdminLogMessage(String userName, HttpServletRequest request) {
        UmsAdmin umsAdmin = umsAdminService.getByUserName(userName);
        if (umsAdmin != null) {
            Browser browser = UserAgentUtil.getBrowser(request);
            String ipAddr = UserAgentUtil.getIpAddr(request);
            String city = UserAgentUtil.getIpInfo(ipAddr).getCity();
            UmsAdminLoginLogDTO dto = new UmsAdminLoginLogDTO();
            dto.setAdminId(umsAdmin.getId());
            dto.setCreateTime(new Date());
            dto.setIp(ipAddr);
            dto.setAddress(city);
            dto.setUserAgent(browser.getName());
            messageService.sendAdminLoginLog(dto);
        }
    }
}
