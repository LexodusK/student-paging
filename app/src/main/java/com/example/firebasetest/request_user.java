package com.example.firebasetest;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.service.autofill.Dataset;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 24/11/2017.
 */

public class request_user extends AppCompatActivity implements View.OnClickListener {


    List<reqUsers> usersList;
    List<request_user> test;
    //public Spinner spinnerUser;
    public Spinner sp;
    public TextView mstudclass;
    public TextView mstuddorm;
    public TextView mstudname;
    public TextView mstudgender;
    public Button btnTest;
    public ListView studRequested;
    ArrayAdapter<String> adapter;
    List<String> names = new ArrayList<String>();
    List<String> classes= new ArrayList<String>();
    List<String> dormm = new ArrayList<String>();
    List<String> gender = new ArrayList<>();
    List<String> Time = new ArrayList<String>();
    List<String> reqNames = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");



    NotificationCompat.Builder notification;
    private static final int uniqueID = 12345;
   // public request_user(){}


    DatabaseReference databaseUsers;
    DatabaseReference databasereqUsers;
    Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_name);
        sp = (Spinner) findViewById(R.id.spinnerName);
        //   spinnerUser = findViewById(R.id.spinnerName);
        mstudclass = (TextView) findViewById(R.id.studclass);
        mstuddorm = (TextView) findViewById(R.id.studdorm);
        mstudname = (TextView) findViewById(R.id.mstudname);
        mstudgender = (TextView) findViewById(R.id.mstudgender);
        btnTest = (Button) findViewById(R.id.btnTest);
        studRequested = (ListView) findViewById(R.id.studRequested);




    //    names = new ArrayList<>();
     //   classes = new ArrayList<>();
        // dormm = new ArrayList<>();
       /// req = new ArrayList<>();


        notification = new NotificationCompat.Builder(request_user.this, "");
        notification.setAutoCancel(true);

        btnTest.setOnClickListener(this);

        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        databasereqUsers = FirebaseDatabase.getInstance().getReference("reqUsers");
        helper = new Helper(databaseUsers);

        usersList = new ArrayList<>();
        usersList.clear();

        databaseUsers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot userSnapshot: dataSnapshot.getChildren()){
                    names.add(userSnapshot.child("studName").getValue().toString());
                    classes.add(userSnapshot.child("studClass").getValue().toString());
                    dormm.add(userSnapshot.child("studDorm").getValue().toString());
                    gender.add(userSnapshot.child("studGender").getValue().toString());
                  //  sp.setAdapter(new ArrayAdapter<String>(request_user.this, android.R.layout.simple_list_item_1,usersList));
                        adapter = new ArrayAdapter<String>(request_user.this, android.R.layout.simple_list_item_1, names);
                        sp.setAdapter(adapter);
                }

                //sp.setPrompt("asd");
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databasereqUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot reqUserSnapshot : dataSnapshot.getChildren()) {
                    reqNames.add( reqUserSnapshot.child("studName").getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               // mstudname.setText(names.get(i));
                mstudclass.setText(classes.get(i));
                mstuddorm.setText(dormm.get(i));
                mstudgender.setText(gender.get(i));
                // ArrayList<String> nameS = names;
                //ArrayList<String> classS = classes;
                //ArrayList<String> dormS = dormm;
                // mstudclass.getText();
                //RequestedUser user = new RequestedUser (nameS, classS, dormS);
              //   reqName = mstudname.getText().toString();
               //  reqClass = mstudclass.getText().toString();
               //  reqDorm = mstuddorm.getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }




    public void viewUser(){

        Date d = new Date();


      //  Users testuser = new Users();

        //testuser.studName = sp.getSelectedItem().toString();
      //  testuser.studClass = mstudclass.getText().toString();
        //testuser.studDorm = mstuddorm.getText().toString();
       // usersList.add(testuser);
        final String time_now = sdf.format(d);
        Time.add(time_now);




        databaseUsers = FirebaseDatabase.getInstance().getReference("reqUsers");
        RequestList adapter = new RequestList(request_user.this, usersList);
        studRequested.setAdapter(adapter);

        

        final reqUsers testuser = new reqUsers(sp.getSelectedItem().toString(), mstudclass.getText().toString(), mstuddorm.getText().toString(), mstudgender.getText().toString() , time_now );
       final String requestId = databaseUsers.push().getKey();
        databaseUsers.child(requestId).setValue(testuser);

    }




    @Override
    public void onClick(View view) {
        if (view == btnTest) {

            boolean request = false;

            for (int i = 0; i < reqNames.size(); i++) {
                if (reqNames.get(i).equals(sp.getSelectedItem().toString())) {
                    request = true;
                }
            }

            if (request == true) {
                Toast.makeText(request_user.this, "User is already requested", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Student Requested", Toast.LENGTH_SHORT).show();
                viewUser();


                //startActivity(new Intent(this, request_success.class) );
                //   RequestList adapter = new RequestList(this, classes);


                //  NotificationGenerator.op
                //customizing notification
                notification.setSmallIcon(R.mipmap.ads);
                notification.setTicker("You have requested a student!");
                notification.setWhen(System.currentTimeMillis());
                notification.setContentTitle("You have successfully requested a student!");
                notification.setContentText("Tap to view");

                //when click on notification will bring them to other screen
                Intent intent = new Intent(request_user.this, ViewList.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                notification.setContentIntent(pendingIntent);

                //build notification and issue it
                NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                nm.notify(uniqueID, notification.build());


            }


        }


    }


}




// final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item);
//sp.setAdapter(adapter);
//adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Toast.makeText(this, "going", Toast.LENGTH_SHORT).show();
//ArrayList<String> testt =new ArrayList<String>();// = helper.retrieve();
//adapter.add(( String.valueOf(testt.size())));
//adapter.add(testt);

        /*final List<Users> usersList= new ArrayList<>();
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                usersList.clear();
                for (DataSnapshot usersSnapshot : dataSnapshot.getChildren()) {
                    Users users = usersSnapshot.getValue(Users.class);
                    usersList.add(users);
                }

                UsersList adapter = new UsersList(request_user.this, usersList);
                sp.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        /*databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot data: dataSnapshot.getChildren())
                {
                    Users userss = data.getValue(Users.class);
                  //  testt.add(userss.studName.toString());
                    adapter.add( String.valueOf(dataSnapshot.getChildren()));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        /*usersList = new ArrayList<>();
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                usersList.clear();
                for (DataSnapshot usersSnapshot : dataSnapshot.getChildren()) {
                    Users users = usersSnapshot.getValue(Users.class);
                    usersList.add(users);
                }

                UsersList adapter = new UsersList(  request_user.this, usersList);
                // sp.SpinnerAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

//adapter.add( String.valueOf(usersList.size()));


//adapter.add(testt.get(1).toString());
        /*databaseUsers.child("stateDescriptions").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                //int length = (int)dataSnapshot.getChildrenCount();


                adapter.clear();
                for(int i = 0;i<length;i++) {
                    //adapter.add(dataSnapshot.child(Integer.toString(i)).getValue(String.class));
                    adapter.add(dataSnapshot.getValue(Users.class).toString());
                }
                for (DataSnapshot usersSnapshot : dataSnapshot.getChildren()) {
                    adapter.add("1");
                    //adapter.add(dataSnapshot.getValue(Users.class).toString());
                    //Users users = usersSnapshot.getValue(Users.class);
                    //usersList.add(users);
                }
                // For example, but this sets it back whenever data is added to Firebase
                sp.setSelection(1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // TODO: Implement error handling
            }

        });*/

      /*  usersList = new ArrayList<>();
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                usersList.clear();
                for (DataSnapshot usersSnapshot : dataSnapshot.getChildren()) {
                    Users users = usersSnapshot.getValue(Users.class);
                    usersList.add(users);
                }

                UsersList adapter = new UsersList(  request_user.this, usersList);
               // sp.SpinnerAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
*/

//ArrayList<String> testt = helper.retrieve();
// sp.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testt));//helper.retrieve()));

/*
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                {
                    Object item = adapterView.getItemAtPosition(i);
                    if (item != null) {
                        Toast.makeText(request_user.this, item.toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(request_user.this, "It was null", Toast.LENGTH_SHORT).show();
                    Toast.makeText(request_user.this, "Selected",
                            Toast.LENGTH_SHORT).show();

                    //Toast.makeText(request_user.this, "Please select one", Toast.LENGTH_SHORT).show();
                    //   break;

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "Please select one", Toast.LENGTH_SHORT).show();
            }
        });

*/




/*

    @Override
    protected void onStart() {
        super.onStart();
        sp.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, helper.retrieve()));
        //mstudclass.setText(String.valueOf(testt));

        /*sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Toast.makeText(request_user.this,"whatever",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(request_user.this,"1",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(request_user.this,"2",Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }*/


    /*    databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

//   databaseUsers.addValueEventListener(new ValueEventListener() {
//       @Override
//      public void onDataChange(DataSnapshot dataSnapshot) {




              /*  ///////////////////////////////////////////////////
                req.clear();

                req.add(reqName);
                req.add(reqClass);
                req.add(reqDorm);
                req.add(currentDateTimeString);

                //
                final  ArrayAdapter<String> adp = new ArrayAdapter<String>(request_user.this, android.R.layout.simple_list_item_1, req);
                studRequested.setAdapter(adp);

                    if (req != adp) {
                        adp.add(reqName);
                        adp.add(reqClass);
                        adp.add(reqDorm);
                        adp.add(currentDateTimeString);
                        adp.notifyDataSetChanged();

                    }
                */ //////////////////////////////////////////////////

//     for (DataSnapshot usersSnapshot : dataSnapshot.getChildren()) {
//       Users users = usersSnapshot.getValue(Users.class);
//        usersList.add(users);
//    }


//studRequested.setAdapter(new ArrayAdapter<String>(request_user.this, android.R.layout.simple_list_item_activated_1, req1));
//adapter = new usersList (this, android.R.layout.simple_list_item_1, reqClass);

//   UsersList adapter = new UsersList(request_user.this, usersList);
//   studRequested.setAdapter(adapter);
//      }
//for (DataSnapshot usersSnapshot : dataSnapshot.getChildren()) {
// request_user users = usersSnapshot.getValue(request_user.class);
//  usersList.add(names.toString());
//   usersList.add(mstudclass.toString());


//    names.add(names.toString());
//    classes.add(classes.toString());
//  dormm.add(dormm.toString());
// request_user user = usersSnapshot.getValue(request_user.class);
//   test.add(user);



//studRequested.setAdapter(new ArrayAdapter<String>(request_user.this, android.R.layout.simple_list_item_1, ));


            /* UsersList adapter = new UsersList(request_user.this, usersList);
             studRequested.setAdapter(adapter);
             }*/

//         @Override
//           public void onCancelled(DatabaseError databaseError) {

//          }
//      });