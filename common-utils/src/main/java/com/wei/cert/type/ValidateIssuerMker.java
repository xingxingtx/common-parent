package com.wei.cert.type;

import com.wei.cert.GenerateMedia;
import com.wei.cert.helper.SysEnvHelper;
import com.wei.cert.helper.SystemConfigHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月23日
 */
public class ValidateIssuerMker {
    private static final Logger log = LoggerFactory.getLogger(ValidateIssuerMker.class);

    public ValidateIssuerMker() {
    }

    public static void main(String[] args) {
        log.warn("You are run generating issuer CA program, make sure you have aleady read the associated docs");
        SysEnvHelper.initLog();
        log.info("logback setting up");
        SysEnvHelper.envCheckgenerated();
        String folder = SystemConfigHelper.getProperty("hbcc.tools.validate.cert.output");
        log.info("env Checked, going to generate ca under folder:{}", folder);
        File caFolder = new File(folder);
        if (!caFolder.exists()) {
            caFolder.mkdirs();
        }

        try {
            (new GenerateMedia(folder, SystemConfigHelper.getProperty("hbcc.tools.validate.cert.issuer.input"))).createValidIssuerCert();
            log.info("issuer ca certificate genertated, pleas check under:{}", SystemConfigHelper.getProperty("hbcc.tools.cert.root"));
        } catch (Exception var4) {
            log.error(var4.getMessage(), var4);
        }

    }
}
