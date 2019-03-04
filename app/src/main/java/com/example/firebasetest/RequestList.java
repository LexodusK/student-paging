package com.example.firebasetest;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 27/11/2017.
 */

public class RequestList extends ArrayAdapter<reqUsers> {

    private Activity context;
    private List<reqUsers> usersList;



    public RequestList(Activity context, List<reqUsers> usersList) {
        super(context, R.layout.list_layout, usersList);
        this.context = context;
        this.usersList = usersList;

    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_layout_extend, null, true);

        TextView textViewStudName = (TextView) listViewItem.findViewById(R.id.textViewStudName);
        TextView textViewStudClass = (TextView) listViewItem.findViewById(R.id.textViewStudClass);
        TextView textViewStudDorm = (TextView) listViewItem.findViewById(R.id.textViewStudDorm);
        TextView textViewStudGender = (TextView) listViewItem.findViewById(R.id.textViewStudGender);
        TextView textViewTime = (TextView) listViewItem.findViewById(R.id.textViewTime);


        reqUsers users = usersList.get(position);

      //  ArrayList<String> arrayList = new ArrayList<String>();

        textViewStudName.setText(users.getStudName());
        textViewStudClass.setText(users.getStudClass());
       textViewStudDorm.setText(users.getStudDorm());
        textViewStudGender.setText(users.getStudGender());
       textViewTime.setText(users.getCurrentTime());


        return listViewItem;

    }
}