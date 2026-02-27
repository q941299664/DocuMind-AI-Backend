package com.documind.common.core.exception;

import com.documind.common.core.result.IResultCode;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * 业务异常
 */
@Log4j2
@Getter
public class BusinessException extends RuntimeException {
    private IResultCode resultCode;

    public BusinessException(IResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(IResultCode resultCode, String message) {
        super(message);
        this.resultCode = resultCode;
    }

}
