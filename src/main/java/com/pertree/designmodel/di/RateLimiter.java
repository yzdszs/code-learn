package com.pertree.designmodel.di;

/**
 * @author 王跃斌
 * @date 2023/1/27
 */
public class RateLimiter {

    private RedisCounter redisCounter;

    public RateLimiter(RedisCounter redisCounter) {
        this.redisCounter = redisCounter;
    }

    public RedisCounter getRedisCounter() {
        return redisCounter;
    }

    public void setRedisCounter(RedisCounter redisCounter) {
        this.redisCounter = redisCounter;
    }
}
