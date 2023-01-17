package com.example.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 通过acquire控制并发数
 *
 */
@Slf4j
public class Samaphore {
    private static int threadCount = 20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executors = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(6);
        for (int i = 0; i < threadCount; i++) {
            final int threadNUm = i;
            executors.execute(()->{
                try{
                    semaphore.acquire(3);// 获取许可，这里可以一次获取多个许可
                    test(threadNUm);
                    semaphore.release(3);// 释放许可，也可以一次释放多个许可
                }catch (Exception e) {
                    log.error("exception:{}",e);
                }
            });
        }
        executors.shutdown();
    }

    private static void test(int threadNUm) throws InterruptedException {
        log.info("threadnum:{}",threadNUm);
        Thread.sleep(1000);
    }
}
