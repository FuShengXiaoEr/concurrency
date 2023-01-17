package com.example.concurrency.example.commonUnsafe;

import com.example.concurrency.annoations.UnThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@UnThreadSafe
public class ArrayListExample {
    private static int clientTotal = 5000;
    private static int threadTotal = 200;
    private static ArrayList<Integer> arrayList = new ArrayList<>();
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            service.execute(() ->{
                try {
                    semaphore.acquire();
                    add(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("error",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        service.shutdown();
        log.info("count:{}",arrayList.size());
    }

    private static void add(int i) {
        arrayList.add(i);
    }
}
