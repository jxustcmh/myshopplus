package com.hx.myshop.plus.provider.service;

import javax.annotation.Resource;
import com.hx.myshop.plus.provider.mapper.UmsAdminLoginLogMapper;
import com.hx.myshop.plus.provider.api.UmsAdminLoginLogService;
import com.hx.myshop.plus.provider.model.UmsAdminLoginLog;
import org.apache.dubbo.config.annotation.Service;

/**
@author jxlgcmh
@date 2019-12-30 19:35
@description
*/
@Service(version = "1.0.1")
public class UmsAdminLoginLogServiceImpl implements UmsAdminLoginLogService{

    @Resource
    private UmsAdminLoginLogMapper umsAdminLoginLogMapper;

    @Override
    public int insert(UmsAdminLoginLog umsAdminLoginLog) {
        return umsAdminLoginLogMapper.insert(umsAdminLoginLog);
    }
}
