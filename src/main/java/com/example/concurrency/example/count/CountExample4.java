package com.example.concurrency.example.count;

import com.example.concurrency.annoations.ThreadSafe;
import com.example.concurrency.annoations.UnThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 使用synchronized保证线程安全
 */
@Slf4j
@UnThreadSafe
public class CountExample4 {
    private static int clientTotal = 5000;
    private static int threadTotal = 200;
    private static volatile int count = 0;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            service.execute(() ->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("error",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        service.shutdown();
        log.info("count:{}",count);
    }

    /**
     * count++分了三步走
     * 先取缓存值
     * 然后加1
     * 再写入缓存
     * 当多个线程发生时，会存在同时获取到相同缓存值然后进行加1操作
     * 这就会导致总数上稍加1
     */
    private static void add() {
        count++;
    }
}
