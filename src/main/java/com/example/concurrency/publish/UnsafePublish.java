package com.example.concurrency.publish;

import com.example.concurrency.annoations.UnThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 该类可以通过公共getString()方法获取到发布的对象safe
 * 该公共方法可以被其他线程调用，并且随时修改对象safe，因此该类是个线程不安全的类
 */

@Slf4j
@UnThreadSafe
public class UnsafePublish {
    private String[] safe = {"a","b","c","d"};
    public String[] getString(){
        return safe;
    }

    public static void main(String[] args) {
        UnsafePublish publish = new UnsafePublish();
        log.info("{}", Arrays.toString(publish.getString()));

        publish.getString()[0] = "d";
        log.info("{}",Arrays.toString(publish.getString()));
    }
}
