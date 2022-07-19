package com.example.demo.test.proxy;

import org.springframework.context.annotation.Bean;

public class TestStaticProxy {
    public static void main(String[] args) {
        //JDK静态代理
        StaticTarget target = new StaticTarget();
        StaticProxy proxy = new StaticProxy(target);
        proxy.addBook();
    }
}

class StaticTarget implements StaticTargetInterface {

    @Override
    public void addBook() {
        System.out.println("增加一本书成功.......");
    }
}

interface StaticTargetInterface {
    public void addBook();
}

class StaticProxy implements StaticTargetInterface {

    StaticTarget target;

    public StaticProxy(StaticTarget target) {
        this.target = target;
    }

    @Override
    public void addBook() {
        System.out.println("静态代理前置增强");
        target.addBook();
        System.out.println("静态代理后置增强");
    }
}
