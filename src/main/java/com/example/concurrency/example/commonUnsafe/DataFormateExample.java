package com.example.concurrency.example.commonUnsafe;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.logging.SimpleFormatter;

@Slf4j
public class DataFormateExample {
    private static int clientTotal = 5000;
    private static int threadTotal = 200;
    private static SimpleDateFormat format = new SimpleDateFormat();// 不是线程安全的
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            service.execute(() ->{
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("error",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        service.shutdown();
    }

    private static void update() {
        try {
            format.parse("20230105");
        } catch (ParseException e) {
            log.error("update exception",e);
        }
    }

}
