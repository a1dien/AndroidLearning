package com.example.sportclub.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.sportclub.R;
import com.example.sportclub.data.ClubContract.MemberEntry;

public class ClubCursorAdapter extends CursorAdapter {

    public ClubCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    //The newView method is used to inflate a new view and return it
    //you don't bind any data to the view at this point
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_sportclub, parent, false);
    }

    //The bindView method is used to bind all data to a given view
    //such as setting the text on a textView
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //Find fields to populate in inflated template
        TextView sportClubID = (TextView)view.findViewById(R.id.tvBody);
        TextView sportClubFirstName = (TextView)view.findViewById(R.id.tvPriority);
        //Extract properties from cursor
        int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        String firstName = cursor.getString(cursor.getColumnIndexOrThrow("firstName"));
        //Populate fields with extracted properties
        sportClubID.setText(id);
        sportClubFirstName.setText(firstName);
    }
}
