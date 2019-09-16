package com.hx.myshop.plus.business.feign;

import com.hx.myshop.plus.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author jxlgcmh
 * @date 2019-09-16 13:21
 */
@FeignClient(value = "business-profile", path = "profile", configuration = FeignRequestConfiguration.class)
public interface ProfileFeign {
    /**
     *  通过feign客户端获取用户信息
     * @param username
     * @return
     */
    @GetMapping("/info/{username}")
    String getUserInfo(@PathVariable  String username);
}
