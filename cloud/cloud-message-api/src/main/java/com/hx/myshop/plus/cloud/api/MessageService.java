package com.hx.myshop.plus.cloud.api;

import com.hx.myshop.plus.cloud.dto.UmsAdminLoginLogDTO;

/**
 * @author jxlgcmh
 * @date 2019-12-31 10:56
 * @description
 */
public interface MessageService {
    /**
     * 发送登录日志
     * @param umsAdminLoginLogDTO
     * @return
     */
    boolean sendAdminLoginLog(UmsAdminLoginLogDTO umsAdminLoginLogDTO);
}
