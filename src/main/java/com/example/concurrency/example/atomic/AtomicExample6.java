package com.example.concurrency.example.atomic;

import com.example.concurrency.annoations.ThreadSafe;
import com.example.concurrency.annoations.UnThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@ThreadSafe
public class AtomicExample6 {
    private static AtomicBoolean isUpdate = new AtomicBoolean(false);
    private static int clientTotal = 500000;
    private static int threadTotal = 200;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            service.execute(() ->{
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("error",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        service.shutdown();
        log.info("isUpdate:{}",isUpdate);
    }

    private static void test() {
        if (isUpdate.compareAndSet(false,true)) {
            log.info("isupdate:{}",isUpdate);
        }
    }
}
