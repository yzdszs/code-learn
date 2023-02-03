package com.pertree.java.concurrent.databasepool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * @author 王跃斌
 * @date 2023/1/29
 */
public class ConnectionPool {
    // 用一个双向链表维护连接
    private LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    // 在 mills 毫秒内无法获取到连接。则返回 null
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills < 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long feature = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() || remaining > 0) {
                    pool.wait(remaining);
                    remaining = feature - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }
}
