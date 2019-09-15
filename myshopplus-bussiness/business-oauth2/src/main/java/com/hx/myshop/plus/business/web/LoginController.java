package com.hx.myshop.plus.business.web;

import com.hx.myshop.plus.business.dto.LoginParam;
import com.hx.myshop.plus.commons.ResponseResult;
import com.hx.myshop.plus.commons.utils.MapperUtil;
import com.hx.myshop.plus.commons.utils.OkHttpClientUtil;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseResult<>(20000, "请求成功", result);
    }

    @GetMapping("/info/{token}")
    public ResponseResult<Map<String, Object>> getUserInfo(@PathVariable String token) {
       // 获取头像和用户名
        return null;
    }


}
