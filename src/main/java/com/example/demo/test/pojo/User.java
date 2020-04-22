package com.example.demo.test.pojo;

public class User {

    private Integer id;

    private String name;

    private Integer age;

    private String userCode;

    public User(Integer id, String name, Integer age, String userCode) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.userCode = userCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
