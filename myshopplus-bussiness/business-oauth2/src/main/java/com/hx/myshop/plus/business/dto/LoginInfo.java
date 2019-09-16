package com.hx.myshop.plus.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jxlgcmh
 * @date 2019-09-15 23:12
 */
@Data
public class LoginInfo implements Serializable {
    /**
     * 前端要展示的信息，必须字段要和前端的一致
     */
    private String name;
    private String avatar;
}
