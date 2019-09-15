package com.hx.myshop.plus.provider.service;

import com.hx.myshop.plus.provider.api.UmsAdminService;
import com.hx.myshop.plus.provider.mapper.UmsAdminMapper;
import com.hx.myshop.plus.provider.model.UmsAdmin;
import org.apache.dubbo.config.annotation.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author jxlgcmh
 * @date 2019-09-14 20:58
 */
@Service(version = "1.0.1")
public class UmsAdminServiceImpl implements UmsAdminService {

    @Resource
    private UmsAdminMapper umsAdminMapper;

    @Override
    public int insert(UmsAdmin umsAdmin) {
        initUmsAdmin(umsAdmin);
        return umsAdminMapper.insert(umsAdmin);
    }

    @Override
    public UmsAdmin getByUserName(String userName) {
        Example example = new Example(UmsAdmin.class);
        example.createCriteria().andEqualTo("username",userName);
        return umsAdminMapper.selectOneByExample(example);
    }

    private void initUmsAdmin(UmsAdmin umsAdmin) {
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setLoginTime(new Date());
        if (umsAdmin.getStatus() == null) {
            umsAdmin.setStatus(0);
        }
    }
}
