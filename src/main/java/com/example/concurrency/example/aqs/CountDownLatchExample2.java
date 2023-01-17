package com.example.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 这里模拟下如果countdownlatch如果等待超时了会出现什么情况
 * countdownlatch.await()等待的时线程里执行的时间
 */
@Slf4j
public class CountDownLatchExample2 {
    private static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executors = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            // 这里设置等待时间，countdownlatch.await(),不受影响
//            Thread.sleep(100);
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
        countDownLatch.await(100, TimeUnit.MILLISECONDS);
        log.info("finish");
        // 这里的shutdown并不会立马就关闭所有线程，而是等未执行完的线程执行完才关闭
        executors.shutdown();
    }

    private static void test(int threadNUm) throws InterruptedException {
        Thread.sleep(100);
        log.info("threadnum:{}",threadNUm);

    }
}
