package com.wei.utils.lottery.common.dto;

import cn.hutool.json.JSONUtil;
import com.wei.utils.utils.SortUtil;

/**
 * @Describe LotteryDto
 * @Author a_pen
 * @Date 2020年09月11日
 */
public class LotteryRedAndBlue extends Lottery implements Comparable<LotteryRedAndBlue>{
    /**
     * 红球
     */
    private Integer[] redBalls;

    /**
     * 蓝球
     */
    private Integer[] blueBalls;

    public Integer[] getRedBalls() {
        return redBalls;
    }

    public void setRedBalls(Integer[] redBalls) {
        this.redBalls = redBalls;
    }

    public Integer[] getBlueBalls() {
        return blueBalls;
    }

    public void setBlueBalls(Integer[] blueBalls) {
        this.blueBalls = blueBalls;
    }

    @Override
    public int compareTo(LotteryRedAndBlue lott) {
        Integer[] balls = lott.getBlueBalls();
        Integer[] blueBalls = getBlueBalls();
        int compare = SortUtil.compareToTwoArray(blueBalls, balls);
        if(compare == 0){
            return SortUtil.compareToTwoArray(getRedBalls(), lott.getRedBalls());
        }
        return compare;

    }

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
