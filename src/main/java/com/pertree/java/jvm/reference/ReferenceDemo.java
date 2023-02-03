package com.pertree.java.jvm.reference;

import com.pertree.java.base.proxy.Car;
import com.pertree.java.base.proxy.CarA;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author 王跃斌
 * @date 2023/2/4
 */
public class ReferenceDemo {
    public static void main(String[] args) {
        // m 指向的 SoftReference 是强引用， SoftReference 指向的 byte[] 是软引用
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(m.get());
        // byte不会被回收
        System.gc();
        byte[] b = new byte[1024 *1024 * 15];
        // 指定堆的大小为 20 MB, 如果内存空间不够用的话， 软引用会被回收
        System.out.println(m.get()); //null

        WeakReference<Car> car = new WeakReference<>(new CarA());
        System.out.println(car.get()); //有值
        System.gc();
        System.out.println(car.get()); //null

        ReferenceQueue<Car> queue = new ReferenceQueue<>();
        PhantomReference<Car> phantomReference = new PhantomReference<>(new CarA(), queue);
        System.out.println(phantomReference.get()); //null
    }
}
