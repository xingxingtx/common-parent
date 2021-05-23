package com.wei.cert;

import com.wei.arithmetic.encryptdecrypt.entity.RSAKeyEntity;
import com.wei.arithmetic.encryptdecrypt.utils.RSAUtils;
import com.wei.cert.dto.Entity;
import com.wei.utils.utils.StringUtil;
import org.apache.commons.lang3.RandomUtils;
import org.bouncycastle.asn1.misc.MiscObjectIdentifiers;
import org.bouncycastle.asn1.misc.NetscapeCertType;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
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
public class GenerateClientCert extends HbccX509CerGenerator {
    private static final Logger log = LoggerFactory.getLogger(GenerateClientCert.class);

    public GenerateClientCert(String issuerOuputFolder, String issuerInputFolder) {
        super(issuerOuputFolder, issuerInputFolder);
    }

    public void generateClient(Entity config) {
        try {
            X500NameBuilder nameBuilder = new X500NameBuilder();
            nameBuilder.addRDN(BCStyle.C, config.getCountryCode());
            nameBuilder.addRDN(BCStyle.O, config.getCompanyName());
            nameBuilder.addRDN(BCStyle.CN, config.getCommonName());
            nameBuilder.addRDN(BCStyle.OU, config.getOrgName());
            if (!StringUtil.isEmpty(config.getBusinessCategory(), true)) {
                nameBuilder.addRDN(BCStyle.BUSINESS_CATEGORY, config.getBusinessCategory());
            }

            if (!StringUtil.isEmpty(config.getEmail(), true)) {
                nameBuilder.addRDN(BCStyle.EmailAddress, config.getEmail());
            }

            log.debug("generating cert with dn:{}", config);
            RSAKeyEntity keys = RSAUtils.createRSAKeys();
            RSAPrivateKey priKey = keys.getPrivateKey();
            RSAPublicKey pubKey = keys.getPublicKey();
            X509v3CertificateBuilder v3Bldr = new JcaX509v3CertificateBuilder(new X500Name(this.issuerCert.getSubjectDN().toString()), BigInteger.valueOf(RandomUtils.nextLong(1L, 9223372036854775807L)), new Date(), new Date(System.currentTimeMillis() + (long)config.getEffectiveYears() * 31536000000L), nameBuilder.build(), pubKey);
            v3Bldr.addExtension(MiscObjectIdentifiers.netscapeCertType, false, new NetscapeCertType(48));
            X509CertificateHolder certHldr = v3Bldr.build((new JcaContentSignerBuilder("SHA512WithRSA")).setProvider("BC").build(this.issuerPriKey));
            X509Certificate cert = (new JcaX509CertificateConverter()).setProvider("BC").getCertificate(certHldr);
            cert.checkValidity(new Date());
            cert.verify(this.issuerCert.getPublicKey());
            byte[][] client = new byte[][]{cert.getEncoded(), priKey.getEncoded()};
            String tm = this.issuerOuputFolder + "/" + config.getCommonName();
            File f = new File(tm);
            f.mkdirs();
            FileOutputStream fos = new FileOutputStream(tm + "/" + config.getCommonName() + ".cer");
            fos.write(client[0]);
            fos.close();
            FileOutputStream pfos = new FileOutputStream(tm + "/" + config.getCommonName() + ".key");
            pfos.write(Base64.toBase64String(client[1]).getBytes());
            pfos.close();
        } catch (Exception var14) {
            log.error(var14.getMessage(), var14);
        }

    }
}
