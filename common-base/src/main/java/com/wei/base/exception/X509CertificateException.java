package com.wei.base.exception;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月14日
 */
public class X509CertificateException extends RuntimeException {

    private String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public X509CertificateException(String errorCode) {
        this.errorCode = errorCode;
    }

    public X509CertificateException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public X509CertificateException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public X509CertificateException(Throwable cause, String errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public X509CertificateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }
}