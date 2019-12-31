package com.hx.myshop.plus.cloud.producer;

import com.hx.myshop.plus.cloud.api.MessageService;
import com.hx.myshop.plus.cloud.dto.UmsAdminLoginLogDTO;
import com.hx.myshop.plus.cloud.message.MessageSource;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author jxlgcmh
 * @date 2019-12-31 11:07
 * @description
 */
@Component
@Service(version = "1.0.1")
public class MessageProducer implements MessageService {

    @Resource
    private MessageSource source;

    /**
     * 管理登录日志
     *
     * @param dto {@link UmsAdminLoginLogDTO}
     * @return {@code boolean}
     */
    @Override
    public boolean sendAdminLoginLog(UmsAdminLoginLogDTO dto) {
        return source.adminLoginLog().send(MessageBuilder.withPayload(dto).build());
    }
}
