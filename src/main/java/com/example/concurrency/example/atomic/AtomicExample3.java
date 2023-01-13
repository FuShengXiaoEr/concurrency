package com.example.concurrency.example.atomic;

import com.example.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
@ThreadSafe
public class AtomicExample3 {
    private static int clientTotal = 5000;
    private static int threadTotal = 200;
    private static LongAdder count = new LongAdder();
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

    private static void add() {
        count.increment();
    }
}
