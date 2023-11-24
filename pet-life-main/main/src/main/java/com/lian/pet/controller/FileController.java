package com.lian.pet.controller;

import com.lian.pet.common.basic.response.AppResp;
import com.lian.pet.common.oss.aliyun.service.AliYunOssService;
import com.lian.pet.common.oss.aliyun.config.properties.AliYunOssProperties;
import com.lian.pet.config.Config;
import com.lian.pet.util.FileUploadUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Desc: File Controller

 * @Time: 2022/1/26 14:38
 */
@Slf4j
@RequestMapping("file")
@RestController
@AllArgsConstructor
public class FileController {
    private final AliYunOssService OssService;
    private final AliYunOssProperties ossProperties;

    /**
     * 上传图片
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadPic")
    public AppResp<String> uploadPic(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
        MultipartFile file = req.getFile("file");
        String filePath = FileUploadUtils.upload(Config.getAvatarPath(), file);
        //filePath = "http://localhost:9998"+filePath;
        //return AppResp.succeed(OssService.uploadObjectOSS(file));
        return AppResp.succeed(filePath);
    }

    /**
     * 删除图片 by key
     * @param picture
     * @return
     */
    @GetMapping("/deletePic")
    public AppResp<Boolean> deletePic(@RequestParam("picture") String picture) {
        String replace = picture.replace(ossProperties.getUrlPrefix(), "");
        OssService.deleteFile(ossProperties.getBucketName(), replace.substring(0, 18), replace.substring(18));
        return AppResp.succeed(true);
    }

}
