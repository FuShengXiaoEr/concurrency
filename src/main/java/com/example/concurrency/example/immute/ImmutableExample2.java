package com.example.concurrency.example.immute;

import com.example.concurrency.annoations.ThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

@Slf4j
@ThreadSafe
public class ImmutableExample2 {
    private final static Integer a = 1;
    private final static String b =  "2";
    private static Map<Integer,Integer> map = Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
        map.put(7,8);
        map = Collections.unmodifiableMap(map);
    }
    public static void main(String[] args) {
//        a = 2;
//        b = "d";
//        map = Maps.newHashMap();
        // 对于引用可以修改里面的值
        // 会报错
        map.put(1,0);

    }
}
