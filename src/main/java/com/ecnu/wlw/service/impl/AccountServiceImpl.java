package com.ecnu.wlw.service.impl;

import com.ecnu.wlw.service.AccountService;

import java.util.Date;

public class AccountServiceImpl implements AccountService {
    /*如果是经常变化的数据，并不适用于注入的方式赋值
    * 下面的数据只是关注注入的数据类型
    * 演示如果两个类是不经常发生变化的，但彼此之间又有依赖关系
    * 使用spring来维护依赖
    * 请看配置文件来注入
    * */
    private String name; //字符串
    private Integer age;//基本类型
    private Date birthday;//其他bean类型
    //构造函数
    public AccountServiceImpl(String name,Integer ageee,Date birthday){
        this.name = name;
        this.age = ageee;
        this.birthday = birthday;

    }
    public void saveAccount(){
        System.out.println("Service中的saveAccount方法执行了"+name+age+birthday);
    }
}
