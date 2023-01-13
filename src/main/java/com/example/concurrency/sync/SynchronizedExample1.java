package com.example.concurrency.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 探究synchronized作用于调用对象
 */
@Slf4j
public class SynchronizedExample1 {
    private void test1(int index){
        synchronized(this) {
            for (int i = 0; i < 10; i++) {
                log.info("thread{},test1:{}",index, i);
            }
        }
    }
    private synchronized void test2(int index){
        for (int i = 0; i < 10; i++) {
            log.info("thread{}, test2:{}",i);
        }
    }

    public static void main(String[] args) {
        // 此时乱序交叉打印
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
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
