package com.pertree.java.base.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @author 王跃斌
 * @date 2023/2/4
 */
public class CarProxy {
    public static void main(String[] args) {
        CarA carA = new CarA();
        Car car = (Car) Proxy.newProxyInstance(Car.class.getClassLoader(), new Class[]{Car.class}, new CarHandler(carA));
        car.getSpeed();

        String prosyClassName = "$Proxy1";
        byte[] proxyClassFile = ProxyGenerator.generateProxyClass(prosyClassName, new Class[]{Car.class});
        try (FileOutputStream fileOutputStream = new FileOutputStream(prosyClassName + ".class")) {
            fileOutputStream.write(proxyClassFile);
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
