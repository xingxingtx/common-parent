package com.wei.cert.generate.factory;

import com.wei.cert.generate.CertGenerate;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月23日
 */
public class CertGenerateFactory {

    private CertGenerateFactory() {
    }

    public void generateCert(CertGenerate certGenerate){
        certGenerate.generateRootCert();
        certGenerate.generateUserCert();
    }

    public static CertGenerateFactory getInstance(){
        return CertGenerateFactoryHelper.singleton;
    }

    private static class CertGenerateFactoryHelper{
        private static CertGenerateFactory singleton = new CertGenerateFactory();
    }
}
