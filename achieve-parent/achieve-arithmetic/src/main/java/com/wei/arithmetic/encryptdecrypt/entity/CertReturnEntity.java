package com.wei.arithmetic.encryptdecrypt.entity;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月13日
 */
public class CertReturnEntity {
    private byte[] publicKey;
    private byte[] privateKey;
    private String CommonName;

    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    public String getCommonName() {
        return CommonName;
    }

    public void setCommonName(String commonName) {
        CommonName = commonName;
    }
}

