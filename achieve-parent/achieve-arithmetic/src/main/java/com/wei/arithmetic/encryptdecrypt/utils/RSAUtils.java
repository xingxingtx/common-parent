package com.wei.arithmetic.encryptdecrypt.utils;

import com.wei.arithmetic.encryptdecrypt.entity.RSAKeyEntity;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月23日
 */
public class RSAUtils {
    private static final Logger log = LoggerFactory.getLogger(RSAUtils.class);
    private static final String alg = "RSA";
    private static final int MAX_DECRYPT_BLOCK = 256;

    public RSAUtils() {
    }

    public static RSAKeyEntity createRSAKeys() {
        Security.addProvider(new BouncyCastleProvider());
        KeyPairGenerator keyPairGen = null;

        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException var5) {
            log.error(var5.getMessage(), var5);
        }

        keyPairGen.initialize(2048, new SecureRandom());
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        RSAKeyEntity keys = new RSAKeyEntity();
        keys.setPrivateKey(privateKey);
        keys.setPublicKey(publicKey);
        return keys;
    }

    public static byte[] encryptPriRSA(RSAPrivateKey prikey, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(1, prikey);
            return cipher.doFinal(data);
        } catch (Exception var4) {
            log.error(var4.getMessage(), var4);
            return null;
        }
    }

    public static byte[] decryptPriRSA(RSAPrivateKey prikey, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(2, prikey);
            int inputLen = data.length;
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int offSet = 0;

            for(int i = 0; inputLen - offSet > 0; offSet = i * 256) {
                byte[] cache;
                if (inputLen - offSet > 256) {
                    cache = cipher.doFinal(data, offSet, 256);
                } else {
                    cache = cipher.doFinal(data, offSet, inputLen - offSet);
                }

                out.write(cache, 0, cache.length);
                ++i;
            }

            byte[] decryptedData = out.toByteArray();
            out.close();
            return decryptedData;
        } catch (Exception var9) {
            var9.printStackTrace();
            log.error(var9.getMessage(), var9);
            return null;
        }
    }

    public static byte[] encryptPubRSA(RSAPublicKey pubkey, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(1, pubkey);
            return cipher.doFinal(data);
        } catch (Exception var4) {
            log.error(var4.getMessage(), var4);
            return null;
        }
    }

    public static byte[] decryptPubRSA(RSAPublicKey pubkey, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(2, pubkey);
            return cipher.doFinal(data);
        } catch (Exception var4) {
            log.error(var4.getMessage(), var4);
            return null;
        }
    }

    public static RSAPrivateKey priFromPKCS8End(String base64edprikey) throws InvalidKeySpecException, NoSuchAlgorithmException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec((new Base64(0)).decode(base64edprikey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey)keyFactory.generatePrivate(keySpec);
    }

    public static RSAPublicKey pubFromPKCS8End(String base64edpubkey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec((new Base64(0)).decode(base64edpubkey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey)keyFactory.generatePublic(keySpec);
    }

    public static String pkcs8Topkcs1(String pkcs8) throws InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        PrivateKey priKey = priFromPKCS8End(pkcs8);
        PrivateKeyInfo pkInfo = PrivateKeyInfo.getInstance(priKey.getEncoded());
        ASN1Encodable encodable = pkInfo.parsePrivateKey();
        ASN1Primitive primitive = encodable.toASN1Primitive();
        byte[] privateKeyPKCS1 = primitive.getEncoded();
        return Base64.encodeBase64String(privateKeyPKCS1);
    }
}
