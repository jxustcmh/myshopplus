package com.hx.myshop.plus.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author jxlgcmh
 * @date 2019-09-29 11:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo implements Serializable {
    private static final long serialVersionUID = 2233631029688245963L;
    /**
     * 文件上传路径
     */
    private String path;
}
