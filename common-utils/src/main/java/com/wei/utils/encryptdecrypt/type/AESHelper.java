package com.wei.utils.encryptdecrypt.type;

import com.wei.utils.encryptdecrypt.entity.MessageEntity;
import com.wei.utils.exception.EncryptOrDecryptException;
import com.wei.utils.encryptdecrypt.type.abs.AbstractEncryptAndDecrypt;
import com.wei.utils.utils.HexConvertUtil;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.PublicKey;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月06日
 */
public class AESHelper extends AbstractEncryptAndDecrypt {

    private static final String ALG = "AES";
    private static final int AES_INIT_LENGTH_128 = 128;
    private static final int AES_INIT_LENGTH_192 = 192;
    private static final int AES_INIT_LENGTH_258 = 258;
    private static final Logger log =  LoggerFactory.getLogger(AESHelper.class);

    @Override
    public String encryptSelectKey(String selectKey, PublicKey key) {
        throw new EncryptOrDecryptException("nonsupport AES encryptSelectKey");
    }

    @Override
    public byte[] decryptSelectKey(MessageEntity msg) {
        throw new EncryptOrDecryptException("nonsupport AES decryptSelectKey");
    }

    @Override
    public MessageEntity encryptData(MessageEntity msg, int secretKeyLength) {
        if(StringUtils.isEmpty(msg.getData())){
            return msg;
        }
        try {
            /**创建AES secretKey*/
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALG);
            if(secretKeyLength != AES_INIT_LENGTH_128 || secretKeyLength != AES_INIT_LENGTH_192
                    || secretKeyLength != AES_INIT_LENGTH_258){
                log.error("AES encryptData secretKeyLength:{} nonsupport", secretKeyLength);
                throw new EncryptOrDecryptException("AES encryptData secretKeyLength nonsupport");
            }
            keyGenerator.init(secretKeyLength);
            SecretKeySpec keySpec = new SecretKeySpec(keyGenerator.generateKey().getEncoded(), ALG);
            /**创建Cipher实例*/
            Cipher cipher = CipherInstance.getInstance();
            IvParameterSpec spec = new IvParameterSpec("0102030405060708".getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, spec);
            byte[] encryptData = cipher.doFinal(msg.getData().getBytes());
            /**转换数据报文为十六进制*/
            msg.setData(HexConvertUtil.parseBy2To16(encryptData));
            msg.setSecretKey(Base64.toBase64String(keySpec.getEncoded()));
            return msg;
        } catch (Exception e) {
            log.error("AES encryptData error", e);
            throw new EncryptOrDecryptException("AES encryptData error", e, e.getMessage());
        }
    }

    @Override
    public MessageEntity encryptData(MessageEntity msg) {
        try {
            return encryptData(msg, AES_INIT_LENGTH_128);
        } catch (Exception e) {
            log.error("AES encryptData error", e);
            throw new EncryptOrDecryptException("AES encryptData error", e, e.getMessage());
        }
    }

    @Override
    public String decryptData(MessageEntity msg, byte[] selectKey) {
        if(msg == null ||  StringUtils.isEmpty(msg.getData()) ||
                selectKey == null || selectKey.length == 0){
            return null;
        }
        try {
            SecretKeySpec keySpec = new SecretKeySpec(selectKey, ALG);
            Cipher cipher = CipherInstance.getInstance();
            IvParameterSpec spec = new IvParameterSpec("0102030405060708".getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, spec);
            byte[] data = cipher.doFinal(HexConvertUtil.parseBy16To2(msg.getData()));
            return new String(data, "UTF-8");
        } catch (Exception e) {
            log.error("AES decryptData error", e);
            throw new EncryptOrDecryptException("AES decryptData error", e, e.getMessage());
        }
    }


    static class CipherInstance{
        private static ThreadLocal<Cipher> local = ThreadLocal.withInitial(() -> {
            try {
                return Cipher.getInstance("AES/CBC/PKCS5Padding");
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new EncryptOrDecryptException("create CipherInstance error");
            }
        });
        public static Cipher getInstance(){
            return local.get();
        }
    }
}
