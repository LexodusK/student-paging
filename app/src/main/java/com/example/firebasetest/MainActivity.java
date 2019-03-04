package com.example.firebasetest;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.appwidget.AppWidgetProviderInfo;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public Button mBtnSignIn;
    public Button mBtnSignUp;
    public EditText mUsername;
    public EditText mPassword;
    public VideoView vView;
    public static String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mBtnSignIn = (Button) findViewById(R.id.btnSignIn);
        mBtnSignUp = (Button) findViewById(R.id.btnSignUp);
        mUsername = (EditText) findViewById(R.id.mUsername);
        mPassword = (EditText) findViewById(R.id.mPassword);
     final   VideoView vView = (VideoView) findViewById(R.id.videoView);
        vView.requestFocus();
        String vsource = "android.resource://com.example.firebasetest/" + R.raw.ads;
        vView.setVideoURI(Uri.parse(vsource));
        vView.setMediaController(new MediaController(this));
        vView.start();
        vView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                vView.start();
            }
        });


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference newUsers = database.getReference("newUsers");

        final Toast notexist = Toast.makeText(MainActivity.this, "User does not exist", Toast.LENGTH_SHORT);
        final Toast wrongpass =  Toast.makeText(MainActivity.this, "Wrong Password!!", Toast.LENGTH_SHORT);
        final Toast signedin = Toast.makeText(MainActivity.this, "Signed in successfully!", Toast.LENGTH_SHORT);
        final Toast userpass = Toast.makeText(MainActivity.this, "Username or password cannot be blank", Toast.LENGTH_SHORT);
        notexist.cancel();
        wrongpass.cancel();
        signedin.cancel();
        userpass.cancel();

        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                final ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
                mDialog.setMessage("Please wait...");
                mDialog.show();
                final String name = mUsername.getText().toString();
                final String pass = mPassword.getText().toString();

                newUsers.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (!TextUtils.isEmpty(name) && (!TextUtils.isEmpty(pass))) {
                            //check for existing user
                            if (dataSnapshot.child(mUsername.getText().toString()).exists()) {
                                username = mUsername.getText().toString();
                                //get user info
                                mDialog.dismiss();
                                NewUser newUsers = dataSnapshot.child(mUsername.getText().toString()).getValue(NewUser.class);
                                if (newUsers.getPassword().equals(mPassword.getText().toString())) {

                                    signedin.show();
                                    startActivity(new Intent(MainActivity.this, SignIn.class));
                                }
                                else {
                                    mDialog.dismiss();
                                    wrongpass.show();
                                }
                            }
                            else {
                                mDialog.dismiss();
                                notexist.show();
                            }
                        }
                        else
                        {
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


        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
        });

    }



}