package com.wei.partone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年04月07日
 */
public class ThreadInterrupt implements Runnable{

    private final static Logger log = LoggerFactory.getLogger(ThreadInterrupt.class);

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadInterrupt());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @Override
    public void run() {

        while (!Thread.currentThread().isInterrupted()){
            try {
                log.info("sleep ... ");
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                log.error("Interrupted");
                Thread.currentThread().interrupt();
            }
        }
    }
}
