package com.hx.myshop.plus.provider;

import com.hx.myshop.plus.provider.mapper.UmsAdminMapper;
import com.hx.myshop.plus.provider.model.UmsAdmin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author jxlgcmh
 * @date 2019-09-14 17:47
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMySqlConnection {

    @Autowired
    private UmsAdminMapper umsAdminMapper;
    @Test
    public void getAllAdmin() {
        List<UmsAdmin> umsAdmins = umsAdminMapper.selectAll();
        for (UmsAdmin umsAdmin : umsAdmins) {
            System.out.println(umsAdmin);
        }
    }
}
