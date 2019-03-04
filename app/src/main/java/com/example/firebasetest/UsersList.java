package com.example.firebasetest;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 23/11/2017.
 */

public class UsersList extends ArrayAdapter<Users>{

    private Activity context;
    private List<Users> usersList;


    public UsersList(Activity context, List<Users> usersList){
        super(context, R.layout.list_layout, usersList);
        this.context = context;
        this.usersList = usersList;
    }



    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
    String currentDateTimeString = sdf.format(d);


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textViewStudName = (TextView) listViewItem.findViewById(R.id.textViewStudName);
        TextView textViewStudClass = (TextView) listViewItem.findViewById(R.id.textViewStudClass);
        TextView textViewStudDorm = (TextView) listViewItem.findViewById(R.id.textViewStudDorm);
        TextView textViewTime = (TextView) listViewItem.findViewById(R.id.textViewTime);

        Users users = usersList.get(position);


        textViewStudName.setText(users.getStudName());
        textViewStudClass.setText(users.getStudClass());
        textViewStudDorm.setText(users.getStudDorm());
        textViewTime.setText(currentDateTimeString);

        return listViewItem;
    }
}
