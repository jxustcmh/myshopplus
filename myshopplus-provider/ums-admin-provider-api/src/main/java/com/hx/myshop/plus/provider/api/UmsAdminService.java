package com.hx.myshop.plus.provider.api;

import com.hx.myshop.plus.provider.model.UmsAdmin;

/**
 * @author jxlgcmh
 * @date 2019-09-14 20:00
 */
public interface UmsAdminService {
    /**
     * 添加umsAdmin
     * @param umsAdmin 实体
     * @return 结果
     */
    int insert(UmsAdmin umsAdmin);

    /**
     *  通过用户名查询用户
     * @param userName 用户名
     * @return 用户对象
     */
    UmsAdmin getByUserName(String userName);

    /**
     *  更新用户信息
     * @param umsAdmin 用户对象
     * @return 更新结果
     */
    int update(UmsAdmin umsAdmin);
}
