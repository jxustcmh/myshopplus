package com.hx.myshop.plus.cloud.web;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.hx.myshop.plus.cloud.dto.FileInfo;
import com.hx.myshop.plus.commons.ResponseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author jxlgcmh
 * @date 2019-09-29 11:32
 */
@RestController
@RequestMapping(value = "upload")
public class UploadController {

    private static final String ENDPOINT = "oss-cn-beijing.aliyuncs.com";
    private static final String WEBSITE = "jxustcmh.cn";
    private static final String ACCESS_KEY_ID = "LTAI4Fr97JfSMEv7bzfdAi36";
    private static final String ACCESS_KEY_SECRET = "Ph3Y4G9zw5XscW1mdER6ZkhdHqXiXp";
    private static final String BUCKET_NAME = "myshopplus";
    private static final String DIR = "upload/";

    /**
     * 文件上传
     *
     * @param multipartFile @{code MultipartFile}
     * @return {@link ResponseResult<FileInfo>} 文件上传路径
     */
    @PostMapping(value = "")
    public ResponseResult<FileInfo> upload(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        String newName = UUID.randomUUID() + "." + suffix;

        OSS client = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

        try {
            client.putObject(new PutObjectRequest(BUCKET_NAME, DIR+newName, new ByteArrayInputStream(multipartFile.getBytes())));
            // 上传文件路径 = http://BUCKET_NAME.ENDPOINT/自定义路径/fileName
            return new ResponseResult<FileInfo>(ResponseResult.CodeStatus.FAIL, "文件上传成功", new FileInfo("http://" +WEBSITE +"/"+ DIR + newName));
        } catch (IOException e) {
            return new ResponseResult<>(ResponseResult.CodeStatus.FAIL, "文件上传失败，请重试");
        } finally {
            client.shutdown();
        }
    }
}
