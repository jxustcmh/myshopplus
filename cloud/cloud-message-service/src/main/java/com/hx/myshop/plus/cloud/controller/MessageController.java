package com.hx.myshop.plus.cloud.controller;

import com.hx.myshop.plus.cloud.dto.UmsAdminLoginLogDTO;
import com.hx.myshop.plus.cloud.producer.MessageProducer;
import com.hx.myshop.plus.commons.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author jxlgcmh
 * @date 2019-12-31 11:08
 * @description
 */
@RestController
@RequestMapping(value = "message")
public class MessageController {

    @Resource
    private MessageProducer messageProducer;


    @PostMapping(value = "admin/login/log")
    public ResponseResult<Void> sendAdminLoginLog(@RequestBody UmsAdminLoginLogDTO dto) {
        boolean flag = messageProducer.sendAdminLoginLog(dto);
        // 发送成功
        if (flag) {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.OK, "管理员登录日志发送成功");
        }
        // 发送失败
        else {
            return new ResponseResult<Void>(ResponseResult.CodeStatus.FAIL, "管理员登录日志发送失败");
        }
    }
}
