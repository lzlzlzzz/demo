package com.example.demo.test.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

//Cglib代理
public class TestCglibProxy {
    public static void main(String[] args) {
        Car car = new Car();
        CglibAdvice cglibAdvice = new CglibAdvice();
//        //创建增强器
//        Enhancer enhancer = new Enhancer();
//        //设置目标类
//        enhancer.setSuperclass(car.getClass());
//        //设置回调函数
//        enhancer.setCallback(new MethodInterceptor() {
//            @Override
//            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//                cglibAdvice.before();
//                Object invoke = method.invoke(car, objects);
//                cglibAdvice.after();
//                return invoke;
//            }
//        });
//        //创建代理对象
//        Car proxy = (Car) enhancer.create();
        Car proxy = (Car) cglibAdvice.getProxy(car);
        proxy.runing();
        System.out.println(proxy.getClass().getName());
    }
}

class Car implements CarInterface {

    @Override
    public void runing() {
        System.out.println("Car runing........");
    }
}

interface CarInterface {
    void runing();
}

class CglibAdvice {
    private Enhancer enhancer = new Enhancer();

    public <T> Object getProxy(T t){
        enhancer.setSuperclass(t.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                before();
                Object proxy = method.invoke(t, objects);
                after();
                return proxy;
            }
        });
        Object proxy = enhancer.create();
        return proxy;
    }

    //前置增强
    public void before(){
        System.out.println("前置增强");
    }

    //后置增强
    public void after(){
        System.out.println("后置增强");
    }
}
