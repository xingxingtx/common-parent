package com.wei.utils.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author a_pen
 * @describe
 * @date 2020年07月28日
 */
public class RegexUtil {
    /**验证中文*/
    public  final static  String CHINESE = "[\u4e00-\u9fa5]";

    /**验证邮箱*/
    public  final static  String EMAIL
            = "^[a-z0-9A-Z]+[-|a-z0-9A-Z._]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$";

    /**
     * 验证中文
     * @param chinese
     * @return
     */
    public static boolean isChines(String chinese){
        return match(CHINESE, chinese);
    }

    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
        return match(EMAIL, email);
    }


    public static boolean match(String regex, String res){
        if(StringUtil.isEmpty(regex, false) || StringUtil.isEmpty(res, false)){
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(res);
        return matcher.matches();
    }

}
