package com.example.concurrency.example.single;

import com.example.concurrency.annoations.ThreadSafe;
import com.example.concurrency.annoations.UnThreadSafe;

/**
 * 饿汉模式创建对象
 * 单例实例在类装栽的时候被创建
 * 饿汉模式的缺点：
 * 在构造中，不能有太多的函数处理，否则可能会导致性能问题
 * 无论我会不会用到这个类，都会创建这样一个实例在内存中，浪费空间资源
 */
@ThreadSafe
public class SingleExample2 {
    private SingleExample2(){}

    private static SingleExample2 intance = new SingleExample2();

    public static SingleExample2 getIntance(){
        return intance;
    }
}
