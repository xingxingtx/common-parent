package com.wei.arithmetic.encryptdecrypt.entity;


import org.apache.commons.lang3.StringUtils;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月06日
 */
public class MessageEntity {
    /**数据报文*/
    private String data;
    private String secretKey;
    private String publicKey;
    private String privateKey;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public boolean isEmpty(String ... content){
        if(content == null || content.length == 0){
            return true;
        }
        for (String s : content) {
            if(StringUtils.isEmpty(s)){
                return true;
            }
        }
        return false;
    }
}
