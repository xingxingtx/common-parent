package com.wei.cert.helper;


import com.wei.arithmetic.encryptdecrypt.entity.RSAKeyEntity;
import com.wei.arithmetic.encryptdecrypt.utils.CAUtils;
import com.wei.arithmetic.encryptdecrypt.utils.RSAUtils;
import com.wei.cert.dto.Entity;
import com.wei.utils.utils.StringUtil;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月23日
 */
public class CaHelper {
    private static final Logger log = LoggerFactory.getLogger(CaHelper.class);
    private PrivateKey signerPrivateKey;
    private X509Certificate singerCert;
    private String outputPath;

    public CaHelper(String outputPath) {
        this.outputPath = outputPath;
    }

    public void loadIssuer(String configPath) {
        if (StringUtil.isEmpty(configPath)) {
            log.error("No Sign CA Configured  program shutdown");
            System.exit(0);
        }

        try {
            File folder = new File(configPath);
            if (!folder.exists() && !folder.isDirectory()) {
                log.error("No Sign CA Configured  program shutdown");
                System.exit(0);
            }

            File[] files = folder.listFiles();
            if (files == null || files.length != 2) {
                log.error("folder only accept issuer ca and private key file, No Sign CA Configured program shutdown");
                System.exit(0);
            }

            File[] var4 = files;
            int var5 = files.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                File file = var4[var6];
                if (this.singerCert == null && file.getName().endsWith(".cer")) {
                    this.singerCert = CAUtils.generateFromInputStream(new FileInputStream(file));
                    log.debug("singerCert found");
                }

                if (this.signerPrivateKey == null && file.getName().endsWith(".key")) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    String base64edprikey = "";

                    try {
                        base64edprikey = br.readLine();
                    } catch (Exception var15) {
                        log.error(var15.getMessage(), var15);
                    } finally {
                        br.close();
                    }

                    this.signerPrivateKey = RSAUtils.priFromPKCS8End(base64edprikey);
                    log.debug("signerPrivateKey found");
                }
            }
        } catch (Exception var17) {
            log.error(var17.getMessage(), var17);
        }

    }

    public void createClientCert(Entity config) throws Exception {
        X500NameBuilder nameBuilder = new X500NameBuilder();
        nameBuilder.addRDN(BCStyle.C, config.getCountryCode());
        nameBuilder.addRDN(BCStyle.O, config.getCompanyName());
        nameBuilder.addRDN(BCStyle.CN, config.getCommonName());
        nameBuilder.addRDN(BCStyle.OU, config.getOrgName());
        if (!StringUtil.isEmpty(config.getBusinessCategory())) {
            nameBuilder.addRDN(BCStyle.BUSINESS_CATEGORY, config.getBusinessCategory());
        }

        if (!StringUtil.isEmpty(config.getEmail())) {
            nameBuilder.addRDN(BCStyle.EmailAddress, config.getEmail());
        }

     log.debug("generating cert with dn:{}", config);
        RSAKeyEntity keys = RSAUtils.createRSAKeys();
        RSAPrivateKey priKey = keys.getPrivateKey();
        RSAPublicKey pubKey = keys.getPublicKey();
        X509v3CertificateBuilder v3Bldr = new JcaX509v3CertificateBuilder(new X500Name(this.singerCert.getSubjectDN().toString()), BigInteger.valueOf(2L), new Date(), new Date(System.currentTimeMillis() + (long)config.getEffectiveYears() * 31536000000L), nameBuilder.build(), pubKey);
        v3Bldr.addExtension(MiscObjectIdentifiers.netscapeCertType, false, new NetscapeCertType(48));
        X509CertificateHolder certHldr = v3Bldr.build((new JcaContentSignerBuilder("SHA512WithRSA")).setProvider("BC").build(this.signerPrivateKey));
        X509Certificate cert = (new JcaX509CertificateConverter()).setProvider("BC").getCertificate(certHldr);
        cert.checkValidity(new Date());
        cert.verify(this.singerCert.getPublicKey());
        byte[][] client = new byte[][]{cert.getEncoded(), priKey.getEncoded()};
        String tm = this.outputPath + "/" + config.getOrgName();
        File f = new File(tm);
        f.mkdirs();
        FileOutputStream fos = new FileOutputStream(tm + "/" + config.getOrgName() + ".cer");
        fos.write(client[0]);
        fos.close();
        FileOutputStream pfos = new FileOutputStream(tm + "/" + config.getOrgName() + ".key");
        pfos.write(Base64.toBase64String(client[1]).getBytes());
        pfos.close();
    }
}
