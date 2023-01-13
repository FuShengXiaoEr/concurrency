package com.example.concurrency.example.single;

import com.example.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式创建对象 双重同步锁
 * 解决指令重排-volatile
 */
@ThreadSafe
public class SingleExample5 {
    private SingleExample5(){}
    // volatile + 双重检测
    private volatile static SingleExample5 intance = null;

    // intance = new SingleExample4(); 对象创建时分了三步走
    // 1.memory = allocate() 分配对象的内存空间
    // 2.ctorInstance() 创建对象
    // 3.instance = memory 把内存空间地址赋值给实例

    // jvm 和cpu优化，对指令进行重排序 原有的指令执行顺序可能被修改如下
    // 1.memory = allocate() 分配对象的内存空间
    // 3.instance = memory 把内存空间地址赋值给实例
    // 2.ctorInstance() 创建对象

    public static SingleExample5 getIntance(){
        // 双重检测机制
        if (intance == null) { // 线程B 此时B看到A有内存地址了，不为null，就直接返回实例，此时调用时就会报错，提示对象未创建
            synchronized (SingleExample5.class) {
                // 同步锁
                if (intance == null) {
                    intance = new SingleExample5();  // 线程A 当线程A在执行优化后的创建实例指令时，先走到三步，此时已经拿到内存地址了，但是还没有创建实例
                }
            }
        }
        return intance;
    }
}
