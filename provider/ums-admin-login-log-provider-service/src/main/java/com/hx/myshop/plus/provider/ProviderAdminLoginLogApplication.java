package com.hx.myshop.plus.provider;

import com.hx.myshop.plus.configuration.DubboSentinelConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author jxlgcmh
 * @date 2019-12-30 17:04
 * @description 日志服务的提供者
 */
@SpringBootApplication(scanBasePackageClasses = {ProviderAdminLoginLogApplication.class, DubboSentinelConfiguration.class})
@MapperScan(basePackages = "com.hx.myshop.plus.provider.mapper")
public class ProviderAdminLoginLogApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderAdminLoginLogApplication.class, args);
    }
}
