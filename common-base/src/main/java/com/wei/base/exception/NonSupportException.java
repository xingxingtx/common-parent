package com.wei.base.exception;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年07月13日
 */
public class NonSupportException extends RuntimeException {

    private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public NonSupportException(String errorCode) {
        this.errorCode = errorCode;
    }

    public NonSupportException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public NonSupportException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public NonSupportException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public NonSupportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }
}