package com.pertree.designmodel.di;

/**
 * DI 容器执行入口类
 *
 * @author 王跃斌
 */
public interface ApplicationContext {
    Object getBean(String beanId);
}
