package com.study.model;

import lombok.Data;

@Data
public class TestModel {

    private String name;

    private Integer age;

    private Integer sex;

    public void println(String property) {
        System.err.println(property + "属性 :名中了规则");
    }

    public TestModel(String name, Integer age, Integer sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}
