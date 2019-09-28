package com.hx.myshop.plus.business.web;

import com.hx.myshop.plus.commons.ResponseResult;
import com.hx.myshop.plus.provider.api.UmsAdminService;
import com.hx.myshop.plus.provider.model.UmsAdmin;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    /**
     * 其实这样不行，应该先获取token，从token里面获取了用户的信息和username对比，如果一致，则和可以获取信息
     * @param username
     * @return
     */
    @GetMapping("/info/{username}")
    public ResponseResult<UmsAdmin> getUserInfo(@PathVariable String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        if (! username.equals(name)) {
            return new ResponseResult<>(ResponseResult.CodeStatus.OK,"非法获取",null);
        }
        UmsAdmin umsAdmin = umsAdminService.getByUserName(username);
        return new ResponseResult<>(ResponseResult.CodeStatus.OK,"获取个人信息成功",umsAdmin);
    }
}
