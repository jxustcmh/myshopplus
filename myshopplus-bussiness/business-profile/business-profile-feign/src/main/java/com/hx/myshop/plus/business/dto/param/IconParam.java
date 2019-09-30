package com.hx.myshop.plus.business.dto.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jxlgcmh
 * @date 2019-09-29 13:34
 */
@Data
public class IconParam implements Serializable {
    private static final long serialVersionUID = -3241658915128282243L;
    /**
     * 用户名
     */
    private  String username;
    /**
     * 头像路径
     */
    private String path;
}
