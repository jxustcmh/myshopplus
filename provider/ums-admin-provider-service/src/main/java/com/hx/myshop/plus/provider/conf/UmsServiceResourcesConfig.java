package com.hx.myshop.plus.provider.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author jxlgcmh
 * @date 2019-09-14 23:00
 */
@Configuration
public class UmsServiceResourcesConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
