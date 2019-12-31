package com.hx.myshop.plus.business.conf;

import com.hx.myshop.plus.business.fallback.ProfileFeignFallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jxlgcmh
 * @date 2019-09-30 10:03
 */
@Configuration
public class FallBackConf {
    @Bean
    public ProfileFeignFallback profileFeignFallback() {
        return new ProfileFeignFallback();
    }
}
