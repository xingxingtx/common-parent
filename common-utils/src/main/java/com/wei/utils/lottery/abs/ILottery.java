package com.wei.utils.lottery.abs;

import com.wei.utils.lottery.common.dto.Lottery;
import com.wei.utils.lottery.common.dto.LotteryVO;
import com.wei.utils.lottery.common.fsm.LotteryType;

import java.math.BigDecimal;

/**
 * @Describe Lottery
 * @Author a_pen
 * @Date 2020年09月11日
 */
public interface ILottery {

    /**
     * 彩票类型
     * @return
     */
    public LotteryType type();

    /**
     * 中奖彩票奖金
     * @return
     */
    public BigDecimal getAward(LotteryVO lotteryVO);

    /**
     * 开奖期数
     * @return
     */
    public String getPeriods();

    /**
     * 奖池
     * @return
     */
    public BigDecimal jackpot();

    /**
     * 开奖号码
     * @return
     */
    public Lottery winningNumber();

    /**
     * 本期销量金额
     * @return
     */
    public BigDecimal currentSalesAmount();

    /**
     * 每注金额
     * @return
     */
    public BigDecimal eachNoteAmount();
}
