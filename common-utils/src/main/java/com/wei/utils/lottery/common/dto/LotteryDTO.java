package com.wei.utils.lottery.common.dto;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2020年10月30日
 */
public class LotteryDTO {
    /**
     * 红球
     */
    private Integer[] redBallsExclude;

    /**
     * 蓝球
     */
    private Integer[] blueBallsExclude;

    public Integer[] getRedBallsExclude() {
        return redBallsExclude;
    }

    public void setRedBallsExclude(Integer[] redBallsExclude) {
        this.redBallsExclude = redBallsExclude;
    }

    public Integer[] getBlueBallsExclude() {
        return blueBallsExclude;
    }

    public void setBlueBallsExclude(Integer[] blueBallsExclude) {
        this.blueBallsExclude = blueBallsExclude;
    }
}
