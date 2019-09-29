package com.hx.myshop.plus.cloud.feign;

import com.hx.myshop.plus.configuration.FeignRequestConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author jxlgcmh
 * @date 2019-09-29 11:20
 */
@FeignClient(value = "cloud-upload",path = "upload",configuration = FeignRequestConfiguration.class )
public interface UploadFeign {
    /**
     *  文件上传
     * @param multipartFile 文件
     * @return
     */
    @PostMapping(value = "")
    String upload(@RequestPart(value = "multipartFile") MultipartFile multipartFile);
}
