package com.hx.myshop.plus.cloud;

import com.hx.myshop.plus.cloud.message.MessageSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author jxlgcmh
 * @date 2019-12-31 11:02
 * @description  消息生产者服务
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableBinding({MessageSource.class})
public class CloudMessageApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudMessageApplication.class, args);
    }
}
