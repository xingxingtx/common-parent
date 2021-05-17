package com.wei.arithmetic.encryptdecrypt.type;


import com.wei.arithmetic.encryptdecrypt.entity.MessageEntity;
import com.wei.arithmetic.encryptdecrypt.type.abs.AbstractEncryptAndDecrypt;
import com.wei.exception.EncryptOrDecryptException;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月06日
 */
public class SM2Helper extends AbstractEncryptAndDecrypt {

    private static final Logger log = LoggerFactory.getLogger(SM2Helper.class);

    @Override
    public String encryptSelectKey(String selectKey, PublicKey key) {
        if (StringUtils.isEmpty(selectKey) || key == null) {
            log.error("SM2 encryptSelectKey error, selectKey:{} or publicKey:{} is null", selectKey, key);
            throw new EncryptOrDecryptException("SM2 encryptSelectKey error, selectKey or publicKey is null");
        }
        try {
            byte[] bytes = Base64.decode(selectKey);
            SM2Engine engine = new SM2Engine();
            ParametersWithRandom pwr = new ParametersWithRandom((ECPublicKeyParameters) key, new SecureRandom());
            engine.init(true, pwr);
            byte[] encryptBytes = engine.processBlock(bytes, 0, bytes.length);
            return new String(Base64.encode(encryptBytes), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("SM2 encryptSelectKey error", e);
            throw new EncryptOrDecryptException("SM2 encryptSelectKey error", e, e.getMessage());
        }
    }

    @Override
    public byte[] decryptSelectKey(MessageEntity msg) {
        if (msg == null || msg.isEmpty(msg.getPrivateKey(), msg.getSecretKey())) {
            log.error("SM2 decryptSelectKey error, msg:{}, privateKey:{}, secretKey:{} is not null", msg, msg.getPrivateKey(),
                    msg.getSecretKey());
            throw new EncryptOrDecryptException("SM2 decryptSelectKey error");
        }
        try {
            byte[] privateKey = Base64.decode(msg.getPrivateKey());
            ByteArrayInputStream bIn = new ByteArrayInputStream(privateKey);
            PemReader pemReader = new PemReader(new InputStreamReader(bIn));
            PemObject pemObject = pemReader.readPemObject();
            privateKey = pemObject.getContent();
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKey);
            KeyFactory keyFactory = KeyFactory.getInstance("EC", "BC");
            BCECPrivateKey gPrivate = (BCECPrivateKey) keyFactory.generatePrivate(spec);
            byte[] secretKey = Base64.decode(msg.getSecretKey());
            ECParameterSpec parameterSpec = gPrivate.getParameters();
            ECDomainParameters domainParameters = new ECDomainParameters(parameterSpec.getCurve(), parameterSpec.getG(),
                    parameterSpec.getN(), parameterSpec.getH());
            ECPrivateKeyParameters keyParameters = new ECPrivateKeyParameters(gPrivate.getD(), domainParameters);
            SM2Engine engine = new SM2Engine();
            engine.init(false, keyParameters);
            return engine.processBlock(secretKey, 0, secretKey.length);
        } catch (Exception e) {
            log.error("SM2 decryptSelectKey error", e);
            throw new EncryptOrDecryptException("SM2 decryptSelectKey error", e, e.getMessage());
        }
    }

    @Override
    public MessageEntity encryptData(MessageEntity msg) {
        throw new EncryptOrDecryptException("nonsupport SM2 encryptData");
    }

    @Override
    public MessageEntity encryptData(MessageEntity msg, int secretKeyLength) {
        throw new EncryptOrDecryptException("nonsupport SM2 encryptData");
    }

    @Override
    public String decryptData(MessageEntity msg, byte[] selectKey) {
        throw new EncryptOrDecryptException("nonsupport SM2 decryptData");
    }
}
