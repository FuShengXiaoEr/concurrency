package com.example.concurrency.example.single;

import com.example.concurrency.annoations.ThreadSafe;
import com.example.concurrency.annoations.UnThreadSafe;

/**
 * 懒汉模式创建对象
 * 单例实例在第一次使用时就创建
 */
@ThreadSafe
public class SingleExample3 {
    private SingleExample3(){}

    private static SingleExample3 intance = null;

    /**
     * 加上synchronized，但是会带来性能问题
     * @return
     */
    public static synchronized SingleExample3 getIntance(){
        if (intance == null) {
            return new SingleExample3();
        }
        return intance;
    }
}
