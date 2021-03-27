package com.wei.utils.lottery.impl;

import com.wei.utils.constant.CodeConstant;
import com.wei.utils.generate.StringUtils;
import com.wei.utils.lottery.abs.AbstractLottery;
import com.wei.utils.lottery.common.dto.Lottery;
import com.wei.utils.lottery.common.dto.LotteryAwards;
import com.wei.utils.lottery.common.dto.LotteryDTO;
import com.wei.utils.lottery.common.dto.LotteryRedAndBlue;
import com.wei.utils.lottery.common.dto.LotteryRule;
import com.wei.utils.lottery.common.dto.LotteryVO;
import com.wei.utils.lottery.common.exception.LotteryException;
import com.wei.utils.lottery.common.fsm.AwardsType;
import com.wei.utils.lottery.common.fsm.LotteryType;
import com.wei.utils.lottery.common.util.LotteryUtils;
import com.wei.utils.utils.DateUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 前区号码1-33，后区号码1-16
 * @Describe UnionLotto
 * @Author a_pen
 * @Date 2020年09月11日
 */
public class UnionLotto extends AbstractLottery{

    /**篮球*/
    private static Integer[] RED_BALLS = null;
    /**红球*/
    private static  Integer[] BLUE_BALLS = null;
    /**开奖时间，每周日，周二，周四*/
    private static  int[] OPEN_AWARD_TIME = null;
    /**每次开奖具体时间21:15*/
    private static final String EVERY_TIME = "21:15";

    private static Map<String, String> map = new ConcurrentHashMap<>();
    /**奖项*/
    private static List<LotteryAwards> DEFAULT_LOTTERY_AWARDS;


    static {
        RED_BALLS = LotteryUtils.getNumbers(1, 33);
        BLUE_BALLS = LotteryUtils.getNumbers(1, 16);
        OPEN_AWARD_TIME = new int[]{1, 3 ,5};
    }

    static {
        getDefaultLotteryAwards();
    }


    @Override
    public LotteryType type() {
        return LotteryType.UNION_LOTTO;
    }

    @Override
    public BigDecimal getAward(LotteryVO lotteryVO) {


        return null;
    }

    @Override
    public String getPeriods() {
        String key = getKey();
        if (StringUtils.isEmpty(map.get(key), false)){
            map.clear();
            final String  period = LotteryUtils.generatePeriods(OPEN_AWARD_TIME);
            map.put(key, period);
        }
        return map.get(key);
    }

    @Override
    public BigDecimal jackpot() {

        return null;
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
        if(noteNum  <= 0){
            throw new LotteryException(CodeConstant.LotteryConstant.LOTTERY_BUY_ERROR, "UnionLotto buy exception , note number less than one");
        }
        List<LotteryRedAndBlue> lotteries = new ArrayList<>(noteNum);
        for (int i = 0; i < noteNum; i++) {
            LotteryRedAndBlue blue = new LotteryRedAndBlue();
            blue.setTypeName(type().getName());
            blue.setPeriods(getPeriods());
            blue.setAmount(eachNoteAmount());
            blue.setRedBalls(LotteryUtils.getRandomNumbers(RED_BALLS, 6));
            blue.setBlueBalls(LotteryUtils.getRandomNumbers(BLUE_BALLS, 1));
            lotteries.add(blue);
        }
        LotteryVO<LotteryRedAndBlue> vo = new LotteryVO<>();
        vo.setLottery(lotteries);
        vo.setSum(eachNoteAmount().multiply(new BigDecimal(noteNum)));
        return vo;
    }


    public LotteryVO buyLotteryByExclude(int noteNum, LotteryDTO dto) {
        if(noteNum  <= 0){
            throw new LotteryException(CodeConstant.LotteryConstant.LOTTERY_BUY_ERROR, "UnionLotto buy exception , note number less than one");
        }
        List<LotteryRedAndBlue> lotteries = new ArrayList<>(noteNum);
        for (int i = 0; i < noteNum; i++) {
            LotteryRedAndBlue blue = new LotteryRedAndBlue();
            blue.setType(type());
            blue.setPeriods(getPeriods());
            blue.setAmount(eachNoteAmount());
            blue.setRedBalls(LotteryUtils.getRandomNumbers(
                    LotteryUtils.excludeSameBalls(RED_BALLS, dto == null ? null: dto.getRedBallsExclude()), 6));
            blue.setBlueBalls(LotteryUtils.getRandomNumbers(
                    LotteryUtils.excludeSameBalls(BLUE_BALLS, dto == null ? null: dto.getBlueBallsExclude()), 1));
            lotteries.add(blue);
        }
        LotteryVO<LotteryRedAndBlue> vo = new LotteryVO<>();
        vo.setLottery(lotteries);
        vo.setSum(eachNoteAmount().multiply(new BigDecimal(noteNum)));
        return vo;
    }


    private  static String getKey() {
        Calendar calendar =  Calendar.getInstance();
        int weeks = calendar.getWeeksInWeekYear();
        int year = calendar.getWeekYear();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case 1:
            case 7:
            case 6:
                if(day != 1){
                    weeks += 1;
                }
                day = 1;
                break;
            case 2:
            case 3:
                day = 3;
                break;
            case 4:
            case 5:
                day = 5;
                break;
            default:
                throw new LotteryException("days exception", "days exception");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(year);
        sb.append(weeks);
        sb.append(day);
        return sb.toString();
    }

    /**
     *单式购买
     * @param redBalls 红球
     * @param blueBalls 篮球
     * @return
     */
    public Lottery buyOfSimplex(Integer[] redBalls, Integer[] blueBalls){
        return null;
    }

    /**
     *复式购买
     * @return
     */
    public Lottery buyOfComplex(Integer[] redBalls, Integer[] blueBalls){
        return null;
    }

    private static void getDefaultLotteryAwards() {
        DEFAULT_LOTTERY_AWARDS = new ArrayList<>();
        /**一等奖*/
        LotteryAwards var1 = new LotteryAwards();
        var1.setLotteryType(LotteryType.UNION_LOTTO);
        var1.setAwards(new BigDecimal(5000000));
        var1.setAwardsType(AwardsType.FIRST_PRISE);
        LotteryRule first = new LotteryRule();
        first.setOpenTime(EVERY_TIME);
        first.setFrontNumber(new int[]{6});
        first.setBackNumber(new int[]{1});
        first.setDescribe("中六红一蓝");
        var1.setRule(first);
        /**二等奖*/
        LotteryAwards var2 = new LotteryAwards();
        var2.setLotteryType(LotteryType.UNION_LOTTO);
        var2.setAwards(new BigDecimal(100000));
        var2.setAwardsType(AwardsType.SECOND_PRISE);
        LotteryRule two = new LotteryRule();
        two.setOpenTime(EVERY_TIME);
        two.setFrontNumber(new int[]{6});
        two.setBackNumber(new int[]{0});
        two.setDescribe("中六红");
        var2.setRule(two);
        /**三等奖*/
        LotteryAwards var3 = new LotteryAwards();
        var3.setLotteryType(LotteryType.UNION_LOTTO);
        var3.setAwards(new BigDecimal(3000));
        var3.setAwardsType(AwardsType.THIRD_PRISE);
        LotteryRule third = new LotteryRule();
        third.setOpenTime(EVERY_TIME);
        third.setFrontNumber(new int[]{5});
        third.setBackNumber(new int[]{1});
        third.setDescribe("中五红一蓝");
        var3.setRule(third);
        /**四等奖*/
        LotteryAwards var4 = new LotteryAwards();
        var4.setLotteryType(LotteryType.UNION_LOTTO);
        var4.setAwards(new BigDecimal(200));
        var4.setAwardsType(AwardsType.FOURTH_PRISE);
        LotteryRule four = new LotteryRule();
        four.setOpenTime(EVERY_TIME);
        four.setFrontNumber(new int[]{4, 5});
        four.setBackNumber(new int[]{1, 0});
        four.setDescribe("中六红一蓝");
        var4.setRule(four);
        /**五等奖*/
        LotteryAwards var5 = new LotteryAwards();
        var5.setLotteryType(LotteryType.UNION_LOTTO);
        var5.setAwards(new BigDecimal(10));
        var5.setAwardsType(AwardsType.FIVE_PRISE);
        LotteryRule five = new LotteryRule();
        five.setOpenTime(EVERY_TIME);
        five.setFrontNumber(new int[]{4, 3});
        five.setBackNumber(new int[]{0, 1});
        five.setDescribe("中六红一蓝");
        var5.setRule(five);
        /**六等奖*/
        LotteryAwards var6 = new LotteryAwards();
        var6.setLotteryType(LotteryType.UNION_LOTTO);
        var6.setAwards(new BigDecimal(5));
        var6.setAwardsType(AwardsType.SIX_PRISE);
        LotteryRule six = new LotteryRule();
        six.setOpenTime(EVERY_TIME);
        six.setFrontNumber(new int[]{2, 1, 0});
        six.setBackNumber(new int[]{1,1,1});
        six.setDescribe("中一蓝+(一红或二红）");
        var6.setRule(six);
        DEFAULT_LOTTERY_AWARDS.add(var1);
        DEFAULT_LOTTERY_AWARDS.add(var2);
        DEFAULT_LOTTERY_AWARDS.add(var3);
        DEFAULT_LOTTERY_AWARDS.add(var4);
        DEFAULT_LOTTERY_AWARDS.add(var5);
        DEFAULT_LOTTERY_AWARDS.add(var6);
    }
    @Override
    public List<LotteryAwards> getLotteryAwards() {
        return DEFAULT_LOTTERY_AWARDS;
    }

}
