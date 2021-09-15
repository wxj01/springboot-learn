package com.wxj.springboot.mybatis.pojo;

/**
 * @author wxj
 * @version 1.0.0
 * @ClassName User.java
 * @Description TODO
 * @createTime 2021年09月15日 19:47:00
 */


public class User {

    private Integer id;
    private String  userName;
    private String  userAge;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userAge='" + userAge + '\'' +
                '}';
    }

    public User() {

    }

    public User(Integer id, String userName, String userAge) {
        this.id = id;
        this.userName = userName;
        this.userAge = userAge;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }
}