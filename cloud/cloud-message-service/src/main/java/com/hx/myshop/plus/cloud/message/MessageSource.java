package com.hx.myshop.plus.cloud.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author jxlgcmh
 * @date 2019-12-31 11:06
 * @description
 */
public interface MessageSource {
    @Output("admin-login-log-topic")
    MessageChannel adminLoginLog();
}
