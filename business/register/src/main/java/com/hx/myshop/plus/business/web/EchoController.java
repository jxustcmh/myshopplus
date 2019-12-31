package com.hx.myshop.plus.business.web;

import com.hx.myshop.plus.provider.api.EchoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jxlgcmh
 * @date 2019-09-14 19:11
 */
@RestController
@RequestMapping("/test")
public class EchoController {

    @Reference(version = "1.0.1")
    private EchoService echoService;

    @GetMapping("/hello/{s}")
    public String hello(@PathVariable String s) {
        return echoService.echo(s);
    }
}
