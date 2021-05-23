package com.wei.arithmetic.encryptdecrypt.type;

import com.wei.arithmetic.encryptdecrypt.entity.MessageEntity;
import com.wei.arithmetic.encryptdecrypt.type.abs.AbstractEncryptAndDecrypt;
import com.wei.base.exception.EncryptOrDecryptException;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;


/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月06日
 */
public class RSAHelper extends AbstractEncryptAndDecrypt {

    private static final Logger log = LoggerFactory.getLogger(RSAHelper.class);
    private static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
    private static final int MAX_ENCRYPT_BLOCK = 245;
    private static final String ALG = "RSA";

    @Override
    public String encryptSelectKey(String selectKey, PublicKey key) {
        if (StringUtils.isEmpty(selectKey) || key == null) {
            log.error("RSA encryptSelectKey is not null of selectKey:{} or publicKey:{}", selectKey, key);
            throw new EncryptOrDecryptException("RSA encryptSelectKey is not null of selectKey or publicKey");
        }
        try {
            byte[] sKey = Base64.decode(selectKey);
            byte[] encryptText = getSecretKey(key, sKey);
            return Base64.toBase64String(encryptText);
        } catch (Exception e) {
            log.error("RSA encryptSelectKey error", e);
            throw new EncryptOrDecryptException("RSA encryptSelectKey", e, e.getMessage());
        }
    }

    @Override
    public byte[] decryptSelectKey(MessageEntity msg) {
        if (msg == null || msg.isEmpty(msg.getPrivateKey(), msg.getSecretKey())) {
            log.error("RSA decryptSelectKey error");
            throw new EncryptOrDecryptException("RSA decryptSelectKey error");
        }
        try {
            byte[] privateKey = Base64.decode(msg.getPrivateKey());
            byte[] secretKey = Base64.decode(msg.getSecretKey());
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
            KeyFactory keyFactory = KeyFactory.getInstance(ALG);
            PrivateKey gPrivate = keyFactory.generatePrivate(keySpec);
            return getSecretKey(gPrivate, secretKey);
        } catch (Exception e) {
            log.error("RSA decryptSelectKey error", e);
            throw new EncryptOrDecryptException("RSA encryptSelectKey", e, e.getMessage());
        }
    }

    private byte[] getSecretKey(Key key, byte[] secretKey) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            int sKeyLength = secretKey.length;
            int offSet = 0;
            byte[] cache;
            int i = 0;
            while (sKeyLength - offSet > 0) {
                if (sKeyLength - offSet > MAX_ENCRYPT_BLOCK) {
                    cache = cipher.doFinal(secretKey, offSet, MAX_ENCRYPT_BLOCK);
                } else {
                    cache = cipher.doFinal(secretKey, offSet, sKeyLength - offSet);
                }
                out.write(cache, 0, cache.length);
                i++;
                offSet = i * MAX_ENCRYPT_BLOCK;
            }
            return out.toByteArray();
        } catch (Exception e) {
            throw new EncryptOrDecryptException("RSA getSecretKey error", e, e.getMessage());
        }

    }

    @Override
    public MessageEntity encryptData(MessageEntity msg) {
        throw new EncryptOrDecryptException("nonsupport RSA encryptData");
    }

    @Override
    public MessageEntity encryptData(MessageEntity msg, int secretKeyLength) {
        throw new EncryptOrDecryptException("nonsupport RSA encryptData");
    }

    @Override
    public String decryptData(MessageEntity msg, byte[] selectKey) {
        throw new EncryptOrDecryptException("nonsupport RSA encryptData");
    }
}
