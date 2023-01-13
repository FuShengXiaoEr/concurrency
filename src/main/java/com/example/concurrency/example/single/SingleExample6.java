package com.example.concurrency.example.single;

import com.example.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 饿汉模式创建对象
 * 单例实例在类装栽的时候被创建
 * 饿汉模式的缺点：
 * 在构造中，不能有太多的函数处理，否则可能会导致性能问题
 * 无论我会不会用到这个类，都会创建这样一个实例在内存中，浪费空间资源
 */
@ThreadSafe
@Slf4j
public class SingleExample6 {
    private SingleExample6(){}
    private static SingleExample6 intance = null;

    /**
     * 静态代码块
     *
     */
    static{
        intance = new SingleExample6();
    }


    /**
     * 静态代码方法
     * @return
     */
    public static SingleExample6 getIntance(){
        return intance;
    }

    public static void main(String[] args) {
        log.info("{}",intance.hashCode());
        log.info("{}",intance.hashCode());
    }
}
