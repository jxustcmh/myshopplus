package com.hx.myshop.plus.configuration;

import com.hx.myshop.plus.interceptor.FeignRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jxlgcmh
 * @date 2019-09-16 13:14
 */
@Configuration
public class FeignRequestConfiguration {
    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestInterceptor();
    }
}
