package com.hx.myshop.plus.provider.service;

import com.hx.myshop.plus.provider.api.EchoService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author jxlgcmh
 * @date 2019-09-14 17:01
 */
@Service(version = "1.0.1")
public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String message) {
        return "Hello"+message;
    }

}
