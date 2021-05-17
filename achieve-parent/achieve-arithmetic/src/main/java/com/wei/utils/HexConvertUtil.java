package com.wei.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月06日
 */
public class HexConvertUtil {

    public static String parseBy2To16(byte[] data){
        if(data == null || data.length == 0){
            return null;
        }
        StringBuffer sb  = new StringBuffer();
        for (byte datum : data) {
            String hexString = Integer.toHexString(datum & 0xff);
            if(hexString.length() == 1){
                hexString += '0';
            }
            sb.append(hexString.toUpperCase());
        }
        return sb.toString();
    }

    public static byte[] parseBy16To2(String data) {
        if(StringUtils.isEmpty(data)){
            return null;
        }
        byte[] result = new byte[data.length() / 2];
        for (int i = 0; i < data.length() / 2; i++) {
            int high = Integer.parseInt(data.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(data.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}
