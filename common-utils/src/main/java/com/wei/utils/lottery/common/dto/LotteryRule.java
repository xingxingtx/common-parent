package com.wei.utils.lottery.common.dto;

/**
 * @Describe 彩票制度说明对象
 * @Author wei.peng
 * @Date 2021年02月04日
 */
public class LotteryRule {
    /**前区需要中奖个数*/
    private int[] frontNumber;
    /**后区需要中奖个数*/
    private int[] backNumber;
    /**制度说明*/
    private String describe;
    /**赔率*/
    private double lossPerCent;
    /**开奖时间*/
    private String openTime;

    public int[] getFrontNumber() {
        return frontNumber;
    }

    public void setFrontNumber(int[] frontNumber) {
        this.frontNumber = frontNumber;
    }

    public int[] getBackNumber() {
        return backNumber;
    }

    public void setBackNumber(int[] backNumber) {
        this.backNumber = backNumber;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public double getLossPerCent() {
        return lossPerCent;
    }

    public void setLossPerCent(double lossPerCent) {
        this.lossPerCent = lossPerCent;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }
}
