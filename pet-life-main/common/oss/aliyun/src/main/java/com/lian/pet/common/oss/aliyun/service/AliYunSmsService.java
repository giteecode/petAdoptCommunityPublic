package com.lian.pet.common.oss.aliyun.service;

/**
 * @Desc: 阿里云发送短信

 * @Time: 2022/3/2 17:24
 */
public interface AliYunSmsService {
    /**
     * 发送短信
     * @param phone
     * @return
     * @throws Exception
     */
    Boolean sendSms(String phone) throws Exception;
}
