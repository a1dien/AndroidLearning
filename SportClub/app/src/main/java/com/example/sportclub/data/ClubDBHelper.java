package com.example.sportclub.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sportclub.data.ClubContract.MemberEntry;


public class ClubDBHelper extends SQLiteOpenHelper {
    public ClubDBHelper(@Nullable Context context) {
        super(context, ClubContract.DATABASE_NAME, null, ClubContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SPORT_TABLE = "CREATE TABLE " + MemberEntry.TABLE_NAME + "("
                + MemberEntry._ID + " INTEGER PRIMARY KEY, "
                + MemberEntry.COLUMN_FIRST_NAME + " TEXT, "
                + MemberEntry.COLUMN_LAST_NAME + " TEXT, "
                + MemberEntry.COLUMN_GENDER + " INTEGER NOT NULL, "
                + MemberEntry.COLUMN_SPORT + " TEXT" + ")"
                ;
        db.execSQL(CREATE_SPORT_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + MemberEntry.TABLE_NAME);
        onCreate(db);
    }
}
