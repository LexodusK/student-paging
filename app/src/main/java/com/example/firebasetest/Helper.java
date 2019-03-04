package com.example.firebasetest;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by ASUS on 23/11/2017.
 */



public class Helper {

    public Helper(){}

   // DatabaseReference databaseUsers;

    public Helper(DatabaseReference databaseUsers) {
    }

    DatabaseReference databaseUsers = FirebaseDatabase.getInstance().getReference();

    public ArrayList<String> retrieve() {
        final ArrayList<String> getStudName = new ArrayList<>();
        databaseUsers.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot, getStudName);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchData(dataSnapshot, getStudName);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return getStudName;
    }


    @NonNull
    public void fetchData(DataSnapshot snapshot,ArrayList<String> studNames){
        studNames.clear();
        for(DataSnapshot ds: snapshot.getChildren())
        {
            String names = ds.getValue(Users.class).getStudName();
            studNames.add(names);

        }

    }
/*
     public void selectUser(){
         AdapterView adapterView = new AdapterView() {
             @Override
             public Adapter getAdapter() {

                 return
             }

             @Override
             public void setAdapter(Adapter adapter) {

             }

             @Override
             public View getSelectedView() {
                 return null;
             }

             @Override
             public void setSelection(int i) {

             }
         };

     }*/



}
