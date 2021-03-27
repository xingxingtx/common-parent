package com.wei.utils.lottery.common.dto;

/**
 * @Describe 彩票统计对象
 * @Author wei.peng
 * @Date 2020年11月11日
 */
public class LotteryCount {
    /**跨度,既最小值与最大值的差*/
    private int span;
    /**和值*/
    private int sumValue;
    /**奇偶*/
    private String oddEven;

    public int getSpan() {
        return span;
    }

    public void setSpan(int span) {
        this.span = span;
    }

    public int getSumValue() {
        return sumValue;
    }

    public void setSumValue(int sumValue) {
        this.sumValue = sumValue;
    }

    public String getOddEven() {
        return oddEven;
    }

    public void setOddEven(String oddEven) {
        this.oddEven = oddEven;
    }
}
