package com.example.demo.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//jdk动态代理
public class TestProxy {
    public static void main(String[] args) {
        Person person = new Person();
        Advice advice = new Advice();

        PersonInterface proxy = (PersonInterface) Proxy.newProxyInstance(person.getClass().getClassLoader(), person.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                advice.before();
                method.invoke(person, args);
                advice.after();
                return proxy;
            }
        });
        proxy.say("aaa");
    }
}

//目标类
class Person implements PersonInterface {
    public void say(String a){
        System.out.println("saying............" + a);
    }
}

interface PersonInterface {
    void say(String a);
}

class Advice {
    //前置增强
    public void before(){
        System.out.println("前置增强");
    }

    //后置增强
    public void after(){
        System.out.println("后置增强");
    }
}
