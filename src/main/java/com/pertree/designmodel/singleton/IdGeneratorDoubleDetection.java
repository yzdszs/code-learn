package com.pertree.designmodel.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 双重检测：懒汉式获取实例时的锁的力度太大，有性能问题，
 * 可以使用双重检测，减小锁的力度
 *
 * @author 王跃斌
 * @date 2022/11/4
 */
public class IdGeneratorDoubleDetection {

    private static IdGeneratorDoubleDetection instance;

    private AtomicLong id = new AtomicLong(0);

    private IdGeneratorDoubleDetection() {}

    public static IdGeneratorDoubleDetection getInstance() {
        if (instance == null) {
            synchronized (IdGeneratorDoubleDetection.class) {
                if (instance == null) {
                    instance = new IdGeneratorDoubleDetection();
                }
            }
        }
        return instance;
    }

    public Long getId() {
        return  id.incrementAndGet();
    }


}
