package com.example.concurrency.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 探究synchronized作用于类
 */
@Slf4j
public class SynchronizedExample2 {
    private static void test1(int index){
        synchronized(SynchronizedExample2.class) {
            for (int i = 0; i < 10; i++) {
                log.info("thread{},test1:{}",index, i);
            }
        }
    }
    private static synchronized void test2(int index){
        for (int i = 0; i < 10; i++) {
            log.info("thread{}, test2:{}",i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample2 example1 = new SynchronizedExample2();
        SynchronizedExample2 example2 = new SynchronizedExample2();
        ExecutorService executorService1 = Executors.newCachedThreadPool();
        ExecutorService executorService2 = Executors.newCachedThreadPool();
        executorService1.execute(() ->{
            example1.test1(1);
        });
        executorService2.execute(() -> {
            example2.test2(2);
        });
    }
}
