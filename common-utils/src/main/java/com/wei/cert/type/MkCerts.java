package com.wei.cert.type;

import com.wei.cert.GenerateClientCert;
import com.wei.cert.dto.Entity;
import com.wei.cert.helper.ReadFileHelper;
import com.wei.cert.helper.SysEnvHelper;
import com.wei.cert.helper.SystemConfigHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月23日
 */
public class MkCerts {
    private static final Logger log = LoggerFactory.getLogger(MkCerts.class);

    public MkCerts() {
    }

    public static void main(String[] args) {
        try {
            String issuer = SystemConfigHelper.getProperty("hbcc.tools.cert.input.root");
            log.warn("You are run issue certificaions program:{}", issuer);
            SysEnvHelper.initLog();
            log.info("logback setting up");
            SysEnvHelper.envCheckmkcerts();
            String folder = SystemConfigHelper.getProperty("hbcc.tools.cert.out.certificates");
            log.info("env Checked, going to generate cers under folder:{}", folder);
            File caFolder = new File(folder);
            if (!caFolder.exists()) {
                caFolder.mkdirs();
            }

            GenerateClientCert helper = new GenerateClientCert(folder, issuer);
            helper.loadIssuer(issuer);
            String csrrfolder = SystemConfigHelper.getProperty("hbcc.tools.cert.input.csrs");
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

            log.info("user certificate genertated, pleas check under:{}", SystemConfigHelper.getProperty("hbcc.tools.cert.out.certificates"));
        } catch (Exception var10) {
            log.error(var10.getMessage(), var10);
        }

    }
}
