package com.hx.myshop.plus.business.feign;

import com.hx.myshop.plus.business.dto.param.IconParam;
import com.hx.myshop.plus.business.dto.param.ProfileParam;
import com.hx.myshop.plus.business.fallback.ProfileFeignFallback;
import com.hx.myshop.plus.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author jxlgcmh
 * @date 2019-09-16 13:21
 */
@FeignClient(value = "business-profile", path = "profile", configuration = FeignRequestConfiguration.class, fallback = ProfileFeignFallback.class)
public interface ProfileFeign {
    /**
     * 通过feign客户端获取用户信息
     *
     * @param username
     * @return
     */
    @GetMapping("/info/{username}")
    String getUserInfo(@PathVariable String username);

    /**
     * 更新用户信息
     *
     * @param profileParam 更新用户信息的 dto
     * @return Json数据
     */
    @PostMapping("update")
    String update(@RequestBody ProfileParam profileParam);

    /**
     * 修改用户头像
     *
     * @param iconParam
     * @return
     */
    @PostMapping("modify/icon")
    String modifyIcon(@RequestBody IconParam iconParam);
}
