package com.hx.myshop.plus.provider.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.hx.myshop.plus.provider.api.UmsAdminService;
import com.hx.myshop.plus.provider.fallback.UmsAdminServiceFallback;
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
    @SentinelResource(value = "getByUserName", fallback = "getByUserNameFallback", fallbackClass = UmsAdminServiceFallback.class)
    public UmsAdmin getByUserName(String userName) {
        Example example = new Example(UmsAdmin.class);
        example.createCriteria().andEqualTo("username",userName);
        return umsAdminMapper.selectOneByExample(example);
    }

    @Override
    public int update(UmsAdmin umsAdmin) {
        UmsAdmin oldUmsAdmin = getByUserName(umsAdmin.getUsername());
        // 仅更新 邮箱、昵称、备注、状态
        oldUmsAdmin.setEmail(umsAdmin.getEmail());
        oldUmsAdmin.setNickName(umsAdmin.getNickName());
        oldUmsAdmin.setNote(umsAdmin.getNote());
        oldUmsAdmin.setStatus(umsAdmin.getStatus());

        return umsAdminMapper.updateByPrimaryKey(oldUmsAdmin);
    }

    @Override
    public int modifyIcon(String username, String path) {
        UmsAdmin admin = getByUserName(username);
        admin.setIcon(path);
        return   umsAdminMapper.updateByPrimaryKey(admin);
    }

    private void initUmsAdmin(UmsAdmin umsAdmin) {
        umsAdmin.setCreateTime(new Date());
        umsAdmin.setLoginTime(new Date());
        if (umsAdmin.getStatus() == null) {
            umsAdmin.setStatus(0);
        }
    }
}
