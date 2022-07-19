package com.example.demo.test;

import com.example.demo.util.HttpUtil;

public class TestHttpUtil {
    public static void main(String[] args) {
        try {
            String s = HttpUtil.get("http://127.0.0.1:8088/joyApi/v1.0/adv/getList", null, 0, 0);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
