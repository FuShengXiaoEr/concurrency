package com.example.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * FutureTask 使用匿名内部类
 */
@Slf4j
public class FutureExample2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        FutureTask<String> future = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                log.info("do something in call()");
                Thread.sleep(5000);
                return "done";
            }
        });
        new Thread(future).start();
        log.info("do something in main");
        Thread.sleep(1000);
        String result = future.get();
        log.info("result:{}",result);
    }
}
