package com.hx.myshop.plus.message.sink;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author jxlgcmh
 * @date 2019-12-31 11:54
 * @description
 */
public interface AdminLoginLogSink {

    @Input("admin-login-log-topic")
    SubscribableChannel adminLoginLog();
}
