package com.hx.myshop.plus.provider;

import com.hx.myshop.plus.provider.api.UmsAdminService;
import com.hx.myshop.plus.provider.mapper.UmsAdminMapper;
import com.hx.myshop.plus.provider.model.UmsAdmin;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author jxlgcmh
 * @date 2019-09-14 17:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUms {

    @Autowired
    private UmsAdminMapper umsAdminMapper;
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Test
    public void getAllAdmin() {
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectAll();
        umsAdmins.forEach(System.out::println);
    }

    @Test
    public void insertUmsAdmin() {
        UmsAdmin umsAdmin =new UmsAdmin();
        umsAdmin.setUsername("jxustcmh");
        umsAdmin.setPassword(passwordEncoder.encode("123456"));
        int result = umsAdminService.insert(umsAdmin);
        // 断言
        Assert.assertEquals(result,1);


    }
}
