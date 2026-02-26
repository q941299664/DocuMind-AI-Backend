package com.documind.common.core.result;

/**
 * 系统级错误码
 * 200: 成功
 * 500: 系统内部错误
 * 400: 参数错误
 * 401: 未认证
 * 403: 无权限
 */
public enum ResultCode implements IResultCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "系统内部错误"),
    VALIDATE_FAILED(400, "参数校验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    
    // 业务错误码 (1000~9999)
    USER_NOT_EXIST(1001, "用户不存在"),
    USER_PASSWORD_ERROR(1002, "用户名或密码错误"),
    USER_ACCOUNT_LOCKED(1003, "账号已被锁定");

    private final Integer code;
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
