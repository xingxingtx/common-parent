package com.wei.utils.generate;

import com.hoperun.hbcc.cert.cert.GenerateClientCert;
import com.hoperun.hbcc.cert.cert.GenerateMedia;
import com.hoperun.hbcc.cert.utils.Entity;
import com.hoperun.hbcc.cert.utils.ReadFileHelper;
import com.hoperun.hbcc.cert.utils.SysEnv;
import com.hoperun.hbcc.cert.utils.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月19日
 */
public class CertGenerate {

    private static final Logger log = LoggerFactory.getLogger(CertGenerate.class);

    public CertGenerate() {
    }

    public static void main(String[] args) {
        generateRootCert();
        generateCert();
    }


    private static void generateRootCert() {
        log.warn("You are run generating issuer CA program, make sure you have aleady read the associated docs");
        SysEnv.initLog();
        log.info("logback setting up");
        SysEnv.envCheckgenerated();
        String folder = SystemConfig.getProperty("hbcc.tools.validate.cert.output");
        log.info("env Checked, going to generate ca under folder:{}", folder);
        File caFolder = new File(folder);
        if (!caFolder.exists()) {
            caFolder.mkdirs();
        }

        try {
            (new GenerateMedia(folder, SystemConfig.getProperty("hbcc.tools.validate.cert.issuer.input"))).createValidIssuerCert();
            log.info("issuer ca certificate genertated, pleas check under:{}", SystemConfig.getProperty("hbcc.tools.cert.root"));
        } catch (Exception var4) {
            log.error(var4.getMessage(), var4);
        }
    }


    public static void generateCert(){
        try {
            String issuer = SystemConfig.getProperty("hbcc.tools.cert.input.root");
            log.warn("You are run issue certificaions program:{}", issuer);
            SysEnv.initLog();
            log.info("logback setting up");
            SysEnv.envCheckmkcerts();
            String folder = SystemConfig.getProperty("hbcc.tools.cert.out.certificates");
            log.info("env Checked, going to generate cers under folder:{}", folder);
            File caFolder = new File(folder);
            if (!caFolder.exists()) {
                caFolder.mkdirs();
            }

            GenerateClientCert helper = new GenerateClientCert(folder, issuer);
            helper.loadIssuer(issuer);
            String csrrfolder = SystemConfig.getProperty("hbcc.tools.cert.input.csrs");
            ReadFileHelper readFileHelper = new ReadFileHelper(csrrfolder);
            List<Entity> coen = readFileHelper.results;
            if (coen == null) {
                log.error("no valid found under {}", csrrfolder);
                System.exit(-1);
            }

            Iterator var8 = coen.iterator();

            while(var8.hasNext()) {
                Entity config = (Entity)var8.next();
                helper.generateClient(config);
            }

            log.info("user certificate genertated, pleas check under:{}", SystemConfig.getProperty("hbcc.tools.cert.out.certificates"));
        } catch (Exception var10) {
            log.error(var10.getMessage(), var10);
        }
    }
}
