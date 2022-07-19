package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.demo.annotation.DataMasking;
import com.example.demo.constant.DataMaskingFunc;
import lombok.Data;

@TableName("tb_user")
@Data
public class User {

    @TableField("username")
    private String username;

    @TableField("password")
    @DataMasking(maskFunc = DataMaskingFunc.ALL_MASK)
    private String password;

    @TableField("address")
    private String address;
}
