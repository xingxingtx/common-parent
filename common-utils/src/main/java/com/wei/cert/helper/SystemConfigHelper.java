package com.wei.cert.helper;

import com.wei.utils.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年05月23日
 */
public class SystemConfigHelper {
    private static final Logger log = LoggerFactory.getLogger(SystemConfigHelper.class);
    private static Properties configProperties = null;
    private static final String PROPS_FILE_NAME = "cert.properties";
    private static final String OURTER_CONFIG_PATH = "root.dir";

    public SystemConfigHelper() {
    }

    private static String getLocalPath() {
        String rootdir = System.getProperty("root.dir");
        if (rootdir == null || "".equals(rootdir) || "null".equals(rootdir)) {
            rootdir = "";
        }

        return rootdir;
    }

    public static void loadConfig() {
        InputStream innerIs = null;
        InputStream outerIs = null;
        boolean empty = false;
        configProperties = new Properties();

        try {
            log.debug("loadConfig() starts.");
            innerIs = SystemConfigHelper.class.getClassLoader().getResourceAsStream("cert.properties");
            if (innerIs == null) {
                empty = true;
            } else {
                configProperties.load(innerIs);
                empty = false;
            }

            String ourterConfig = getLocalPath();
            if (!StringUtil.isEmpty(ourterConfig)) {
                File f = new File(ourterConfig, "cert.properties");

                try {
                    outerIs = new FileInputStream(f);
                } catch (FileNotFoundException var19) {
                    log.error(var19.getMessage(), var19);
                }

                if (outerIs != null) {
                    configProperties.load(outerIs);
                    empty = false;
                }
            }

            if (empty) {
                log.warn("no properties config found...", new IOException("Properties file: cert.properties is not found!"));
            }
        } catch (Exception var20) {
            log.error(var20.getMessage(), var20);
        } finally {
            try {
                if (innerIs != null) {
                    innerIs.close();
                }
            } catch (IOException var18) {
                log.error(var18.getMessage(), var18);
            }

            try {
                if (outerIs != null) {
                    outerIs.close();
                }
            } catch (IOException var17) {
                log.error(var17.getMessage(), var17);
            }

            log.debug("loadConfig() ends.");
        }

    }

    public static String getProperty(String key) {
        String value = "";
        if (configProperties != null) {
            value = configProperties.getProperty(key);
        }

        return value;
    }

    public static String getProperty(String property, String def) {
        String retVal = null;
        if (configProperties != null) {
            retVal = configProperties.getProperty(property, def).trim();
        }

        if (StringUtil.isEmpty(retVal)) {
            retVal = def;
        }

        return retVal;
    }

    static {
        loadConfig();
    }
}
