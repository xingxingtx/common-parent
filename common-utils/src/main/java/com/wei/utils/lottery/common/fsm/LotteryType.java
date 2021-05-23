package com.wei.utils.lottery.common.fsm;

import com.wei.utils.exception.LotteryTypeException;
import com.wei.utils.lottery.impl.ElevenSelectedFive;
import com.wei.utils.lottery.impl.FootballLotto;
import com.wei.utils.lottery.impl.WelfareLotto3d;
import com.wei.utils.lottery.impl.SuperLotto;
import com.wei.utils.lottery.impl.UnionLotto;

/**
 * @Describe LotteryType
 * @Author a_pen
 * @Date 2020年09月11日
 */
public enum LotteryType {
    UNION_LOTTO(UnionLotto.class.getName(),"双色球"),
    SUPER_LOTTO(SuperLotto.class.getName(), "大乐透"),
    FOOTBALL_LOTTO(FootballLotto.class.getName(), "足彩"),
    ELEVEN_SELECTED_FIVE(ElevenSelectedFive.class.getName(), "11选五"),
    WELFARE_LOTTO_3D(WelfareLotto3d.class.getName(), "福彩3D"),
    ;
    private String className;
    private String name;

    private LotteryType(String className, String name) {
        this.className = className;
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public String getName() {
        return name;
    }

    public static LotteryType getLotteryTypeByClassName(String className){
        for (LotteryType type : values()) {
            if(type.getClassName().equals(className)){
                return type;
            }
        }
        throw new LotteryTypeException("nonsupport LotteryTypeException by className:" + className);
    }
}
