package com.example.firebasetest;

/**
 * Created by ASUS on 5/12/2017.
 */

public class NewUser {

    private String Password;

    public NewUser(){

    }

    public NewUser(String password){
        Password = password;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}
