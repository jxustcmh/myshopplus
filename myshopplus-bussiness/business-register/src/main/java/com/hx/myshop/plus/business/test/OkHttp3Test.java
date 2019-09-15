package com.hx.myshop.plus.business.test;

import com.hx.myshop.plus.commons.ResponseResult;
import com.hx.myshop.plus.commons.utils.MapperUtil;
import com.hx.myshop.plus.commons.utils.OkHttpClientUtil;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jxlgcmh
 * @date 2019-09-15 13:19
 */
public class OkHttp3Test {
    public static void main(String[] args) throws Exception {
        String url = "http://localhost:9001/oauth/token";
        Map<String,String> map =new HashMap<>();
        map.put("username","admin");
        map.put("password","123456");
        map.put("grant_type","password");
        map.put("client_id","client");
        map.put("client_secret","secret");
        Response response = OkHttpClientUtil.getInstance().postData(url, map);
        Map<String, Object> map1 = MapperUtil.json2map(response.body().string());
        Object token = map1.get("access_token");
        System.out.println(token);
    }


    public static void test2() {
        String url = "http://localhost:9001/oauth/token";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("username", "admin")
                .add("password", "123456")
                .add("grant_type", "password")
                .add("client_id", "client")
                .add("client_secret", "secret")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void test1() {
        String url ="https://www.baidu.com";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        Response response = null;
        try {
            response = call.execute();
            System.out.println(response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
