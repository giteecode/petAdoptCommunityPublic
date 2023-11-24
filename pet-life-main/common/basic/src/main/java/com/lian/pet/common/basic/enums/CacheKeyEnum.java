package com.lian.pet.common.basic.enums;

import lombok.Getter;

/**
 * @Desc: Cache Key

 * @Time: 2022/1/27 12:02
 */
public enum CacheKeyEnum {

    /** 用户信息（暂不使用） */
    CACHE_USER_INFO("USER:INFO", "用户信息");

    @Getter
    private final String code;
    private final String message;

    CacheKeyEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

}
