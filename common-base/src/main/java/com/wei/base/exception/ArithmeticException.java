package com.wei.base.exception;

import com.wei.base.constant.ExceptionCodeEnum;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月23日
 */
public class ArithmeticException extends RuntimeException{

    private String errorCode = null;

    public ArithmeticException(String errorCode) {
        this.errorCode = errorCode;
    }

    public ArithmeticException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ArithmeticException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public ArithmeticException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ArithmeticException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }
    public ArithmeticException(ExceptionCodeEnum exp) {
        super(exp.getExpMsg());
        this.errorCode = exp.getExpCode();
    }
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
