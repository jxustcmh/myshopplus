package com.hx.myshop.plus.provider.service;

import com.hx.myshop.plus.provider.api.UmsAdminService;
import com.hx.myshop.plus.provider.mapper.UmsAdminMapper;
import com.hx.myshop.plus.provider.model.UmsAdmin;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author jxlgcmh
 * @date 2019-09-14 20:58
 */
@Repository
public class UmsAdminServiceImpl implements UmsAdminService {

    @Resource
    private UmsAdminMapper umsAdminMapper;

    @Override
    public int insert(UmsAdmin umsAdmin) {
        return umsAdminMapper.insert(umsAdmin);
    }
}
