package com.wei.cert;


import com.wei.arithmetic.encryptdecrypt.utils.CAUtils;
import com.wei.arithmetic.encryptdecrypt.utils.RSAUtils;
import com.wei.utils.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月20日
 */
public class HbccX509CerGenerator {
    private static final Logger log = LoggerFactory.getLogger(HbccX509CerGenerator.class);
    protected X509Certificate issuerCert;
    protected PrivateKey issuerPriKey;
    protected String issuerOuputFolder;
    protected String issuerInputFolder;

    public HbccX509CerGenerator(String issuerOuputFolder, String issuerInputFolder) {
        this.issuerOuputFolder = issuerOuputFolder;
        this.issuerInputFolder = issuerInputFolder;
        this.loadIssuer(issuerInputFolder);
    }

    public void loadIssuer(String issuerIuputFolder) {
        if (StringUtil.isEmpty(issuerIuputFolder, true)) {
            log.error("No Sign CA Configured。。。 program shutdown");
            System.exit(0);
        }

        try {
            File folder = new File(issuerIuputFolder);
            if (!folder.exists() && !folder.isDirectory()) {
                log.error("No Sign CA Configured。。。 program shutdown");
                System.exit(0);
            }

            File[] files = folder.listFiles();
            if (files == null || files.length != 2) {
                log.error("folder only accept issuer ca and private key file, No Sign CA Configured。。。 program shutdown,{}", folder);
                System.exit(0);
            }

            File[] var4 = files;
            int var5 = files.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                File file = var4[var6];
                if (this.issuerCert == null && file.getName().endsWith(".cer")) {
                    this.issuerCert = CAUtils.generateFromInputStream(new FileInputStream(file));
                    log.debug("singerCert found。。。");
                }

                if (this.issuerPriKey == null && file.getName().endsWith(".key")) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    String base64edprikey = "";

                    try {
                        base64edprikey = br.readLine();
                    } catch (Exception var15) {
                        log.error(var15.getMessage(), var15);
                    } finally {
                        br.close();
                    }

                    this.issuerPriKey = RSAUtils.priFromPKCS8End(base64edprikey);
                    log.debug("signerPrivateKey found。。。");
                }
            }
        } catch (Exception var17) {
            log.error(var17.getMessage(), var17);
        }

    }
}