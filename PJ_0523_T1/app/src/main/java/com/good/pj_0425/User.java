package com.good.pj_0425;

public class User {

    private String user_key; //키
    private String user_name; //이름
    private String user_cont; //나이

    User(){

    }

    public User(String user_key, String user_name, String user_cont) {
        this.user_key = user_key;
        this.user_name = user_name;
        this.user_cont = user_cont;
    }

    public String getUser_key() {
        return user_key;
    }

    public void setUser_key(String user_key) {
        this.user_key = user_key;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_cont() {
        return user_cont;
    }

    public void setUser_cont(String user_cont) {
        this.user_cont = user_cont;
    }
}