package com.hx.myshop.plus.business.web;

import com.hx.myshop.plus.commons.ResponseResult;
import com.hx.myshop.plus.provider.api.UmsAdminService;
import com.hx.myshop.plus.provider.model.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author jxlgcmh
 * @date 2019-09-14 23:21
 * CrossOrigin 解决跨域问题
 */
@RestController
@RequestMapping("/reg")
public class RegisterController {

    @Reference(version = "1.0.1")
    private UmsAdminService umsAdminService;


    @PostMapping("")
    public ResponseResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdmin) {
        String message = validateUmsAdmin(umsAdmin);
        if (message == null) {
            int result = umsAdminService.insert(umsAdmin);
            if (result > 0) {
                UmsAdmin admin = umsAdminService.getByUserName(umsAdmin.getUsername());
                return new ResponseResult<>(HttpStatus.OK.value(), "注册成功", admin);
            }
        }
        return new ResponseResult<>(HttpStatus.NOT_ACCEPTABLE.value(), message != null ? message : "用户注册失败！");
    }

    /**
     * 检查用户名是否存在
     *
     * @param umsAdmin
     * @return
     */
    public String validateUmsAdmin(UmsAdmin umsAdmin) {
        UmsAdmin umsAdmin1 = umsAdminService.getByUserName(umsAdmin.getUsername());
        if (umsAdmin1 != null) {
            return "用户名已存在";
        }
        return null;
    }
}
