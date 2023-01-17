package com.example.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * 在CyclicBarrier中设置Runnable，当线程执行到屏障的时候，优先执行Runnable
 */
@Slf4j
public class CylicBarrierExample3 {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()->{
        log.info("excute cyclicbarrier");
    });

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            service.execute(()->{
                try {
                    race(threadNum);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        service.shutdown();
    }

    public static void race(int threadNum) throws InterruptedException, BrokenBarrierException {
        Thread.sleep(1000);
        log.info("threadNUm:{}", threadNum);
        try {
            cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            // 这里包含await()会抛出的异常
            throw new RuntimeException(e);
        }
        log.info("continue:{}",threadNum);

    }
}
