package com.wei.utils.lottery.common.util;

import com.wei.utils.lottery.common.dto.LotteryCount;
import org.apache.commons.lang3.RandomUtils;

import java.util.*;

/**
 * @Describe Lottery
 * @Author a_pen
 * @Date 2020年09月11日
 */
public class LotteryUtils {


    public static Integer[] getNumbers(int start, int end) {
        Integer[] numbers = new Integer[end - start + 1];
        int j = 0;
        for (int i = start; i < end + 1; i++) {
            numbers[j] = i;
            j++;
        }
        return numbers;
    }

    /**
     * 生成彩票的期数
     *
     * @param days 一周的那几天开奖，周日为：1，周一为：2 。。。。。
     * @return
     */
    public static String generatePeriods(int[] days) {
        Arrays.sort(days);
        Calendar calendar = Calendar.getInstance();
        /**获取星期几，周日返回：1，周一返回：2 。。。。*/
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String[] dayOfWeeks = getDayOfWeeks(day, days);
        int rellDay = Integer.parseInt(dayOfWeeks[1]);
        if(day < rellDay){
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + (rellDay - day));
        }
        int weeks = calendar.getWeeksInWeekYear();
        int year = calendar.getWeekYear();
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DATE);
        StringBuilder sb = new StringBuilder();
        sb.append(year);
        sb.append(getTimes(weeks, days, day));
        sb.append(" ");
        sb.append(String.valueOf((100 + month)).substring(1));
        sb.append(".");
        sb.append(date);
        sb.append(dayOfWeeks[0]);
        return sb.toString();
    }

    private static String getTimes(int weeks, int[] days, int day) {
        Arrays.sort(days);
        if (days == null || days.length == 0) {
            return null;
        }
        int number = 0;
        for (int i = 0; i < days.length; i++) {
            if (days[i] <= day) {
                number++;
            }
        }
        int times = (weeks - 1) * days.length + number;
        return String.valueOf(1000 + times).substring(1);
    }

    private static String[] getDayOfWeeks(int day, int[] days) {
        String[] dayName = new String[2];
        Arrays.sort(days);
        for (int i = 0; i < days.length; i++) {
            if(days[days.length - 1] < day){
                day = days[0];
                break;
            }
            if(day < days[i]){
                day = days[i];
                break;
            }
        }
        if(day == 1){
            dayName[1] = "8";
        }else {
            dayName[1] = String.valueOf(day);
        }
        switch (day) {
            case 1:
                dayName[0] = "(周日)";
                break;
            case 2:
                dayName[0] = "(周一)";
                break;
            case 3:
                dayName[0] = "(周二)";
                break;
            case 4:
                dayName[0] = "(周三)";
                break;
            case 5:
                dayName[0] = "(周四)";
                break;
            case 6:
                dayName[0] = "(周五)";
                break;
            case 7:
                dayName[0] = "(周六)";
                break;
            default:
                dayName[0] = "(日期错误)";
                break;
        }

        return dayName;
    }

    /**
     * 从origin数组中取出number个值。
     *
     * @param origin
     * @param number
     * @return
     */
    public static Integer[] getRandomNumbers(Integer[] origin, int number) {
        if (null == origin || origin.length == 0 || number == 0) {
            return origin;
        }
        Set<Integer> set = new HashSet();
        while (true) {
            int nextInt = RandomUtils.nextInt(1, origin.length + 1);
            set.add(origin[nextInt - 1]);
            if (set.size() == number) {
                Integer[] target = set.toArray(new Integer[]{});
                sort(target);
                return target;
            }
        }
    }

    /**
     * 去除originBalls集合中包含excludeBalls的元素
     * @param originBalls
     * @param excludeBalls
     * @return
     */
    public static Integer[] excludeSameBalls(Integer[] originBalls, Integer[] excludeBalls) {
        if (originBalls == null || excludeBalls == null || originBalls.length == 0 || excludeBalls.length == 0) {
            return originBalls;
        }
        List<Integer> list = new ArrayList<>(Arrays.asList(originBalls));
        list.removeAll(Arrays.asList(excludeBalls));
        return list.toArray(new Integer[]{});
    }


    /**
     * 冒泡排序
     */
  public static void sort(Integer[] targrt){
      int len = targrt.length-1;
      int temp = 0; // 开辟一个临时空间, 存放交换的中间值
      int tempPostion = 0;  // 记录最后一次交换的位置
      // 要遍历的次数
      for (int i = 0; i < targrt.length-1; i++) {
          int flag = 1; //设置一个标志位
          //依次的比较相邻两个数的大小，遍历一次后，把数组中第i小的数放在第i个位置上
          for (int j = 0; j < len; j++) {
              // 比较相邻的元素，如果前面的数小于后面的数，交换
              if (targrt[j] > targrt[j+1]) {
                  temp = targrt[j+1];
                  targrt[j+1] = targrt[j];
                  targrt[j] = temp;
                  flag = 0;  //发生交换，标志位置0
                  tempPostion = j;  //记录交换的位置
              }
          }
          len = tempPostion; //把最后一次交换的位置给len，来缩减内循环的次数
          if (flag == 1) {//如果没有交换过元素，则已经有序
              return;
          }

      }
  }

  public static LotteryCount getLotteryCount(Integer[] balls){
      LotteryCount count = new LotteryCount();
      if(balls != null && balls.length > 0){
          for (Integer ball : balls) {

          }
      }
      return count;
  }
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int nextInt = RandomUtils.nextInt(0, 2);
            System.out.println(nextInt);
        }
    }

}
