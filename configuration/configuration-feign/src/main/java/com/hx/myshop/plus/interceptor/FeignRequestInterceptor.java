package com.hx.myshop.plus.interceptor;

import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Enumeration;

/**
 * @author jxlgcmh
 * @date 2019-09-16 12:54
 */
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        //设置请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String value = request.getHeader(name);
                requestTemplate.header(name,value);
            }
        }
        // 设置请求体，为了传递 access_token
        Enumeration<String> parameterNames = request.getParameterNames();
        StringBuilder sb = new StringBuilder();
        if (parameterNames != null) {
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                String value = request.getParameter(name);
                // 将Token 加入请求头
                if ("access_token".equals(name)) {
                    requestTemplate.header("authorization", "Bearer " + value);
                }
                // 其他参数
                else {
                    sb.append(name).append("=").append(value).append("&");
                }
            }
        }
        // 设置请求体
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
            requestTemplate.body(Request.Body.bodyTemplate(sb.toString(), Charset.defaultCharset()));
        }
    }
}
