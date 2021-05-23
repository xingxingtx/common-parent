package com.wei.arithmetic.encryptdecrypt.utils;

import com.wei.base.exception.ArithmeticException;
import com.wei.base.constant.ExceptionCodeEnum;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月23日
 */
public class CAUtils {
    public static final String signatureAlgorithm = "SHA512WITHRSA";
    private static final Logger log = LoggerFactory.getLogger(CAUtils.class);

    private CAUtils() {
    }

    public static X509Certificate generateFromInputStream(InputStream inputStream) throws ArithmeticException {
        if (inputStream == null) {
            throw new ArithmeticException(ExceptionCodeEnum.securityEmptyCretExcetion);
        } else {
            try {
                return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(inputStream);
            } catch (CertificateException var2) {
                log.error(var2.getMessage(), var2);
                return null;
            }
        }
    }

    public static String getSignature(InputStream inputStream) {
        X509Certificate cret = generateFromInputStream(inputStream);
        if (cret == null) {
            log.warn("No Valid certification found from give inputStream");
            return null;
        } else {
            return Base64.toBase64String(cret.getSignature());
        }
    }
}
