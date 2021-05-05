package com.wei.utils.lottery.impl;

import com.wei.utils.lottery.abs.AbstractLottery;
import com.wei.utils.lottery.common.dto.Lottery;
import com.wei.utils.lottery.common.dto.LotteryAwards;
import com.wei.utils.lottery.common.dto.LotteryVO;
import com.wei.utils.lottery.common.exception.LotteryException;
import com.wei.utils.lottery.common.fsm.LotteryType;
import com.wei.utils.lottery.common.util.LotteryUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 前区1-35，后区1-12
 * @Describe Superlotto
 * @Author a_pen
 * @Date 2020年09月11日
 */
public class SuperLotto extends AbstractLottery {

    /**篮球*/
    private static final int[] RED_BALLS = null;
    /**红球*/
    private static final int[] BLUE_BALLS = null;

    private static volatile BigDecimal jackpot = new BigDecimal(0);

    static {
        LotteryUtils.getNumbers(1, 35);
        LotteryUtils.getNumbers(1, 12);
    }

    @Override
    public LotteryType type() {
        return LotteryType.SUPER_LOTTO;
    }

    @Override
    public BigDecimal getAward(LotteryVO lotteryVO) {

        return null;
    }

    @Override
    public String getPeriods() {
        return null;
    }

    @Override
    public BigDecimal jackpot() {
        return jackpot;
    }

    @Override
    public Lottery winningNumber() {
        return null;
    }

    @Override
    public BigDecimal currentSalesAmount() {
        return null;
    }

    @Override
    public LotteryVO buyLottery(int noteNum) {
        return null;
    }

    @Override
    public List<LotteryAwards> getLotteryAwards() {
        return null;
    }

    @Override
    protected BigDecimal toExpiry(List<? extends Lottery> lotteries, List<LotteryAwards> awards) {
        return null;
    }
}
