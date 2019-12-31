package com.hx.myshop.plus.business.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author jxlgcmh
 * @date 2019-09-15 15:13
 */
@Data
public class LoginParam implements Serializable {
    private String username;
    private String password;
}
