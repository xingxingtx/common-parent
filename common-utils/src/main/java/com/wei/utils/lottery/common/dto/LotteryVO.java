package com.wei.utils.lottery.common.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2020年10月30日
 */
public class LotteryVO<T extends Lottery> {

    private List<T> lottery;

    private BigDecimal sum;


    public List<T> getLottery() {
        return lottery;
    }

    public void setLottery(List<T> lottery) {
        this.lottery = lottery;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for(T t :lottery){
            sb.append("第" + i + "注:");
            sb.append(t.toString());
            sb.append("\n");
            i++;
        }
        return "LotteryVO:{" +"\n" +
                sb.toString() +
                ", sum=" + sum + "\n" +
                '}';
    }


}
