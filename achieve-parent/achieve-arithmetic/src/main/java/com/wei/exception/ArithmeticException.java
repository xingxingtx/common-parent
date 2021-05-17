package com.wei.exception;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月05日
 */
public class ArithmeticException extends RuntimeException{
    private String msg;
    private String code;

    public ArithmeticException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ArithmeticException(Throwable cause, String msg) {
        super(cause);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
