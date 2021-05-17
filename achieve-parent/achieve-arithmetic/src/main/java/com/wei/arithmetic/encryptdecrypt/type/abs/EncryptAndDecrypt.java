package com.wei.arithmetic.encryptdecrypt.type.abs;


import com.wei.arithmetic.encryptdecrypt.entity.MessageEntity;

import java.security.PublicKey;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月06日
 */
public interface EncryptAndDecrypt   {

    /**根据公钥key加密selectKey
     * @param selectKey
     * @param key
     * @return
     */
    String encryptSelectKey(String selectKey, PublicKey key);

    /**
     * 解密密文selectKey
     * @param msg
     * @return
     */
    byte[] decryptSelectKey(MessageEntity msg);

    /**
     * 加密数据报文
     * @param msg
     * @return
     */
    MessageEntity encryptData(MessageEntity msg);

    /**
     * 加密数据报文
     * @param msg
     * @param secretKeyLength
     * @return
     */
    MessageEntity encryptData(MessageEntity msg, int secretKeyLength);

    /**
     * 解密数据报文
     * @param msg
     * @param selectKey
     * @return
     */
    String decryptData(MessageEntity msg, byte[] selectKey);




}
