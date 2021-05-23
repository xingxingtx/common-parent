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
public  class GenerateMedia extends HbccX509CerGenerator {
    private static final Logger log = LoggerFactory.getLogger(GenerateMedia.class);

    public GenerateMedia(String issuerOuputFolder, String issuerInputFolder) {
        super(issuerOuputFolder, issuerInputFolder);
    }

    public void createValidIssuerCert() throws Exception {
        StringBuilder sb = new StringBuilder("CN=");
        sb.append(SystemConfigHelper.getProperty("hbcc.tools.validate.cert.cn", "validate.hoperun.com")).append(", C=");
        sb.append(SystemConfigHelper.getProperty("hbcc.tools.validate.cert.country", "CN")).append(", O=");
        sb.append(SystemConfigHelper.getProperty("hbcc.tools.validate.cert.organization", "Hoperun software Crop")).append(", OU=");
        sb.append(SystemConfigHelper.getProperty("hbcc.tools.validate.cert.ou", "Hoperun RD Certificate")).append(", L=");
        sb.append(SystemConfigHelper.getProperty("hbcc.tools.validate.cert.ln", "Jiangsu China"));
        String subject = sb.toString();
        log.debug("create validate issuer certificate with subject dn:{} and issuer dn:{}", subject, this.issuerCert.getSubjectDN().toString());
        RSAKeyEntity keys = RSAUtils.createRSAKeys();
        RSAPrivateKey privKey = keys.getPrivateKey();
        RSAPublicKey pubKey = keys.getPublicKey();
        X509v1CertificateBuilder v1Bldr = new JcaX509v1CertificateBuilder(new X500Name(this.issuerCert.getSubjectDN().toString()), BigInteger.valueOf(1L), new Date(), new Date(System.currentTimeMillis() + 2592000000L), new X500Name(subject), pubKey);
        X509CertificateHolder certHldr = v1Bldr.build((new JcaContentSignerBuilder("SHA512WithRSA")).setProvider("BC").build(this.issuerPriKey));
        X509Certificate cert = (new JcaX509CertificateConverter()).setProvider("BC").getCertificate(certHldr);
        cert.checkValidity(new Date());
        cert.verify(this.issuerCert.getPublicKey());
        SysEnvHelper.output(this.issuerOuputFolder, SystemConfigHelper.getProperty("hbcc.tools.validate.cert.organization", "Hoperun software Crop") + ".cer", cert.getEncoded());
        SysEnvHelper.output(this.issuerOuputFolder, SystemConfigHelper.getProperty("hbcc.tools.validate.cert.organization", "Hoperun software Crop") + ".key", Base64.toBase64String(privKey.getEncoded()).getBytes());
    }

    public String getIssuerInputFolder() {
        return this.issuerInputFolder;
    }

    public void setIssuerInputFolder(String issuerInputFolder) {
        this.issuerInputFolder = issuerInputFolder;
    }
}