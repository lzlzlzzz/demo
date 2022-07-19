package com.example.demo.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.mail.MailUtil;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.NumberFormat;
import java.util.concurrent.atomic.AtomicInteger;

@Controller
public class DemoController {

    private static final String MAIL_TEMPLATE_1 = "回放完成，本次应回放数据%d条，实际回放数%d条。";
    private static final String MAIL_TEMPLATE_2 = "回放完成，本次应回放数据%d条，实际回放数%d条，相似度小于%s的数据有%d条，介于%s-%s之间的有%d条，大于%s的有%d条。";

    public static void main(String[] args) {
//        MailUtil.send("903335848@qq.com", "总结", "", false);
//        AtomicInteger atomicInteger = new AtomicInteger();
//        atomicInteger.getAndIncrement();
//        System.out.println(atomicInteger.get());

//        double numLower = 0.3;
//        double numUpper = 0.8;
//        String lowerString = NumberFormat.getPercentInstance().format(numLower);
//        String upperString = NumberFormat.getPercentInstance().format(numUpper);
//        String content = "";
//        if (5 == 0){
//            content = String.format(MAIL_TEMPLATE_1, 5, 5);
//        } else {
//            content = String.format(MAIL_TEMPLATE_2, 5, 5, lowerString, 2, lowerString, upperString, 2, upperString, 1);
//        }
//
//        System.out.println(content);

        int a = 1;
        for (;a <= 10;) {
            System.out.println("aaa");
            a++;
        }
    }

    @RequestMapping("/demo")
    @ResponseBody
    public String test(){
        return "hello";
    }
}
