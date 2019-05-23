package com.jedzer.model;

import java.util.Date;

public class User {
    private long id;
    private String nickname;
    private String email;
    private String password;
    private String registrationDate;
    private boolean isPremium;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    public User(long id, String nickname, String email, String password, String registrationDate, boolean isPremium) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
        this.isPremium = isPremium;
    }
}
