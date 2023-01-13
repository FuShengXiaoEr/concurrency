package com.example.concurrency;

import com.example.concurrency.annoations.UnThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
@UnThreadSafe
public class ConcurrencyTest {
    private static int clientTotal = 500000;
    private static int threadTotal = 200;
    private static int count = 0;
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
        count++;
    }
}
