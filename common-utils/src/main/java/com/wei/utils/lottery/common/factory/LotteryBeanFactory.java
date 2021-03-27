package com.wei.utils.lottery.common.factory;

import com.wei.utils.lottery.common.fsm.LotteryType;
import com.wei.utils.lottery.impl.SuperLotto;
import com.wei.utils.lottery.impl.UnionLotto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2020年10月30日
 */
public class LotteryBeanFactory {

    private static Map<String, Object> objectMap = new ConcurrentHashMap<>();

    static {
        objectMap.put(LotteryType.UNION_LOTTO.name(), new UnionLotto());
        objectMap.put(LotteryType.SUPER_LOTTO.name(), new SuperLotto());
    }
    private LotteryBeanFactory() {
    }
    public static Object getBean(String className){
        synchronized (objectMap){
            if (objectMap.containsKey(className)){
                return objectMap.get(className);
            }else {
                Object instance = null;
                try {
                    instance = Class.forName(className).newInstance();
                    String type = LotteryType.getLotteryTypeByClassName(className).name();
                    objectMap.put(type, instance);
                } catch (Exception e) {

                }
                return instance;
            }
        }
    }
}
