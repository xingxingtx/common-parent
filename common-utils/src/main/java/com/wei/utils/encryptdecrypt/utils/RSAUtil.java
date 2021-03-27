package com.wei.utils.encryptdecrypt.utils;

import com.wei.utils.encryptdecrypt.entity.CertEntity;
import com.wei.utils.encryptdecrypt.entity.CertReturnEntity;
import com.wei.utils.encryptdecrypt.entity.KeyEntity;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.misc.NetscapeCertType;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import java.math.BigInteger;
import java.security.*;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月07日
 */
public class RSAUtil extends GMBaseUtil{

    /**
     * 随机生成密钥对
     * @throws NoSuchAlgorithmException
     */
    public static KeyEntity genRSAKeyPair(int keySize) throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(keySize == 0 ? 1024 : keySize,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        /**得到私钥*/
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        /**得到公钥*/
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        KeyEntity entity = new KeyEntity();
        entity.setPrivateKey(privateKey);
        entity.setPublicKey(publicKey);
        return entity;
    }


    public static CertReturnEntity generatorCert(CertEntity entity, X509Certificate x509Certificate, KeyEntity keyE) throws Exception {
        CertReturnEntity cert = new CertReturnEntity();
        X500NameBuilder builder = new X500NameBuilder();
        builder.addRDN(BCStyle.C, entity.getCountyCode())
                .addRDN(BCStyle.O, entity.getCompanyName())
                .addRDN(BCStyle.CN, entity.getCommonName())
                .addRDN(BCStyle.OU, entity.getOrgName());
        if(StringUtils.isNoneBlank(entity.getEmail())){
            builder.addRDN(BCStyle.EmailAddress, entity.getEmail());
        }
        KeyEntity keyEntity = genRSAKeyPair(2048);
        PrivateKey privateKey = keyEntity.getPrivateKey();
        PublicKey publicKey = keyEntity.getPublicKey();
        X509v3CertificateBuilder v3CertificateBuilder = new JcaX509v3CertificateBuilder(
                new X500Name(x509Certificate.getSubjectDN().toString()),
                BigInteger.valueOf(RandomUtils.nextLong(1L, 9223372036854775807L)),
                new Date(),new Date(System.currentTimeMillis() + entity.getEffectiveYears() * 31536000000L),
                builder.build(), publicKey);
        v3CertificateBuilder.addExtension(MiscObjectIdentifiers.netscapeCertType, false, new NetscapeCertType(48));
        X509CertificateHolder holder = v3CertificateBuilder.build(
                new JcaContentSignerBuilder("SHA512WithRSA").setProvider("BC").build(keyE.getPrivateKey()));
        X509Certificate certificate = new JcaX509CertificateConverter().setProvider("BC").getCertificate(holder);
        certificate.checkValidity(new Date());
        certificate.verify(keyE.getPublicKey());
        cert.setPrivateKey(privateKey.getEncoded());
        cert.setPublicKey(certificate.getEncoded());
        cert.setCommonName(entity.getCommonName());
        return cert;
    }
}
