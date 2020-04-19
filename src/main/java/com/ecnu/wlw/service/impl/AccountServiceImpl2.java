package com.ecnu.wlw.service.impl;

import com.ecnu.wlw.service.AccountService;

import java.util.Date;
public class AccountServiceImpl2 implements AccountService {
    private String name; //字符串
    private Integer age;//基本类型
    private Date birthday;//其他bean类型
    //构造函数

    public void setUserName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void saveAccount(){
        System.out.println("Service中的saveAccount方法执行了"+name+age+birthday);
    }
}
