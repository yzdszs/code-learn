package com.pertree.designmodel.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 懒汉式: 支持延迟加载，但是有性能问题，因为给getInstance加了一把大锁
 *
 * @author 王跃斌
 * @date 2022/11/4
 */
public class IdGeneratorLazyStyle {

    private static IdGeneratorLazyStyle instance;

    private AtomicLong id = new AtomicLong(0);

    private IdGeneratorLazyStyle() {}

    public static synchronized IdGeneratorLazyStyle getInstance() {
        if (instance == null) {
            instance = new IdGeneratorLazyStyle();
        }
        return instance;
    }

    public Long getId() {
        return id.incrementAndGet();
    }
}
