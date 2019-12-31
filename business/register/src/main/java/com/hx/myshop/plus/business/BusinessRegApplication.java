package com.hx.myshop.plus.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author jxlgcmh
 * @date 2019-09-14 19:03
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BusinessRegApplication {
    public static void main(String[] args) {
        SpringApplication.run(BusinessRegApplication.class,args);
    }
}
