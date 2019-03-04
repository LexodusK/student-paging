package com.example.firebasetest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by ASUS on 1/12/2017.
 */

public class reqUsers {

    String studName;
    String studClass;
    String studDorm;
    String studGender;
    String currentTime;

    DatabaseReference databaseUsers;
    @JsonIgnore
    String key;

    public reqUsers(){}


    public reqUsers(String studName, String studClass, String studDorm, String studGender, String dateTime ){
        this.studName = studName;
        this.studClass = studClass;
        this.studDorm = studDorm;
        this.studGender = studGender;
        this.currentTime = dateTime;
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

    public String getCurrentTime() {
        return currentTime;
    }

    public String getStudGender() { return studGender; }

    public String getKey() {
        return key;
    }



    // public String getKeyID() {
  //      return keyID;
  //  }

  //  public void setKeyID(String keyID) {
  //      this.keyID = keyID;
  //  }

   // public void remove(reqUsers users) {
 //       databaseUsers.child(users.getKeyID()).removeValue();
 //   }
}
