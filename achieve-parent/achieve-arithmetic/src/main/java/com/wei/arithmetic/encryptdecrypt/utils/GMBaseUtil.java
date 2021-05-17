package com.wei.arithmetic.encryptdecrypt.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月06日
 */
public class GMBaseUtil {
    static {
        if (null == Security.getProvider(BouncyCastleProvider.PROVIDER_NAME)) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }
}
