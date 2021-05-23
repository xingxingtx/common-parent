package com.wei.arithmetic.encryptdecrypt.utils;

import com.wei.arithmetic.encryptdecrypt.entity.KeyEntity;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月23日
 */
public class AESUtils {
    private static final Logger log = LoggerFactory.getLogger(AESUtils.class);
    private static final String alg = "AES";
    private static final int aesInitLength = 128;

    public AESUtils() {
    }

    public static KeyEntity createASEKeys() {
        KeyEntity key = new KeyEntity();

        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            SecretKey secretKey = keyGen.generateKey();
            key.setSecretKey(secretKey);
        } catch (NoSuchAlgorithmException var3) {
            log.error(var3.getMessage(), var3);
        }

        return key;
    }

    public static byte[] encryptionByAES(KeyEntity key, String data) {
        SecretKeySpec secretKey = new SecretKeySpec(key.getSecretKey().getEncoded(), "AES");

        try {
            byte[] dataByte = data.getBytes("UTF-8");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
            cipher.init(1, secretKey, iv);
            return cipher.doFinal(dataByte);
        } catch (Exception var6) {
            log.error(var6.getMessage(), var6);
            return null;
        }
    }

    public static byte[] decryptionByAES(KeyEntity key, byte[] data) {
        SecretKeySpec secretKey = new SecretKeySpec(key.getSecretKey().getEncoded(), "AES");

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
            cipher.init(2, secretKey, iv);
            return cipher.doFinal(data);
        } catch (Exception var5) {
            log.error(var5.getMessage(), var5);
            return null;
        }
    }

    public static SecretKey KeySpec(String secretKeyString) {
        byte[] keyBytes = (new Base64(0)).decode(secretKeyString);
        return new SecretKeySpec(keyBytes, 0, keyBytes.length, "AES");
    }

    public static String parseByte2To16(byte[] data) {
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < data.length; ++i) {
            String s = Integer.toHexString(data[i] & 255);
            if (s.length() == 1) {
                s = '0' + s;
            }

            sb.append(s.toUpperCase());
        }

        return sb.toString();
    }

    public static byte[] parseByte16To2(String data) {
        if (data.length() <= 1) {
            return null;
        } else {
            byte[] result = new byte[data.length() / 2];

            for(int i = 0; i < data.length() / 2; ++i) {
                int high = Integer.parseInt(data.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(data.substring(i * 2 + 1, i * 2 + 2), 16);
                result[i] = (byte)(high * 16 + low);
            }

            return result;
        }
    }
}
