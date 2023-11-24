package com.lian.pet.common.oss.aliyun.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Desc: AliYun Oss Service

 * @Time: 2022/1/26 10:37
 */
public interface AliYunOssService {

    /**
     * 创建 bucket
     * @param bucketName
     * @return
     */
    String createBucketName(String bucketName);

    /**
     * 删除 bucket
     * @param bucketName
     */
    void deleteBucket(String bucketName);

    /**
     * 创建目录
     * @param bucketName
     * @param folder
     * @return
     */
    String createFolder(String bucketName, String folder);

    /**
     * 根据 key 删除文件
     * @param bucketName
     * @param folder
     * @param key
     */
    void deleteFile(String bucketName, String folder, String key);

    /**
     * 上传图片到 oss
     * @param file
     * @return
     */
    String uploadObjectOSS(MultipartFile file);
}
