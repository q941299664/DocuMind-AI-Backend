package com.documind.common.web.handler;

import com.documind.common.core.exception.BusinessException;
import com.documind.common.core.result.Result;
import com.documind.common.core.result.ResultCode;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<?> handleBusinessException(BusinessException e) {
        if (e.getResultCode() != null) {
            return Result.failed(e.getResultCode());
        }
        return Result.failed(e.getMessage());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<?> handleValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return Result.failed(ResultCode.VALIDATE_FAILED.getCode(), message);
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<?> handleBindException(BindException e) {
        BindingResult bindingResult = e.getBindingResult();
        String message = null;
        if (bindingResult.hasErrors()) {
            FieldError fieldError = bindingResult.getFieldError();
            if (fieldError != null) {
                message = fieldError.getField() + fieldError.getDefaultMessage();
            }
        }
        return Result.failed(ResultCode.VALIDATE_FAILED.getCode(), message);
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<?> handleRuntimeException(RuntimeException e) {
        e.printStackTrace();
        return Result.failed(ResultCode.FAILED.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<?> handleException(Exception e) {
        e.printStackTrace();
        return Result.failed(ResultCode.FAILED);
    }
}
