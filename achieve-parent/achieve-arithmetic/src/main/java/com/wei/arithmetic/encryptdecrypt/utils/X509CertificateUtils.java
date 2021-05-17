package com.wei.arithmetic.encryptdecrypt.utils;

import com.wei.exception.X509CertificateException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月14日
 */
public class X509CertificateUtils extends GMBaseUtil{
    private static final Logger log =  LoggerFactory.getLogger(X509CertificateUtils.class);

    public static X509Certificate getCertFromFilePath(String  filePath) {
       try {
           File file = new File(filePath);
           return getCertFromInputStream(new FileInputStream(file));
       } catch (FileNotFoundException e) {
           log.error("file path not found:{}", filePath);
           throw new X509CertificateException(e, e.getMessage());
       }
   }

    public static X509Certificate getCertFromInputStream(InputStream inputStream){
        if(inputStream == null){
            throw new X509CertificateException("getCertFromInputStream:inputStream is null");
        }
        try {
            return (X509Certificate) CertificateFactory.getInstance("X.509", BouncyCastleProvider.PROVIDER_NAME)
                    .generateCertificate(inputStream);
        } catch (CertificateException | NoSuchProviderException e) {
            throw new X509CertificateException(e, e.getMessage());
        }
    }


}

