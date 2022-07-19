package com.example.demo.service;

public interface DataMaskingOperation {

    String MASK_CHAR = "*";
    
    String mask(String content, String maskChar);

}