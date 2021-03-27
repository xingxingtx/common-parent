package com.wei.utils.generate;

import org.apache.commons.lang3.RandomUtils;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Describe GenerateOnlyNo
 * @Author a_pen
 * @Date 2020年09月02日
 */
public class GenerateOnlyNoUtils {

    private static ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");

    private static final AtomicInteger SEQ = new AtomicInteger(1000);

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyMMddHHmmssSS");

    private volatile static String IP_SUFFIX = null;

    public static String generateOrderNo(){
        LocalDateTime dateTime = LocalDateTime.now(ZONE_ID);
        return dateTime.format(DATE_TIME_FORMATTER) + getLocalIpSuffix() + SEQ.getAndIncrement();
    }

    private static String getLocalIpSuffix() {
        if(null != IP_SUFFIX){
            return IP_SUFFIX;
        }
        try {
            synchronized (GenerateOnlyNoUtils.class){
                if( null != IP_SUFFIX){
                    return IP_SUFFIX;
                }
                InetAddress address = InetAddress.getLocalHost();
                String hostAddress = address.getHostAddress();
                if(null != hostAddress && hostAddress.trim().length() > 4){
                    String ipSuffix = hostAddress.trim().split("\\.")[3];
                    if(ipSuffix.length() == 2){
                        IP_SUFFIX = ipSuffix;
                        return IP_SUFFIX;
                    }
                    ipSuffix = "0" + ipSuffix;
                    IP_SUFFIX = ipSuffix.substring(ipSuffix.length() - 2);
                    return IP_SUFFIX;
                }
                IP_SUFFIX = String.valueOf(RandomUtils.nextInt(10, 20));
                return IP_SUFFIX;
            }
        }catch (Exception e){
            IP_SUFFIX = String.valueOf(RandomUtils.nextInt(10, 20));
            return IP_SUFFIX;
        }
    }



    public static void main(String[] args) {
        List<String> orderNos = Collections.synchronizedList(new ArrayList<String>());
        IntStream.range(0, 2147480).parallel().forEach(i ->{
            orderNos.add(generateOrderNo());
        });
        List<String> collect = orderNos.parallelStream().distinct().collect(Collectors.toList());
        System.out.println("生成订单数：" + orderNos.get(111111));
        System.out.println("生成订单数：" + orderNos.size());
        System.out.println("过滤重复后的订单数量：" + (orderNos.size() - collect.size()));
    }
}
