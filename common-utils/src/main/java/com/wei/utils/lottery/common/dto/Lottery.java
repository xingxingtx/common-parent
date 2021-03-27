package com.wei.utils.lottery.common.dto;

import com.wei.utils.lottery.common.fsm.LotteryType;

import java.math.BigDecimal;

/**
 * @Describe Lottery
 * @Author a_pen
 * @Date 2020年09月11日
 */
public class Lottery {
    /**
     * 彩票类型
     */
    private LotteryType type;

    /**
     * 彩票名称
     */
    private String typeName;
    /**
     * 开奖期数
     */
    private String periods;
    /**
     * 彩票购买时间
     */
    private String createTime;

    /**
     * 彩票购买金额
     */
    private BigDecimal amount;

    private LotteryCount lotteryCount;
    /**
     * 彩票唯一编号
     */
    private String lottoNo;

    public String getLottoNo() {
        return lottoNo;
    }

    public void setLottoNo(String lottoNo) {
        this.lottoNo = lottoNo;
    }

    public LotteryCount getLotteryCount() {
        return lotteryCount;
    }

    public void setLotteryCount(LotteryCount lotteryCount) {
        this.lotteryCount = lotteryCount;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public LotteryType getType() {
        return type;
    }

    public void setType(LotteryType type) {
        this.type = type;
    }

    public String getPeriods() {
        return periods;
    }

    public void setPeriods(String periods) {
        this.periods = periods;
    }


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
