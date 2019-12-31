package com.hx.myshop.plus.cloud.feign;

import com.hx.myshop.plus.cloud.feign.fallback.MessageFeignFallback;
import com.hx.myshop.plus.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author jxlgcmh
 * @date 2019-12-31 10:59
 * @description
 */
@FeignClient(value = "cloud-message", path = "message", configuration = FeignRequestConfiguration.class, fallback = MessageFeignFallback.class)
public interface MessageFeign {
}
