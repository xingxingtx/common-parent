package com.wei.arithmetic.encryptdecrypt.utils;

import com.wei.base.exception.X509CertificateException;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.gm.SM2P256V1Curve;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.EllipticCurve;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月14日
 */
public class SM2Util extends GMBaseUtil {
    private static final SM2P256V1Curve CURVE = new SM2P256V1Curve();
    private static final BigInteger SM2_ECC_P;
    private static final BigInteger SM2_ECC_A;
    private static final BigInteger SM2_ECC_B;
    private static final BigInteger SM2_ECC_N;
    private static final BigInteger SM2_ECC_H;
    private static final BigInteger SM2_ECC_GX;
    private static final BigInteger SM2_ECC_GY;
    private static final ECPoint G_POINT;
    private static final ECDomainParameters DOMAIN_PARAMS;
    private static final EllipticCurve JDK_CURVE;
    private static final java.security.spec.ECPoint JDK_G_POINT;
    private static final ECParameterSpec JDK_EC_SPEC;

    public static KeyPair generateBCECKeyPair() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC", BouncyCastleProvider.PROVIDER_NAME);
            org.bouncycastle.jce.spec.ECParameterSpec spec = new org.bouncycastle.jce.spec.ECParameterSpec(CURVE, G_POINT, SM2_ECC_N, SM2_ECC_H);
            kpg.initialize(spec, new SecureRandom());
            return kpg.generateKeyPair();
        } catch (Exception e) {
            throw new X509CertificateException(e, e.getMessage());
        }
    }

    static {
        SM2_ECC_P = CURVE.getQ();
        SM2_ECC_A = CURVE.getA().toBigInteger();
        SM2_ECC_B = CURVE.getB().toBigInteger();
        SM2_ECC_N = CURVE.getOrder();
        SM2_ECC_H = CURVE.getCofactor();
        SM2_ECC_GX = new BigInteger("32C4AE2C1F1981195F9904466A39C9948FE30BBFF2660BE1715A4589334C74C7", 16);
        SM2_ECC_GY = new BigInteger("BC3736A2F4F6779C59BDCEE36B692153D0A9877CC62A474002DF32E52139F0A0", 16);
        G_POINT = CURVE.createPoint(SM2_ECC_GX, SM2_ECC_GY);
        DOMAIN_PARAMS = new ECDomainParameters(CURVE, G_POINT, SM2_ECC_N, SM2_ECC_H);
        JDK_CURVE = new EllipticCurve(new ECFieldFp(SM2_ECC_P), SM2_ECC_A, SM2_ECC_B);
        JDK_G_POINT = new java.security.spec.ECPoint(G_POINT.getAffineXCoord().toBigInteger(), G_POINT.getAffineYCoord().toBigInteger());
        JDK_EC_SPEC = new ECParameterSpec(JDK_CURVE, JDK_G_POINT, SM2_ECC_N, SM2_ECC_H.intValue());
    }
}
