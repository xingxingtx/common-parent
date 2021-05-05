package com.wei.thread.partone.syn;

import org.springframework.security.core.parameters.P;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年04月10日
 */
public class ProAndConTest {
    public static void main(String[] args) {
        Queue<String>  queue = new LinkedList<>();
        int maxSize = 10;
        Product product = new Product(queue, maxSize);
        Consumer consumer = new Consumer(queue, maxSize);
        Thread thread = new Thread(product, "product");
        Thread thread1 = new Thread(consumer, "consumer");
        thread.start();
        thread1.start();
    }
}
