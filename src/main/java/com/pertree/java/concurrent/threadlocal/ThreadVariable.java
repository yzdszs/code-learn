package com.pertree.java.concurrent.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * @author 王跃斌
 * @date 2023/2/4
 */
public class ThreadVariable {
    public static void main(String[] args) {

        ThreadLocal<String> threadLocal = new ThreadLocal<>();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadLocal.get()); //get 不到 Car 对象
        }).start();

        new Thread(() -> {
            threadLocal.set("这是我线程独享的变量！");
        }).start();
    }

}
