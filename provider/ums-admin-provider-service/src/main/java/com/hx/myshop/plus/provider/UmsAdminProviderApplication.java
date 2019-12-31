package com.hx.myshop.plus.provider;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.hx.myshop.plus.configuration.DubboSentinelConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author jxlgcmh
 * @date 2019-09-14 16:51
 */
@SpringBootApplication(scanBasePackageClasses = {UmsAdminProviderApplication.class, DubboSentinelConfiguration.class})
@MapperScan(basePackages = "com.hx.myshop.plus.provider.mapper")
public class UmsAdminProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(UmsAdminProviderApplication.class,args);
    }
}
