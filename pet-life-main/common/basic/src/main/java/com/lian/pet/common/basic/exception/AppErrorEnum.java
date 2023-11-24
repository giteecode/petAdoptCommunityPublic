package com.lian.pet.common.basic.exception;

import lombok.AllArgsConstructor;

/**

 * @Date: 2021/11/1 12:43
 * @Description: 默认错误类型枚举
 */
@AllArgsConstructor
public enum AppErrorEnum implements ErrorEnumDefine {
    NO_ERROR(0, "msg.api.no.err", "no error"),
    UNKNOWN_ERROR(-1, "msg.api.err.common", "unknown error"),

    // http request

    BAD_REQUEST(400, "msg.api.err.bad_request", "bad request"),

    UNAUTHORIZED(401, "msg.api.err.unauthorized", "unauthorized"),

    NOT_FOUND(404, "msg.api.err.not_found", "not found"),

    METHOD_NOT_ALLOWED(415, "msg.api.err.method_not_allowed", "not allowed"),

    INTERNAL_SERVER_ERROR(500, "msg.api.err.internal_server_error", "internal server error"),

    // spring secure

    ACCESS_DENIED(1001, "msg.api.err.access_denied", "access denied"),

    BAD_CREDENTIALS(1002, "msg.api.err.bad_credentials", "bad credentials"),

    FORBIDDEN_ACCOUNT(1010, "msg.api.err.forbidden_account", "forbidden account"),

    // domain error

    USER_ALREADY_EXIST(10001, "msg.api.err.user_already_exist", "user already exist"),

    ADD_USER_FAILED(10002, "msg.api.err.add_user_failed", "add user failed"),

    GET_ROLE_FAILED(10003, "msg.api.err.get_role_failed", "get role failed"),

    ADD_USER_ROLE_FAILED(10004, "msg.api.err.add_user_role_failed", "add user role failed"),

    DUPLICATE_SUBMIT(10010, "", "duplicate submit")
    ;

    private final int code;
    private final String localeCode;
    private final String defMsg;

    @Override
    public long getCode() {
        return this.code;
    }

    @Override
    public String getLocaleCode() {
        return localeCode;
    }

    @Override
    public String getDefMsg() {
        return defMsg;
    }
}
