package com.hx.myshop.plus.cloud.feign.fallback;

import com.hx.myshop.plus.cloud.feign.MessageFeign;
import org.springframework.stereotype.Component;

/**
 * @author jxlgcmh
 * @date 2019-12-31 11:00
 * @description
 */
@Component
public class MessageFeignFallback implements MessageFeign {

    private static final String BREAKING_MESSAGE = "请求失败了，请检查您的网络";

}
