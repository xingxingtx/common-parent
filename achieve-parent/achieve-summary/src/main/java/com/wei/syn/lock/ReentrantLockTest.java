package com.wei.syn.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年07月06日
 */
public class ReentrantLockTest {
    private static final Logger log = LoggerFactory.getLogger(ReentrantLockTest.class);
    private static List<Integer> list = new ArrayList<>();
    private static Lock lock = new ReentrantLock();

    public  void addList(int element) {
        lock.lock();
        try {
            log.debug("ThreadName:{} get synchronized, add element:{}", Thread.currentThread().getName(), element);
            list.add(element);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            lock.unlock();
            log.debug("ThreadName:{} get synchronized", Thread.currentThread().getName());

        }
    }

    public void removeList(int element) {
        lock.lock();
        try {
            log.debug("ThreadName:{} get synchronized, remove element:{}", Thread.currentThread().getName(), element);
            list.remove(element);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            lock.unlock();
            log.debug("ThreadName:{} get synchronized", Thread.currentThread().getName());

        }
    }


    public  void addListNo(int element) {
        list.add(element);
    }

    public void removeListNO(int element) {
        list.remove(element);
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ReentrantLockTest test = new ReentrantLockTest();
        test.print();
    }

    private void print() throws InterruptedException, ExecutionException {
        ReentrantLockTest test = new ReentrantLockTest();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,  30,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        for (int i = 0; i < 100; i++) {
            executor.submit(()-> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.addList(1);});
        }
        //executor.shutdown();
        Thread.sleep(60000);
        System.out.println("size=" + list.size());

    }
}
