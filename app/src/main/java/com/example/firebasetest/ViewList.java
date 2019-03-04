package com.example.firebasetest;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

//import com.firebase.ui.database.FirebaseListAdapter;
//import com.google.common.base.Strings;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewList extends AppCompatActivity{

    DatabaseReference databaseUsers ;
    RequestList adapter;
    String keyyy;
    //private ListView mListView;
  //  private GridView mGridView;

    List<reqUsers> usersList;

  //  public ArrayList<String> mUsername = new ArrayList<>();
 //   DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
    ListView ListViewUsers;

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(ViewList.this, "Hold to delete your request", Toast.LENGTH_LONG).show();
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                usersList.clear();
                for (DataSnapshot usersSnapshot : dataSnapshot.getChildren()) {
                    reqUsers users = usersSnapshot.getValue(reqUsers.class);
                    keyyy = usersSnapshot.getKey();
                    // Toast.makeText(ViewList.this, keyyy, Toast.LENGTH_SHORT).show();
                    users.key=keyyy;
                    usersList.add(users);
                }

                //RequestList adapter = new RequestList(ViewList.this, usersList);
                adapter = new RequestList(ViewList.this, usersList);
                ListViewUsers.setAdapter(adapter);
                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list);
        databaseUsers = FirebaseDatabase.getInstance().getReference("reqUsers");


       // WebView myWebView = (WebView) findViewById(R.id.webview);
     //   myWebView.loadUrl("https://fir-test-df687.firebaseapp.com");



                                                       ListViewUsers = (ListView) findViewById(R.id.listViewUsers);

                                                        usersList = new ArrayList<>();







        ListViewUsers.setLongClickable(true);
        ListViewUsers.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {


                Toast.makeText(ViewList.this, "Deleted " + usersList.get(position).getStudName().toString() + " from requests", Toast.LENGTH_LONG).show();
             //   removeItemFromPosition(position);
               databaseUsers.child(usersList.get(position).getKey()).removeValue();
              // usersList.remove(position);
                // usersList.get(i)
            //    String position = Integer.toString(i);
               // usersList.remove(i);

           // String key = ListViewUsers.get(i);
            // final String id = databaseUsers.child("studName").getKey();
               //databaseUsers.child(id).removeValue();
               // databaseUsers.child("").removeValue();
               //databaseUsers.child("-L-MDRD-6-mveUZkh5Jh").removeValue();

/*
                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ViewList.this);
                dialogBuilder.setTitle("Please Don't Kill Me!");
                dialogBuilder.setPositiveButton("Kill Me...", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Delete node
                       // Log.d(TAG, "NUM of NODES: " + listAdapter.mNodes.size());
                        reqUsers pos = usersList.get(i);
                       // Log.i(TAG, "Generated Key: " + mKey);
                        String selectedKey = databaseUsers.getKey();
                      //  Log.i(TAG, "Selected Key: " + selectedKey);
                        mNodeRef.child(selectedKey).removeValue();
                        listAdapter.mNodes.remove(pos);
                        listAdapter.notifyDataSetChanged();
                        main_ListView.setAdapter(listAdapter);
                        Toast.makeText(getApplicationContext(), "Node has been murdered terribly."
                                , Toast.LENGTH_SHORT).show();
                    }
                }).
                        setNegativeButton("Don't Kill Me!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Fine, ya coward."
                                        , Toast.LENGTH_SHORT).show();
                            }
                        });

                dialogBuilder.create().show();
            /*
            Toast.makeText(getApplicationContext(),"Item at: " + position,Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), ""  +listAdapter.mNodes.size(), Toast.LENGTH_SHORT).show();*/



              //  usersList.remove(i);
              //  usersList.get(i).getKey()
                return true;
            }
        });

        databaseUsers.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                for(reqUsers rq : usersList)
                {
                    if (key.equals(rq.getKey())){
                        usersList.remove(rq);
                        adapter.notifyDataSetChanged();
                       // ViewList.this.notifyAll();
                        break;
                    }
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
/*
        public void removeItemFromPosition(int position) {

            final int deletePosition = position;
            AlertDialog.Builder alert = new AlertDialog.Builder(
                    ViewList.this);
            alert.setTitle("Delete");
            alert.setMessage("Do you want delete this post?");

            alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    usersList.remove(deletePosition);
                    //   Log.d(TAG, "Messages: " + messages);
                    //   mMessageAdapter.notifyDataSetChanged();
                    //   mMessageAdapter.notifyDataSetInvalidated();
                }
            });

            alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alert.show();
        }



     /*   ListViewUsers.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Toast.makeText(ViewList.this, "Delete?" , Toast.LENGTH_SHORT).show();
                return true;
            }
        });*/



      //  final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mUsername);

      //  mListView.setAdapter(arrayAdapter);
  /*      mGridView.setAdapter(arrayAdapter);
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                mUsername.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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
        });*/
    }
}
