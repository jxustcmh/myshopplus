package com.hx.myshop.plus.provider.api;

/**
 * @author jxlgcmh
 * @date 2019-09-14 16:22
 */
public interface EchoService {
    /**
     *  测试注册到nacos
     * @param message 信息
     * @return 返回消息
     */
    String echo(String message);
}
