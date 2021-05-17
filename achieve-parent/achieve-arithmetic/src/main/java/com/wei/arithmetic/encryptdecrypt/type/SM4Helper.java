package com.wei.arithmetic.encryptdecrypt.type;


import com.wei.arithmetic.encryptdecrypt.entity.MessageEntity;
import com.wei.arithmetic.encryptdecrypt.type.abs.AbstractEncryptAndDecrypt;
import com.wei.exception.EncryptOrDecryptException;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.security.SecureRandom;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月06日
 */
public class SM4Helper extends AbstractEncryptAndDecrypt {

    private static final Logger log = LoggerFactory.getLogger(SM4Helper.class);
    private static final int SM4_INIT_LENGTH_128 = 128;
    private static final String CIPHER_ALGORITHM = "SM4/ECB/PKCS5Padding";

    @Override
    public String encryptSelectKey(String selectKey, PublicKey key) {
        throw new EncryptOrDecryptException("nonsupport SM4 encryptSelectKey");
    }

    @Override
    public byte[] decryptSelectKey(MessageEntity msg) {
        throw new EncryptOrDecryptException("nonsupport SM4 decryptSelectKey");
    }

    @Override
    public MessageEntity encryptData(MessageEntity msg) {
        return encryptData(msg, SM4_INIT_LENGTH_128);
    }

    @Override
    public MessageEntity encryptData(MessageEntity msg, int secretKeyLength) {
        if(msg == null || msg.isEmpty(msg.getData())){
            return msg;
        }
        try {
            /**生成密钥*/
            KeyGenerator kg = KeyGenerator.getInstance("SM4","BC");
            kg.init(secretKeyLength, new SecureRandom());
            byte[] bytes = kg.generateKey().getEncoded();
            byte[] dateBytes = msg.getData().getBytes();
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
            SecretKeySpec sm4 = new SecretKeySpec(bytes, "SM4");
            cipher.init(1, sm4);
            byte[] date = cipher.doFinal(dateBytes);
            String secKey = new String(Base64.encode(bytes), StandardCharsets.UTF_8);
            msg.setData(new String(Base64.encode(date), StandardCharsets.UTF_8));
            msg.setSecretKey(secKey);
            return msg;
        } catch (Exception e) {
            log.error("SM4 encryptData error", e);
            throw new EncryptOrDecryptException("SM4 encryptData error", e, e.getMessage());
        }
    }

    @Override
    public String decryptData(MessageEntity msg, byte[] selectKey) {
        if(selectKey == null || selectKey.length == 0 || msg == null || msg.isEmpty(msg.getData())){
            log.warn("SM4 decryptData warn, msg or selectKey is null");
            return msg.getData();
        }
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
            SecretKeySpec sm4 = new SecretKeySpec(selectKey, "SM4");
            cipher.init(2, sm4);
            byte[] data = cipher.doFinal(Base64.decode(msg.getData()));
            String result = new String(data);
            msg.setData(result);
            return result;
        } catch (Exception e) {
            log.error("SM4 decryptData error", e);
            throw new EncryptOrDecryptException("SM4 decryptData error", e, e.getMessage());
        }
    }
}
