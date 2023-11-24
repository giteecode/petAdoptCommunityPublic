package com.lian.pet.common.basic.enums;

import lombok.Getter;

/**
 * @Desc: 用户状态

 * @Time: 2022/1/13 16:38
 */
public enum UserStatusEnum {

    /** 正常 */
    NORMAL("0", "正常"),

    /** 禁用 */
    DISABLED("1", "禁用"),

    /** 黑名单 */
    BLACK_LIST("2", "黑名单");

    @Getter
    private final String code;
    private final String message;

    UserStatusEnum(String code, String message) {
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
