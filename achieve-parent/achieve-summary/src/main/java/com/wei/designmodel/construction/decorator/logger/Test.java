package com.wei.designmodel.construction.decorator.logger;

import org.slf4j.Logger;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.StringReader;

/**
 * @Author wei.peng
 */
public class Test {
//    private static final Logger logger = LoggerFactory.getLogger(Test.class);
    private static final Logger logger = JsonLoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        logger.error("系统错误");


        try {
            InputStream in = new FileInputStream("");

            BufferedInputStream bis = new BufferedInputStream(in);

            bis.read();
            bis.close();

            BufferedReader br = new BufferedReader(new FileReader(""));
            br.readLine();

            BufferedReader bs = new BufferedReader(new StringReader(""));
            bs.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
