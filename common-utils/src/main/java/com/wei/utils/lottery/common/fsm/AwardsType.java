package com.wei.utils.lottery.common.fsm;

import com.wei.utils.generate.StringUtils;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年02月03日
 */
public enum AwardsType {
    FIRST_PRISE("FIRST_PRISE", "一等奖"),
    SECOND_PRISE("SECOND_PRISE", "二等奖"),
    THIRD_PRISE("THIRD_PRISE", "三等奖"),
    FOURTH_PRISE("FOURTH_PRISE", "四等奖"),
    FIVE_PRISE("FIVE_PRISE", "五等奖"),
    SIX_PRISE("SIX_PRISE", "六等奖"),
    NOT_THE_WINNING("NOT_THE_WINNING", "未获奖")
    ;

    private String key;
    private String desc;

    AwardsType(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }


    public static String getDescByKey(String key){
        if (StringUtils.isEmpty(key, true)){
            return NOT_THE_WINNING.getDesc();
        }
        final AwardsType awardsType;
        try {
            awardsType = valueOf(key);
        } catch (Exception e){
            return NOT_THE_WINNING.getDesc();
        }
        return awardsType.getDesc();
    }
}
