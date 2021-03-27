package com.wei.utils.lottery.common.clent;

import com.wei.utils.lottery.common.dto.LotteryRedAndBlue;
import com.wei.utils.lottery.common.dto.LotteryVO;
import com.wei.utils.lottery.common.factory.LotteryBeanFactory;
import com.wei.utils.lottery.common.fsm.AwardsType;
import com.wei.utils.lottery.impl.UnionLotto;
import com.wei.utils.lottery.impl.WelfareLotto3d;

import java.util.Collections;
import java.util.List;

/**
 * @Describe PublishLottery
 * @Author a_pen
 * @Date 2020年09月11日
 */
public class PublishLottery {

    public static void main(String[] args) {
        UnionLotto lotto = (UnionLotto)LotteryBeanFactory.getBean(UnionLotto.class.getName());
        LotteryVO<LotteryRedAndBlue> vo = lotto.buyLottery(1000);
        List<LotteryRedAndBlue> lottery = vo.getLottery();
        Collections.sort(lottery);
        System.out.println(vo);



        /*String str = "{\"amount\":2,\"blueBalls\":[6],\"redBalls\":[2,6,8,22,24,26],\"type\":\"UNION_LOTTO\",\"periods\":\"2020154 11.9(周一)\"},{\"amount\":3,\"blueBalls\":[14],\"redBalls\":[8,15,19,22,26,30],\"type\":\"UNION_LOTTO\",\"periods\":\"2020154 11.9(周一)\"}";
        String pattern = "\"\\{\"amount\":2,";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());*/
    }

}
