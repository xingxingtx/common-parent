package com.wei.utils.encryptdecrypt.factory;

import cn.hutool.json.JSONUtil;
import com.wei.utils.encryptdecrypt.constans.EncryptConstans;
import com.wei.utils.encryptdecrypt.entity.MessageEntity;
import com.wei.utils.exception.EncryptOrDecryptException;
import com.wei.utils.encryptdecrypt.type.*;
import com.wei.utils.encryptdecrypt.type.abs.EncryptAndDecrypt;
import com.wei.utils.generate.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.PublicKey;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月07日
 */
public class EncryptAndDecryptFactory {
    private static final Logger log = LoggerFactory.getLogger(EncryptAndDecryptFactory.class);
    private static final Map<String, EncryptAndDecrypt> INSTANCE_TYPE = new ConcurrentHashMap<>();

    public static EncryptAndDecrypt getInstanceType(String type) {
        return INSTANCE_TYPE.get(type);
    }

    static {
        INSTANCE_TYPE.put(EncryptConstans.RSA.name(), new RSAHelper());
        INSTANCE_TYPE.put(EncryptConstans.SM2.name(), new SM2Helper());
        INSTANCE_TYPE.put(EncryptConstans.ECDSA.name(), new ECDSAHelper());
        INSTANCE_TYPE.put(EncryptConstans.AES.name(), new AESHelper());
        INSTANCE_TYPE.put(EncryptConstans.SM4.name(), new SM4Helper());
    }
    private EncryptAndDecryptFactory() {
        throw new RuntimeException("can not create many EncryptAndDecryptFactory instance");
    }
    private static class EncryptAndDecryptFactoryHelper{
        private static final EncryptAndDecryptFactory singleton = new EncryptAndDecryptFactory();
    }

    public static EncryptAndDecryptFactory getInstance(){
        return EncryptAndDecryptFactoryHelper.singleton;
    }
    private Object readResolve(){
        return EncryptAndDecryptFactoryHelper.singleton;
    }

    private static String[] getEncryptType(int algorithm){
        String type = EncryptConstans.getTypeByAlgorithm(algorithm);
        if(StringUtils.isEmpty(type, true)){
            log.error("unKnown algorithm：{}", algorithm);
            throw new EncryptOrDecryptException("unKnown algorithm = " + algorithm);
        }
        String[] types = type.split("_");
        if(types == null || types.length < 2){
            log.error("nonsupport type:{}", type);
            throw new EncryptOrDecryptException("nonsupport type = " + type);
        }
        return types;
    }

    public MessageEntity encryptMessageEntity(MessageEntity message, int algorithm, PublicKey publicKey){
        try {
            /**解析算法类型*/
            String[] type = getEncryptType(algorithm);
            log.info("encryptMessageEntity algorithm:{}, type:{}", algorithm, JSONUtil.toJsonStr(type));
            /**获取加密数据data实例*/
            EncryptAndDecrypt encrypt = INSTANCE_TYPE.get(type[1]);
            /**加密数据data*/
            MessageEntity encryptData = encrypt.encryptData(message);
            String secretKey = encryptData.getSecretKey();
            /**获取 secretKey加密实例*/
            EncryptAndDecrypt encryptKey = INSTANCE_TYPE.get(type[0]);
            /**公钥加密secretKey*/
            String  key = encryptKey.encryptSelectKey(secretKey, publicKey);
            message.setSecretKey(key);
            return message;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new EncryptOrDecryptException(e, e.getMessage());
        }
    }

    public MessageEntity decryptMessageEntity(MessageEntity message, int algorithm){
        try {
            /**解析算法类型*/
            String[] type = getEncryptType(algorithm);
            log.info("encryptMessageEntity algorithm:{}, type:{}", algorithm, JSONUtil.toJsonStr(type));
            /**获取 secretKey解密实例*/
            EncryptAndDecrypt decryptKey = INSTANCE_TYPE.get(type[0]);
            /**解密 secretKey*/
            byte[] selectKey = decryptKey.decryptSelectKey(message);
            /**获取data解密实例*/
            EncryptAndDecrypt decryptData = INSTANCE_TYPE.get(type[1]);
            /**解密 data*/
            String data = decryptData.decryptData(message, selectKey);
            message.setData(data);
            return message;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new EncryptOrDecryptException(e, e.getMessage());
        }

    }
}