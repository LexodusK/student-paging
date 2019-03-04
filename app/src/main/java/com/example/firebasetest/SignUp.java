package com.example.firebasetest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    Button mBtnSignUp;
    Button mBtnSignIn;
    public EditText mUsername;
    public EditText mPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mBtnSignIn = (Button) findViewById(R.id.btnSignIn);
        mBtnSignUp = (Button) findViewById(R.id.btnSignUp);
        mUsername = (EditText) findViewById(R.id.mUsername);
        mPassword = (EditText) findViewById(R.id.mPassword);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference newUsers = database.getReference("newUsers");

        final Toast usnm = Toast.makeText(SignUp.this, "Username is already taken", Toast.LENGTH_SHORT);
        final Toast signedup = Toast.makeText(SignUp.this, "Successfully Signed Up!!", Toast.LENGTH_SHORT);
        final Toast userpass = Toast.makeText(SignUp.this, "Username or Password cannot be blank", Toast.LENGTH_SHORT);
        usnm.cancel();
        userpass.cancel();
        signedup.cancel();

        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final ProgressDialog mDialog = new ProgressDialog(SignUp.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();
               final String name = mUsername.getText().toString();
                final String pass = mPassword.getText().toString();
                newUsers.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //check if user exists
                        if (!TextUtils.isEmpty(name) && (!TextUtils.isEmpty(pass))) {
                            if (dataSnapshot.child(mUsername.getText().toString()).exists()) {
                                mDialog.dismiss();
                                usnm.show();

                            } else {
                                mDialog.dismiss();
                                NewUser users = new NewUser(mPassword.getText().toString());
                                newUsers.child(mUsername.getText().toString()).setValue(users);
                                signedup.show();
                                finish();
                                startActivity(new Intent(SignUp.this, SignUp.class));
                            }
                        }
                        else {
                            mDialog.dismiss();
                            userpass.show();
                        }

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this, MainActivity.class));
            }
        });


    }
}
