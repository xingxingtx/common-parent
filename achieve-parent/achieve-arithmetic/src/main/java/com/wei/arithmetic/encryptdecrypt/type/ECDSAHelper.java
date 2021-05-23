package com.wei.arithmetic.encryptdecrypt.type;


import com.wei.arithmetic.encryptdecrypt.entity.MessageEntity;
import com.wei.arithmetic.encryptdecrypt.gm.ECIESwithAESCBCCipher;
import com.wei.arithmetic.encryptdecrypt.type.abs.AbstractEncryptAndDecrypt;
import com.wei.base.exception.EncryptOrDecryptException;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.crypto.agreement.ECDHBasicAgreement;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.engines.IESEngine;
import org.bouncycastle.crypto.generators.KDF2BytesGenerator;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.modes.CBCBlockCipher;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher;
import org.bouncycastle.jce.spec.IESParameterSpec;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月06日
 */
public class ECDSAHelper extends AbstractEncryptAndDecrypt {

    private static final Logger log = LoggerFactory.getLogger(ECDSAHelper.class);
    private static final byte[] NONCE = Hex.decode("000102030405060708090a0b0c0d0e0f");
    @Override
    public String encryptSelectKey(String selectKey, PublicKey key) {
        try {
            byte[] skey = Base64.decode(selectKey);
            IESCipher iesCipher = new IESCipher(new IESEngine(new ECDHBasicAgreement(),
                    new KDF2BytesGenerator(DigestFactory.createSHA256()),
                    new HMac(DigestFactory.createSHA1()),
                    new PaddedBufferedBlockCipher(new CBCBlockCipher(new AESEngine()))), 16);
            IESParameterSpec spec = new IESParameterSpec(null, null, 128, 128, NONCE);
            iesCipher.engineInit(Cipher.ENCRYPT_MODE, key, spec, new SecureRandom());
            return new String(Base64.encode(iesCipher.engineDoFinal(skey, 0, skey.length)), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("ECDSA encryptSelectKey error", e);
            throw new EncryptOrDecryptException("ECDSA encryptSelectKey error", e, e.getMessage());
        }
    }

    @Override
    public byte[] decryptSelectKey(MessageEntity msg) {
        if (msg == null || StringUtils.isEmpty(msg.getPrivateKey())) {
            log.error("decryptSelectKey of ECDSA msg or privateKey is not null");
            throw new EncryptOrDecryptException("decryptSelectKey of ECDSA msg or privateKey is not null");
        }
        try {
            PKCS8EncodedKeySpec peks = new PKCS8EncodedKeySpec(Base64.decode(msg.getPrivateKey()));
            KeyFactory kf = KeyFactory.getInstance("EC", "BC");
            PrivateKey privateKey = kf.generatePrivate(peks);
            IESCipher iesCipher = new ECIESwithAESCBCCipher(new CBCBlockCipher(new AESEngine()), 16);
            IESParameterSpec spec = new IESParameterSpec(null, null, 128, 128, NONCE);
            iesCipher.engineInit(2, privateKey, spec, new SecureRandom());
            byte[] seKey = Base64.decode(msg.getSecretKey());
            return iesCipher.engineDoFinal(seKey, 0, seKey.length);
        } catch (Exception e) {
            log.error("ECDSA decryptSelectKey error", e);
            throw new EncryptOrDecryptException("ECDSA decryptSelectKey error", e, e.getMessage());
        }
    }

    @Override
    public MessageEntity encryptData(MessageEntity msg) {
        throw new EncryptOrDecryptException("nonsupport ECDSA encryptData");
    }

    @Override
    public MessageEntity encryptData(MessageEntity msg, int secretKeyLength) {
        throw new EncryptOrDecryptException("nonsupport ECDSA encryptData");
    }

    @Override
    public String decryptData(MessageEntity msg, byte[] selectKey) {
        throw new EncryptOrDecryptException("nonsupport ECDSA decryptData");
    }
}
