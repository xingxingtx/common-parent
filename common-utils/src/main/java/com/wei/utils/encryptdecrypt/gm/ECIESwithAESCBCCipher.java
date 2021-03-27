package com.wei.utils.encryptdecrypt.gm;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.agreement.ECDHBasicAgreement;
import org.bouncycastle.crypto.engines.IESEngine;
import org.bouncycastle.crypto.generators.KDF2BytesGenerator;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.paddings.PaddedBufferedBlockCipher;
import org.bouncycastle.crypto.util.DigestFactory;
import org.bouncycastle.jcajce.provider.asymmetric.ec.IESCipher;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月06日
 */
public class ECIESwithAESCBCCipher extends IESCipher {

    public ECIESwithAESCBCCipher(BlockCipher cipher, int iv) {
        super(new IESEngine(new ECDHBasicAgreement(), new KDF2BytesGenerator(DigestFactory.createSHA256()),
                new HMac(DigestFactory.createSHA1()), new PaddedBufferedBlockCipher(cipher)), iv);
    }
}
