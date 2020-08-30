package xin.altitude.factory;

import org.junit.Test;

public class factory {


    @Test
    public void factory() {
        F f = new F();
        f.produce(A2.class).method();
    }

}


/**
 * 抽象产品类接口
 */
interface A {
    public void method();
}

/**
 * 具体实现产品类A1
 */
class A1 implements A {

    public void method() {
        System.out.println("这里是苹果");
    }
}

/**
 * 具体实现产品类A2
 */
class A2 implements A {

    public void method() {
        System.out.println("这里是香蕉");
    }
}

/**
 * 创建生产抽象产品类的工厂类
 */
class F {
    /**
     * 通过反射实现相似类型产品的生产
     */
    <T> T produce(Class<T> clazz){
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}
