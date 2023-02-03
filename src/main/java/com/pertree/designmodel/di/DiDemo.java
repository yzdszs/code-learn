package com.pertree.designmodel.di;

import static java.lang.System.out;

/**
 * @author 王跃斌
 * @date 2023/1/27
 */
public class DiDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        RateLimiter rateLimiter = (RateLimiter) applicationContext.getBean("rateLimiter");
        RedisCounter redisCounter = (RedisCounter) applicationContext.getBean("redisCounter");
        out.println(redisCounter);
        out.println(rateLimiter.getRedisCounter().getIp());
        out.println(rateLimiter.getRedisCounter().getPassword());
    }
}
