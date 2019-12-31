package com.hx.myshop.plus.message.consumer;

import com.hx.myshop.plus.commons.utils.MapperUtil;
import com.hx.myshop.plus.provider.api.UmsAdminLoginLogService;
import com.hx.myshop.plus.provider.model.UmsAdminLoginLog;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * @author jxlgcmh
 * @date 2019-12-31 11:55
 * @description
 */
@Service
public class AdminLoginLogReceive {

    @Reference(version = "1.0.1")
    private UmsAdminLoginLogService umsAdminLoginLogService;

    @StreamListener("admin-login-log-topic")
    public void receiveAdminLoginLog(String umsAdminLoginLogJson) throws Exception {
        UmsAdminLoginLog umsAdminLoginLog = MapperUtil.json2pojo(umsAdminLoginLogJson, UmsAdminLoginLog.class);
        umsAdminLoginLogService.insert(umsAdminLoginLog);
    }

}
