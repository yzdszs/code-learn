package com.pertree.designmodel.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 枚举实现单例模式
 *
 * @author 王跃斌
 * @date 2022/11/4
 */
public enum IdGeneratorEnumStyle {
    /**
     * 实例
     */
    INSTANCE;

    private AtomicLong id = new AtomicLong(0);

    public Long getId() {
        return id.incrementAndGet();
    }
}
