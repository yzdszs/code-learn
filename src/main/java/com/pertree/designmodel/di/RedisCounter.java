package com.pertree.designmodel.di;

/**
 * @author 王跃斌
 * @date 2023/1/27
 */
public class RedisCounter {
    private String ip;

    private String password;

    public RedisCounter(String ip, String password) {
        this.ip = ip;
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
