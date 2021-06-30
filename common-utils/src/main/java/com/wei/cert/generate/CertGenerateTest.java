package com.wei.cert.generate;


import com.wei.cert.generate.factory.CertGenerateFactory;
import com.wei.cert.generate.rsa.RsaCertGenerate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月19日
 */
public class CertGenerateTest {
    private static final Logger log = LoggerFactory.getLogger(CertGenerateTest.class);
    public static void main(String[] args) {
        CertGenerateFactory.getInstance().generateCert(new RsaCertGenerate());
    }

}
