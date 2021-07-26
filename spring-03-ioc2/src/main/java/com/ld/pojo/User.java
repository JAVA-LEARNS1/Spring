package com.ld.pojo;

public class User {
    private String name;

    public User(){
        System.out.println("this a user");
    }

    public User(String s) {
        System.out.println(s);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println("name"+name);
    }
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

}
