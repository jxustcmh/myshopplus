package com.hx.myshop.plus.business.web;

import com.hx.myshop.plus.business.dto.LoginInfo;
import com.hx.myshop.plus.business.dto.LoginParam;
import com.hx.myshop.plus.business.feign.ProfileFeign;
import com.hx.myshop.plus.commons.ResponseResult;
import com.hx.myshop.plus.commons.utils.MapperUtil;
import com.hx.myshop.plus.commons.utils.OkHttpClientUtil;
import com.hx.myshop.plus.provider.model.UmsAdmin;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jxlgcmh
 * @date 2019-09-15 14:54
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", maxAge = 3600)
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
    @Autowired
    private ProfileFeign profileFeign;

    @PostMapping("/login")
    public ResponseResult<Map<String,Object>> login(@RequestBody LoginParam loginParam) throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParam.getUsername());
        if (userDetails == null || !passwordEncoder.matches(loginParam.getPassword(),userDetails.getPassword())) {
            return new ResponseResult<>(ResponseResult.CodeStatus.FAILURE,"用户名或密码错误", null);
        }
        Map<String,String> map =new HashMap<>(5);
        map.put("username",loginParam.getUsername());
        map.put("password",loginParam.getPassword());
        map.put("grant_type",grantType);
        map.put("client_id",clientId);
        map.put("client_secret",clientSecret);
        Response response = OkHttpClientUtil.getInstance().postData(LOGIN_URL, map);
        //断言其不为空
        assert response.body() != null;
        Map<String, Object> tempMap = MapperUtil.json2map(response.body().string());
        String token = (String) tempMap.get("access_token");
        Map<String, Object> result= new HashMap<>(1);
        result.put("token",token);
        return new ResponseResult<>(ResponseResult.CodeStatus.OK, "请求成功", result);
    }

    @GetMapping("/info")
    public ResponseResult<LoginInfo> getUserInfo() throws Exception {
       // 获取头像和用户名
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 通过feign客户端获取用户信息，再进行json转换
        String userInfoJson = profileFeign.getUserInfo(authentication.getName());
        UmsAdmin umsAdmin = MapperUtil.json2pojoByTree(userInfoJson, "data", UmsAdmin.class);
        LoginInfo loginInfo =new LoginInfo();
        loginInfo.setName(umsAdmin.getNickName());
        loginInfo.setAvatar(umsAdmin.getIcon());
        return new ResponseResult<>(ResponseResult.CodeStatus.OK,"获取用户信息成功",loginInfo);
    }

    @PostMapping("/logout")
    public ResponseResult<Void> logout(HttpServletRequest request) {
        String access_token = request.getParameter("access_token");
        OAuth2AccessToken auth2AccessToken = tokenStore.readAccessToken(access_token);
        tokenStore.removeAccessToken(auth2AccessToken);
        return new ResponseResult<>(ResponseResult.CodeStatus.OK,"用户注销登录",null);
    }


}
