package com.example.firebasetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignIn extends AppCompatActivity implements View.OnClickListener{

    public Button btnAddStudent;
    public Button btnRequestStudent;
    public Button btnViewReqStudent;
    public TextView TxtUsername;
    public Button btnLogout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        btnAddStudent = (Button) findViewById(R.id.btnAddStudent);
        btnRequestStudent = (Button)findViewById(R.id.btnRequestStudent);
        btnViewReqStudent = (Button)findViewById(R.id.btnViewReqStudent);
        TxtUsername = (TextView)findViewById(R.id.TxtUsername);
        btnLogout = (Button) findViewById(R.id.logout);


        btnAddStudent.setOnClickListener(this);
        btnViewReqStudent.setOnClickListener(this);
        btnRequestStudent.setOnClickListener(this);
        btnLogout.setOnClickListener(this);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference newUsers = database.getReference("newUsers");

         TxtUsername.setText("Hello,  " + MainActivity.username);

    }

    @Override
    public void onClick(View view) {
        if (view == btnAddStudent)
        {
            startActivity(new Intent(SignIn.this, AddStudent.class));
        }

        if (view == btnRequestStudent)
        {
            startActivity(new Intent(SignIn.this, request_user.class));
        }

        if (view == btnViewReqStudent)
        {
            startActivity(new Intent(SignIn.this, ViewList.class));
        }
        if (view == btnLogout)
        {
            //startActivity(new Intent(SignIn.this, MainActivity.class));
            finish();
        }


    }
}
