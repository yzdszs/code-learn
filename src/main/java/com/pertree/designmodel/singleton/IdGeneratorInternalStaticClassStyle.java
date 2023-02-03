package com.pertree.designmodel.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 内部静态类实现单例模式
 *
 * @author 王跃斌
 * @date 2022/11/4
 */
public class IdGeneratorInternalStaticClassStyle {

    private AtomicLong id = new AtomicLong(0);

    private static class IdGenerator {
        private static final IdGeneratorInternalStaticClassStyle instance = new IdGeneratorInternalStaticClassStyle();
    }

    public IdGeneratorInternalStaticClassStyle getInstance() {
        return IdGenerator.instance;
    }

    public Long getId() {
        return id.incrementAndGet();
    }
}
