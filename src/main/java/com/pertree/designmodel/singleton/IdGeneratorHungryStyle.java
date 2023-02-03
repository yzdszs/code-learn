package com.pertree.designmodel.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 * 单例模式
 * 使用场景：1.处理资源访问冲突、2，表示全局唯一类
 * 例子：Id生成器
 * <p>
 * 饿汉式：在类初始化的时候初始化实例,实例的初始化不存在线程安全问题
 * @author 王跃斌

 */
public class IdGeneratorHungryStyle {

    private static final IdGeneratorHungryStyle instance = new IdGeneratorHungryStyle();

    private AtomicLong id = new AtomicLong(0);

    private IdGeneratorHungryStyle() {}

    public static IdGeneratorHungryStyle getIdGenerator() {
        return instance;
    }

    public Long getId() {
        return id.incrementAndGet();
    }
}
