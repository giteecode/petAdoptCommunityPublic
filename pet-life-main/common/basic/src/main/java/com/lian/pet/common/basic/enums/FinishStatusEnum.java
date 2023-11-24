package com.lian.pet.common.basic.enums;

import lombok.Getter;

/**
 * @Desc: 完成状态枚举

 * @Time: 2022/1/27 12:02
 */
public enum FinishStatusEnum {

    /**
     * 0-已完成
     * 1-进行中
     */
    FINISHED("0", "已完成"),
    FINISHING("1", "进行中");

    @Getter
    private final String code;
    private final String message;

    FinishStatusEnum(String code, String message) {
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
