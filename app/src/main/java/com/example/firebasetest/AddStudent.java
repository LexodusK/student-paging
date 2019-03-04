package com.example.firebasetest;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

//import static com.example.firebasetest.YesNoDialog.message;
//import static com.example.firebasetest.YesNoDialog.title;

public class AddStudent extends AppCompatActivity implements View.OnClickListener{

    public Button mBtnSendData;
    public Button mBtnViewData;
    public EditText mFieldName;
    public EditText mPhone;
    public EditText mStudentName;
    // public EditText mStudentClass;
    //public EditText mStudentDorm;
    // public EditText mStudentGender;
    public Spinner mSpinnerName;
    public Spinner mSpinnerClass;
    public Spinner mSpinnerDorm;
    public Spinner mSpinnerGender;
    public Button mBtnUsers;
    // public EditText mKey;

    //  DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        // ArrayList<String> testt =new ArrayList<>(); testt.add("T1");testt.add("T2");testt.add("T3");testt.add("T4");/////////////////


        // mRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-test-df687.firebaseio.com/");
        // mRef.push();
        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");

        mBtnSendData = findViewById(R.id.sendData);
        mBtnViewData = findViewById(R.id.viewData);
        mFieldName = findViewById(R.id.fieldName);
        mPhone = findViewById(R.id.fieldPhone);
        mStudentName = findViewById(R.id.fieldStudName);
        // mStudentClass = findViewById(R.id.fieldStudClass);
        // mStudentDorm = findViewById(R.id.fieldStudDorm);
        // mStudentGender = findViewById(R.id.fieldStudGender);
        mSpinnerClass = findViewById(R.id.spinnerClass);
        mSpinnerDorm = findViewById(R.id.spinnerDorm);
        mSpinnerGender = findViewById(R.id.spinnerGender);
        mBtnUsers = findViewById(R.id.mBtnUsers);
        // mSpinnerName = (Spinner) findViewById(R.id.spinnerName);

        mBtnViewData.setOnClickListener(this);
        mBtnSendData.setOnClickListener(this);
        mBtnUsers.setOnClickListener(this);
        //  mSpinnerName.setOnItemSelectedListener(this);
//mSpinnerClass.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testt));///////////

        //fixedData();

        // mKey = (EditText) findViewById(R.id.fieldKey);
     /*   mBtnSendData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (view == mBtnSendData) {
                    addData();
                    Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();

                }
                //
                //    String value = mFieldName.getText().toString();
                //    String key = mKey.getText().toString();
                //    DatabaseReference mRefChild = mRef.child(key);
                //    mRefChild.setValue(value);

            }
        });

        mBtnViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (view == mBtnViewData) {
                    startActivity(new Intent(String.valueOf(ViewList.class)));
                }
            }
        });*/
    }





/*
    public void addData() {

        String name = mFieldName.getText().toString();
        String hpno = mPhone.getText().toString();
        String studname = mStudentName.getText().toString();
        String studclass = mSpinnerClass.getSelectedItem().toString();
        String studdorm = mSpinnerDorm.getSelectedItem().toString();
        String studgender = mSpinnerGender.getSelectedItem().toString();


        if(!TextUtils.isEmpty(name)  && !TextUtils.isEmpty(hpno) && !TextUtils.isEmpty(studname)){
            String id = databaseUsers.push().getKey();

            Users users = new Users (id, name, hpno, studname, studclass, studdorm, studgender);

            databaseUsers.child(id).setValue(users);

            Toast.makeText(this,"User added", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, AddStudent.class));
        }

        else{
            Toast.makeText(this,"Enter your details", Toast.LENGTH_SHORT).show();
        }


    }*/


    @Override
    public void onClick(View v) {
        if (v == mBtnViewData) {
            startActivity(new Intent(this, ViewList.class));
        }

        if (v == mBtnSendData) {
           final AlertDialog.Builder builder = new AlertDialog.Builder(AddStudent.this, R.style.MyDialogTheme);

            builder.setTitle("Confirm student information is correct?");
            builder.setMessage("Click OK to continue");
            builder.setCancelable(false);

             final String name = mFieldName.getText().toString();
            final String hpno = mPhone.getText().toString();
            final String studname = mStudentName.getText().toString();
            final  String studclass = mSpinnerClass.getSelectedItem().toString();
            final String studdorm = mSpinnerDorm.getSelectedItem().toString();
            final String studgender = mSpinnerGender.getSelectedItem().toString();


            if(!TextUtils.isEmpty(name)  && !TextUtils.isEmpty(hpno) && !TextUtils.isEmpty(studname)){
               // builder.show();

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(getApplicationContext(), "OK clicked", Toast.LENGTH_SHORT).show();
                        String id = databaseUsers.push().getKey();
                        Users users = new Users (id, name, hpno, studname, studclass, studdorm, studgender);
                        databaseUsers.child(id).setValue(users);
                        Toast.makeText(AddStudent.this,"User added", Toast.LENGTH_LONG).show();
                        finish();
                        //startActivity(new Intent(AddStudent.this, AddStudent.class));
                        // addData();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //  Toast.makeText(getApplicationContext()," Canceled", Toast.LENGTH_SHORT).show();
                        dialogInterface.cancel();
                    }
                });
                final  AlertDialog alert = builder.create();
                alert.show();

            }

            else{
                Toast.makeText(AddStudent.this,"Enter your details", Toast.LENGTH_SHORT).show();
            }










        }

        if (v == mBtnUsers) {
            startActivity(new Intent(this, request_user.class));
        }

    }
}
