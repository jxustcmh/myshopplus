package com.hx.myshop.plus.business.fallback;

import com.hx.myshop.plus.business.dto.param.IconParam;
import com.hx.myshop.plus.business.dto.param.ProfileParam;
import com.hx.myshop.plus.business.feign.ProfileFeign;
import com.hx.myshop.plus.commons.ResponseResult;
import com.hx.myshop.plus.commons.utils.MapperUtil;
import org.springframework.stereotype.Component;

/**
 * @author jxlgcmh
 * @date 2019-09-29 14:46
 */
public class ProfileFeignFallback implements ProfileFeign {
    public static final String BREAKING_MESSAGE = "请求失败，请检查网络";

    @Override
    public String getUserInfo(String username) {
        try {
            return MapperUtil.obj2json(new ResponseResult<Void>(ResponseResult.CodeStatus.BREAKING, BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String update(ProfileParam profileParam) {
        try {
            return MapperUtil.obj2json(new ResponseResult<Void>(ResponseResult.CodeStatus.BREAKING, BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String modifyIcon(IconParam iconParam) {
        try {
            return MapperUtil.obj2json(new ResponseResult<Void>(ResponseResult.CodeStatus.BREAKING, BREAKING_MESSAGE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
