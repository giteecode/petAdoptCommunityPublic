package com.lian.pet.common.basic.response;

import com.lian.pet.common.basic.exception.AppErrorEnum;
import com.lian.pet.common.basic.exception.AppException;
import lombok.NoArgsConstructor;
import java.util.Map;

/**

 * @Date: 2021/11/1 14:22
 * @Description: app 响应数据体
 */
@NoArgsConstructor
public class AppResp<T> {
    private boolean success;
    private long code;
    private String message;
    private T data;
    private Map<String, Object> extra;

    public boolean isSuccess() {
        return success;
    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public static <T> AppResp<T> succeed(T data, String message) {
        AppResp<T> resp = new AppResp<T>();
        resp.success = true;
        resp.code = AppErrorEnum.NO_ERROR.getCode();
        resp.message = message;
        resp.data = data;
        return resp;
    }

    public static <T> AppResp<T> succeed(T data) {
        AppResp<T> resp = new AppResp<>();
        resp.success = true;
        resp.code = AppErrorEnum.NO_ERROR.getCode();
        resp.data = data;
        return resp;
    }

    public static <T> AppResp<T> succeed(T data, Map<String, Object> extra) {
        AppResp<T> resp = new AppResp<>();
        resp.success = true;
        resp.code = AppErrorEnum.NO_ERROR.getCode();
        resp.data = data;
        resp.extra = extra;
        return resp;
    }

    public static <T> AppResp<T> failed(long code, String message) {
        AppResp<T> resp = new AppResp<>();
        resp.success = false;
        resp.code = code;
        resp.message = message;
        resp.data = null;
        return resp;
    }

    public static <T> AppResp<T> failed(AppException e) {
        return failed(e.getCode(), e.getMessage());
    }
}
