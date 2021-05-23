package com.wei.utils.utils;

import java.net.URL;

/**
 * @author a_pen
 * @describe
 * @date 2020年07月28日
 */
public class StringUtil {

    /**
     * 判断字符串是否为空，
     * @param res
     * @param isTrim
     * @return
     */
    public static boolean isEmpty(String res, boolean isTrim){
        if(!isTrim){
            return (null == res || "".equals(res));
        }
        return (null == res || "".equals(res.trim()));
    }

    /**
     * 判断字符串是否为空，
     * @param res
     * @return
     */
    public static boolean isEmpty(String res){
        return isEmpty(res, true);
    }

    /**
     * 返回字符长度 中文为2个字符长度
     * @param str
     * @return
     */
    public static int stringLength(String str){
        int length = 0;
        if(isEmpty(str, true)){
            return length;
        }
        for (int i = 0; i < str.length(); i++) {
            String temp = str.substring(i, i+1);
            if (temp.matches(RegexUtil.CHINESE)){
                length +=2;
            }else {
                length +=1;
            }
        }
        return length;
    }

    /**
     * 校验str字符长度是否在[start, end]范围
     * @param str
     * @param start
     * @param end
     * @return
     */
    public static boolean lengthValidate(String str,int start, int end) {
        int length = stringLength(str);
        if(start <= length && length <= end){
            return true;
        }
        return false;
    }


    /**
     * 转换成小写字母 ASCII在65-90是大写字母，在97-122是小写字母
     * @param str
     * @return
     */
    public static char toUpper(char str){
        return Character.isLowerCase(str) ? str -= 32 : str;
    }

    /**
     * 转换成大写字母 ASCII在65-90是大写字母，在97-122是小写字母
     * @param str
     * @return
     */
    public static char toLower(char str){
        return Character.isUpperCase(str) ? str += 32 : str;
    }

    /**
     * 字符串str 首字母大小写转换。即如果是小写就转成大写，大写就转成小写
     * @param str
     * @return
     */
    public static String firstUpperOrLower(String str){
        boolean empty = isEmpty(str, true);
        if (empty) {
            return null;
        }
        char[] chars = str.toCharArray();
        chars[0] = Character.isLowerCase(chars[0]) ? toUpper(chars[0]) : toLower(chars[0]);
        return String.valueOf(chars);
    }


    public static String getRootPath(URL url){
        if (url == null){
            return null;
        }
        String fileUrl = url.getFile();
        int pos = fileUrl.indexOf("!");
        if(-1 == pos){
            return fileUrl;
        }
        return fileUrl.substring(5, pos);
    }

    /**
     * "com.wei.util" -->"com/wei/util"
     * @param path
     * @return
     */
    public static String dotToSplash(String path){
        return path.replaceAll("\\.", "/");
    }

    /**
     * 去除文件名后缀 Test.class --> Test
     * @param name  Test.class
     * @param extension .class
     * @return  Test
     */
    public static String trimExtension(String name, String extension){
        if(isEmpty(name, false) || isEmpty(extension, false)){
            return name;
        }
        if(name.endsWith(extension)){
            int i = name.indexOf(extension);
            if(-1 != i){
                return name.substring(0, i);
            }
        }
        return name;
    }

    /**
     *判断是name（xxx。class）否为extension结尾文件
     * @param name
     * @param extension
     * @return
     */
    public static boolean isExtension(String name, String extension){
        if(isEmpty(name, false) || isEmpty(extension, false)){return false;}
        return name.endsWith(extension);
    }

    public static String getClassPath(String path){
        if(isEmpty(path, false)){
            return null;
        }
        String backPagePath = path.replaceAll("/", "\\\\").replaceAll("\\\\", ".");
        int index = backPagePath.indexOf("target.classes.") + 15;
        return backPagePath.substring(index);
    }


    public static String firstCharUppercase(String target) {
        return target != null && !"".equals(target) ? target.substring(0, 1).toUpperCase().concat(target.substring(1)) : target;
    }

    public static String getString(Object str) {
        if (str != null && str.getClass().getName() != null) {
            if ("java.lang.Integer".equals(str.getClass().getName())) {
                return String.valueOf(str);
            } else if ("java.lang.String".equals(str.getClass().getName())) {
                return str != null && !"".equals(str) ? ((String)str).trim() : "";
            } else {
                return String.valueOf(str);
            }
        } else {
            return "";
        }
    }

    public static String substringAfterLast(String str, String separator) {
        if (isEmpty(str, true)) {
            return str;
        } else if (isEmpty(separator, true)) {
            return "";
        } else {
            int pos = str.lastIndexOf(separator);
            return pos != -1 && pos != str.length() - separator.length() ? str.substring(pos + separator.length()) : "";
        }
    }

    public static String strZeroToNull(String str) {
        return str != null && !"".equals(str) && !"00".equals(str) && !"0".equals(str) ? str : null;
    }

    public static String nullToEmpty(String value) {
        return value == null ? "" : value;
    }

    public static boolean equals(String str1, String str2) {
        return str1 == null && str2 == null || str1 != null && str1.equals(str2);
    }

    public static Integer parseInt(String intString) {
        if (intString != null && intString.length() != 0) {
            try {
                return Integer.parseInt(intString);
            } catch (Exception var2) {
                return 0;
            }
        } else {
            return null;
        }
    }

    public static String trimFormat(String inputStr) {
        if (isEmpty(inputStr, true)) {
            return "";
        } else {
            inputStr = inputStr.trim();
            return inputStr.replaceAll("\\s{1,}", " ");
        }
    }

    public static String trimAll(String inputStr) {
        if (isEmpty(inputStr, true)) {
            return "";
        } else {
            inputStr = inputStr.trim();
            return inputStr.replaceAll("\\s{1,}", "");
        }
    }
}
