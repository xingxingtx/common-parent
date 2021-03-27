package com.wei.utils.exception;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月15日
 */
public class LotteryTypeException extends RuntimeException {

    private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public LotteryTypeException(String errorCode) {
        this.errorCode = errorCode;
    }

    public LotteryTypeException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public LotteryTypeException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public LotteryTypeException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public LotteryTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }
}
