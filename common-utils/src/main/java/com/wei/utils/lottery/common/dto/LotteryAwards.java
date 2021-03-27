package com.wei.utils.lottery.common.dto;

import com.wei.utils.lottery.common.fsm.AwardsType;
import com.wei.utils.lottery.common.fsm.LotteryType;

import java.math.BigDecimal;

/**
 * @Describe 奖项
 * @Author wei.peng
 * @Date 2021年02月03日
 */
public class LotteryAwards {
    /**奖项， 一等奖，二等奖 ....*/
    private AwardsType awardsType;
    /**彩票类型*/
    private LotteryType lotteryType;
    /**金额*/
    private BigDecimal awards;
    /**中奖条件*/
    private LotteryRule rule;

    public AwardsType getAwardsType() {
        return awardsType;
    }

    public void setAwardsType(AwardsType awardsType) {
        this.awardsType = awardsType;
    }

    public LotteryType getLotteryType() {
        return lotteryType;
    }

    public void setLotteryType(LotteryType lotteryType) {
        this.lotteryType = lotteryType;
    }

    public BigDecimal getAwards() {
        return awards;
    }

    public void setAwards(BigDecimal awards) {
        this.awards = awards;
    }

    public LotteryRule getRule() {
        return rule;
    }

    public void setRule(LotteryRule rule) {
        this.rule = rule;
    }
}
