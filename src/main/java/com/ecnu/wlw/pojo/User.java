package com.ecnu.wlw.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private int id;
    private String name;
    private int age;

    public User(){}
    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

}
