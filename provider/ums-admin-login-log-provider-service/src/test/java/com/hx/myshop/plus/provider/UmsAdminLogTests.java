package com.hx.myshop.plus.provider;
import java.util.Date;

import com.hx.myshop.plus.provider.api.UmsAdminLoginLogService;
import com.hx.myshop.plus.provider.model.UmsAdminLoginLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author jxlgcmh
 * @date 2019-12-30 19:46
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class UmsAdminLogTests {

    @Resource
    private UmsAdminLoginLogService umsAdminLoginLogService;

    @Test
    public void testInsert() {
        UmsAdminLoginLog umsAdminLoginLog = new UmsAdminLoginLog();

        umsAdminLoginLog.setAdminId(1L);
        umsAdminLoginLog.setCreateTime(new Date());
        umsAdminLoginLog.setIp("0.0.0.0");
        umsAdminLoginLog.setAddress("0.0.0.0");
        umsAdminLoginLog.setUserAgent("0.0.0.0");
        umsAdminLoginLogService.insert(umsAdminLoginLog);
    }

}
