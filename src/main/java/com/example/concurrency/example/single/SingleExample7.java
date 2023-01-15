package com.example.concurrency.example.single;

import com.example.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 枚举方式实现单例
 * 比较推荐该方式
 */
@ThreadSafe
@Slf4j
public class SingleExample7 {
    private SingleExample7(){}
    public static SingleExample7 getInstance() {
        return Single.INSTANCE.getInstance();
    }

    private enum Single{
        INSTANCE;
        private SingleExample7  singleton;

        // jvm 保证这个方法绝对只执行一次
        Single(){
            singleton = new SingleExample7();
        }
        public SingleExample7 getInstance(){
            return singleton;
        }
    }
}
