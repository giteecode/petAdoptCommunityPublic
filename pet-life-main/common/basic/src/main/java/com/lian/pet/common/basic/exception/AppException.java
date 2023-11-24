package com.lian.pet.common.basic.exception;

/**

 * @Date: 2021/11/1 12:32
 * @Description: 自定义异常
 */

public class AppException extends RuntimeException {
    private long code;
    private String message;

    public AppException(String message) {
        super(message);
        this.message = message;
        this.code = AppErrorEnum.UNKNOWN_ERROR.getCode();
    }

    public AppException(long code, String message) {
        super(message);
        this.code = code;
    }

    public long getCode() {
        return code;
    }

    public String getMessage(){
        return message;
    }
}
