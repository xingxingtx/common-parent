package com.wei.cert;




import com.wei.arithmetic.encryptdecrypt.entity.RSAKeyEntity;
import com.wei.arithmetic.encryptdecrypt.utils.RSAUtils;
import com.wei.cert.helper.SysEnvHelper;
import com.wei.cert.helper.SystemConfigHelper;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v1CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v1CertificateBuilder;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月20日
 */
public class GenerateRootIssuer {
    private static final Logger log = LoggerFactory.getLogger(GenerateRootIssuer.class);
    private String isuuerOutputPath;

    public GenerateRootIssuer(String isuuerOutputPath) {
        this.isuuerOutputPath = isuuerOutputPath;
    }

    public void createAcIssuerCert() throws Exception {
        RSAKeyEntity keys = RSAUtils.createRSAKeys();
        RSAPrivateKey privKey = keys.getPrivateKey();
        RSAPublicKey pubKey = keys.getPublicKey();
        StringBuilder sb = new StringBuilder("CN=");
        sb.append(SystemConfigHelper.getProperty("hbcc.tools.cert.cn", "hoperun.com")).append(", C=");
        sb.append(SystemConfigHelper.getProperty("hbcc.tools.cert.country", "CN")).append(", O=");
        sb.append(SystemConfigHelper.getProperty("hbcc.tools.cert.organization", "Hoperun software Crop")).append(", OU=");
        sb.append(SystemConfigHelper.getProperty("hbcc.tools.cert.ou", "Hoperun RDC Digital")).append(", L=");
        sb.append(SystemConfigHelper.getProperty("hbcc.tools.cert.ln", "Jiangsu China"));
        String issuer = sb.toString();
        log.debug("creating root cert with dn:{}", issuer);
        X509v1CertificateBuilder v1Bldr = new JcaX509v1CertificateBuilder(new X500Name(issuer), BigInteger.valueOf(1L), new Date(), new Date(System.currentTimeMillis() + 51840000000L), new X500Name(issuer), pubKey);
        X509CertificateHolder certHldr = v1Bldr.build((new JcaContentSignerBuilder("SHA512WithRSA")).setProvider("BC").build(privKey));
        X509Certificate cert = (new JcaX509CertificateConverter()).setProvider("BC").getCertificate(certHldr);
        cert.checkValidity(new Date());
        cert.verify(pubKey);
        byte[][] result = new byte[][]{cert.getEncoded(), privKey.getEncoded()};
        SysEnvHelper.output(this.isuuerOutputPath, SystemConfigHelper.getProperty("hbcc.tools.cert.organization", "Hoperun software Crop") + ".cer", result[0]);
        SysEnvHelper.output(this.isuuerOutputPath, SystemConfigHelper.getProperty("hbcc.tools.cert.organization", "Hoperun software Crop") + ".key", Base64.toBase64String(result[1]).getBytes());
    }
}
