package com.wei.utils.encryptdecrypt.constans;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月06日
 */
public enum EncryptConstans {
    RSA(),
    SM2(),
    SM4(),
    AES(),
    ECDSA(),
    NOT_ENCRYPTED("NOT_ENCRYPTED", 300),
    RSA_AES("RSA_AES", 200),
    RSA_SM4("RSA_SM4", 201),
    SM2_AES("RSA_AES", 202),
    SM2_SM4("RSA_AES", 203),
    ECDSA_AES("RSA_AES", 204),
    ECDSA_SM4("RSA_AES", 205);

    private String type;
    private int algorithm = 300;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(int algorithm) {
        this.algorithm = algorithm;
    }

    EncryptConstans(){}

    EncryptConstans(String type, int algorithm) {
        this.type = type;
        this.algorithm = algorithm;
    }

    public static String getTypeByAlgorithm(int algorithm){
        for (EncryptConstans value : EncryptConstans.values()) {
            if(value.getAlgorithm() == algorithm){
                return value.name();
            }
        }
        return null;
    }

    public static int getAlgorithmByType(String type){
        for (EncryptConstans value : EncryptConstans.values()) {
            if(value.name().equalsIgnoreCase(type)){
                return value.getAlgorithm();
            }
        }
        return NOT_ENCRYPTED.getAlgorithm();
    }

}
