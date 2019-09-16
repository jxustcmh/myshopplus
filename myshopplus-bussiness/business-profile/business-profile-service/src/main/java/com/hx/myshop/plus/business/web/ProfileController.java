package com.hx.myshop.plus.business.web;

import com.hx.myshop.plus.commons.ResponseResult;
import com.hx.myshop.plus.provider.api.UmsAdminService;
import com.hx.myshop.plus.provider.model.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jxlgcmh
 * @date 2019-09-16 13:29
 */
@RestController
@RequestMapping("profile")
public class ProfileController {
    @Reference(version = "1.0.1")
    private UmsAdminService umsAdminService;

    @GetMapping("/info/{username}")
    public ResponseResult<UmsAdmin> getUserInfo(@PathVariable String username) {
        UmsAdmin umsAdmin = umsAdminService.getByUserName(username);
        return new ResponseResult<>(ResponseResult.CodeStatus.OK,"获取个人信息成功",umsAdmin);
    }
}
