package com.example.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CountDownLatchExample {
    private static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executors = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int threadNUm = i;
            executors.execute(()->{
                try{
                    test(threadNUm);
                }catch (Exception e) {
                    log.error("exception:{}",e);
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        log.info("finish");
        executors.shutdown();
    }

    private static void test(int threadNUm) throws InterruptedException {
        Thread.sleep(100);
        log.info("threadnum:{}",threadNUm);
        Thread.sleep(100);
    }
}
