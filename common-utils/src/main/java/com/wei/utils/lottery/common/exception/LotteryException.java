package com.wei.utils.lottery.common.exception;

/**
 * @Describe LotteryException
 * @Author a_pen
 * @Date 2020年09月11日
 */
public class LotteryException extends RuntimeException{
    private String errorMessage;
    private String errorCode;


    public  LotteryException(){
    }
    public  LotteryException(String errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    public  LotteryException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
