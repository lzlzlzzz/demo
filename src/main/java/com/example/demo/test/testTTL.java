package com.example.demo.test;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class testTTL {
    public static void main(String[] args) throws InterruptedException {
        // 创建线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // 线程池经过TtlExecutors工具类包装，返回包装类ExecutorServiceTtlWrapper
        executorService = TtlExecutors.getTtlExecutorService(executorService);
        // 创建需要传递给线程池本地变量
        InheritableThreadLocal<String> username = new InheritableThreadLocal<>();

        // 首次调用,这时候线程池中还未有线程，就算不使用TTL也可以通过InheritableThreadLocal获取到
        // 父线程的本地变量。
        username.set("zhangShang");
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(username.get());
            }
        });

        // 第二次调用，由于使用的是单一线程的线程池，这时候是复用了上面创建的线程，所以这时通过
        // inheritableThreadLocal是获取不到本地变量的。
        Thread.sleep(3000);
        username.set("liSi");
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(username.get());
            }
        });

        Thread.sleep(3000);
        username.set("wangWu");
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(username.get());
            }
        });
    }
}
