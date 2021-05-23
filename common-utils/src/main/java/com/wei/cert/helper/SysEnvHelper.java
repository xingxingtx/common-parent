package com.wei.cert.helper;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import com.wei.utils.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月23日
 */
public class SysEnvHelper {
    private static final Logger log = LoggerFactory.getLogger(SysEnvHelper.class);

    public SysEnvHelper() {
    }

    public static void envCheckgenerated() {
        String generatedCaPath = SystemConfigHelper.getProperty("hbcc.tools.cert.root");
        if (StringUtil.isEmpty(generatedCaPath, true)) {
            log.error("hbcc.tools.cert.root can not be empty");
            System.exit(0);
        }

    }

    public static void envCheckmkcerts() {
        String generatedCaPath = SystemConfigHelper.getProperty("hbcc.tools.cert.input.root");
        if (StringUtil.isEmpty(generatedCaPath, true)) {
            log.error("hbcc.tools.cert.input.root can not be empty");
            System.exit(0);
        }

        String csrs = SystemConfigHelper.getProperty("hbcc.tools.cert.input.csrs");
        if (StringUtil.isEmpty(csrs, true)) {
            log.error("hbcc.tools.cert.input.csrs can not be empty");
            System.exit(0);
        }

        String certOut = SystemConfigHelper.getProperty("hbcc.tools.cert.out.certificates");
        if (StringUtil.isEmpty(certOut, true)) {
            log.error("hbcc.tools.cert.out.certificates can not be empty");
            System.exit(0);
        }

    }

    public static void initLog() {
        LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        lc.reset();

        try {
            configurator.doConfigure(SysEnvHelper.class.getClassLoader().getResourceAsStream("log-back.xml"));
        } catch (Exception var3) {
            log.error(var3.getMessage(), var3);
        }

    }

    public static void output(String destFolder, String fileName, byte[] result) {
        File f = new File(destFolder);
        if (!f.exists() || !f.isDirectory()) {
            log.debug("execute mkdirs cmd {}", destFolder);
            f.mkdirs();
        }

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(destFolder + "/" + fileName);
            fos.write(result);
        } catch (Exception var14) {
            log.error(var14.getMessage(), var14);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException var13) {
                    log.error(var13.getMessage(), var13);
                }
            }

        }

    }
}
