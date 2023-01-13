package com.example.concurrency.example.atomic;

import com.example.concurrency.annoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 关于AtomicIntegerFieldUpdater的使用
 *
 */
@Slf4j
@ThreadSafe
public class AtomicExample5 {
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");
    @Getter
    private volatile int count = 100;
    public static void main(String[] args) {
        AtomicExample5 example5 = new AtomicExample5();
        if (updater.compareAndSet(example5, 100, 200)) {
            log.info("count1:{}", example5.getCount());
        }
        if (updater.compareAndSet(example5, 100, 200)) {
            log.info("count2:{}", example5.getCount());
        } else {
            log.info("update fail");
        }
    }
}
