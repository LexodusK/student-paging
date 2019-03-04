package com.example.firebasetest;

/**
 * Created by ASUS on 23/11/2017.
 */

public class Users {
    String userId;
    String userName;
    String userHpno;
    String studName;
    String studClass;
    String studDorm;
    String studGender;

    public Users(){}

    public Users(String userId, String userName, String userHpno, String studName, String studClass, String studDorm, String studGender) {
        this.userId = userId;
        this.userName = userName;
        this.userHpno = userHpno;
        this.studName = studName;
        this.studClass = studClass;
        this.studDorm = studDorm;
        this.studGender = studGender;
    }



    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserHpno() {
        return userHpno;
    }

    public String getStudName() {
        return studName;
    }

    public String getStudClass() {
        return studClass;
    }

    public String getStudDorm() {
        return studDorm;
    }

    public String getStudGender() {
        return studGender;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public void setStudClass(String studClass) {
        this.studClass = studClass;
    }

    public void setStudDorm(String studDorm) {
        this.studDorm = studDorm;
    }
}
