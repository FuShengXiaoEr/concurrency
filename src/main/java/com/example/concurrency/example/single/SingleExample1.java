package com.example.concurrency.example.single;

import com.example.concurrency.annoations.UnThreadSafe;

/**
 * 懒汉模式创建对象
 * 单例实例在第一次使用时就创建
 * 该实例创建方式不是线程安全的，在判断实例为null的时候，可能会出现返回多个不同实例
 */
@UnThreadSafe
public class SingleExample1 {
    private SingleExample1(){}

    private static SingleExample1 intance = null;

    public static SingleExample1 getIntance(){
        if (intance == null) {
            return new SingleExample1();
        }
        return intance;
    }
}
