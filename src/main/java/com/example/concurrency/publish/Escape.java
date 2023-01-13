package com.example.concurrency.publish;

import com.example.concurrency.annoations.UnRecommend;
import com.example.concurrency.annoations.UnThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 这里的内部类InnerClass在Escape还未完成初始化之前就对外暴露了Escape的this引用
 *
 */
@Slf4j
@UnThreadSafe
@UnRecommend
public class Escape {
    private int  thisCanBeEscape = 0;
    public Escape(){
        new InnerClass();
    }
    private class InnerClass{
        public InnerClass(){
            log.info("{}",Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
