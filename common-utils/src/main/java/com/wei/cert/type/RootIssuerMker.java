package com.wei.cert.type;

import com.wei.cert.GenerateRootIssuer;
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
public class RootIssuerMker {

    private static final Logger log = LoggerFactory.getLogger(RootIssuerMker.class);

    public RootIssuerMker() {
    }

    public static void main(String[] args) {
        log.warn("You are run generating Singer CA program, make sure you have aleady read the associated docs");
        SysEnvHelper.initLog();
        log.info("logback setting up");
        SysEnvHelper.envCheckgenerated();
        String folder = SystemConfigHelper.getProperty("hbcc.tools.cert.root");
        log.info("env Checked, going to generate ca under folder:{}", folder);
        File caFolder = new File(folder);
        if (!caFolder.exists()) {
            caFolder.mkdirs();
        }

        try {
            (new GenerateRootIssuer(folder)).createAcIssuerCert();
            log.info("root ca certificate genertated, pleas check under:{}", folder);
        } catch (Exception var4) {
            log.error(var4.getMessage(), var4);
        }

    }
}
