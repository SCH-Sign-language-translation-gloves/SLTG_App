package com.good.pj_0425;

public class User {

    private String user_key; //키
    private String suhwa_name; //수화 이름
    private String suhwa_explan; //설명

    User(){

    }

    public User(String user_key, String suhwa_name, String suhwa_explan) {
        this.user_key = user_key;
        this.suhwa_name = suhwa_name;
        this.suhwa_explan = suhwa_explan;
    }

    public String getUser_key() {
        return user_key;
    }

    public void setUser_key(String user_key) {
        this.user_key = user_key;
    }

    public String getSuhwa_name() {
        return suhwa_name;
    }

    public void setSuhwa_name(String suhwa_name) {
        this.suhwa_name = suhwa_name;
    }

    public String getSuhwa_explan() {
        return suhwa_explan;
    }

    public void setSuhwa_explan(String suhwa_explan) {
        this.suhwa_explan = suhwa_explan;
    }
}