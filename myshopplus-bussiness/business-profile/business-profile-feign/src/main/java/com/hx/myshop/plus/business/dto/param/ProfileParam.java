package com.hx.myshop.plus.business.dto.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jxlgcmh
 * @date 2019-09-29 09:29
 */
@Data
public class ProfileParam implements Serializable {

    private Long id;

    private String username;

    /**
     * 头像
     */
    private String icon;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 备注信息
     */
    private String note;

    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    private Integer status;

    private static final long serialVersionUID = 6184319182136612013L;

}
