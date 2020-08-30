package xin.altitude.singleton;


import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * 单例模式及其变种
 */
@Logger
public class singleton {


    @SneakyThrows
    @Test
    public void client() {
        CountDownLatch cdl = new CountDownLatch(10);
        for(int i=0; i<10; i++){
            new Thread(() -> {
                System.out.println(A1.getInstance());
            }, "thread" + i).start();
            cdl.countDown();

        }
        cdl.await();
    }

    @SneakyThrows
    @Test
    public void clientB() {
        CountDownLatch cdl = new CountDownLatch(10);
        for(int i=0; i<10; i++){
            new Thread(() -> {
                System.out.println(B1.getInstance());
            }, "thread" + i).start();
            cdl.countDown();

        }
        cdl.await();
    }
}

/**
 * 饿汉式
 */
class A1 {
    private static A1 instance = new A1();
    private A1() {
    }
    public static A1 getInstance() {
        return instance;
    }
}

/**
 * 懒汉式（双重检查，线程安全）
 */
class B1 {
    private volatile static B1 instance = null;

    private B1() {
    }

    public static B1 getInstance() {
        if (instance == null) {
            synchronized (B1.class) {
                if (instance == null) {
                    instance = new B1();
                }
            }
        }
        return instance;
    }
}
