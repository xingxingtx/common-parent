package com.wei.utils.lottery.abs;

import com.wei.utils.lottery.common.dto.Lottery;
import com.wei.utils.lottery.common.dto.LotteryAwards;
import com.wei.utils.lottery.common.dto.LotteryVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Describe AbstractLottery
 * @Author a_pen
 * @Date 2020年09月11日
 */
public abstract class AbstractLottery implements ILottery {
    private static final Logger log = LoggerFactory.getLogger(AbstractLottery.class);
    @Override
    public BigDecimal eachNoteAmount() {
        return new BigDecimal(2);
    }

    /**
     * 买取彩票
     * @param noteNum 彩票注数
     * @return
     */
    public abstract LotteryVO buyLottery(int noteNum);

    /**
     * 获取奖项
     * @return
     */
    public abstract List<LotteryAwards> getLotteryAwards();

    @Override
    public BigDecimal getAward(LotteryVO lotteryVO){
        if (lotteryVO == null || lotteryVO.getLottery() == null
                || lotteryVO.getLottery().size() == 0){
            log.warn("no lottery buy, you can not get awards");
            return new BigDecimal(0);
        }
        List<LotteryAwards> awards = getLotteryAwards();
        if(awards == null || awards.size() == 0){
            log.warn("no lottery awards");
            return new BigDecimal(0);
        }
        List lottery = lotteryVO.getLottery();
        return toExpiry(lottery, getLotteryAwards());
    }

    protected abstract BigDecimal toExpiry(List<? extends Lottery> lotteries, List<LotteryAwards> awards);

}
