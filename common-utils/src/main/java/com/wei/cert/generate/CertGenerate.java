package com.wei.cert.generate;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月23日
 */
public interface CertGenerate {
    /**
     * 签发用户证书
     */
    void generateUserCert();

    /**
     * 签发根证书
     */
    void generateRootCert();

}
