package com.example.sportclub;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sportclub.data.ClubCursorAdapter;
import com.example.sportclub.data.ClubDBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.example.sportclub.data.ClubContract.MemberEntry;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    //TextView dataTextView;
    ListView dataListView;
    private static final int MEMBER_LOADER=123;
    private CursorAdapter memberCursorAdapter;


    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dataTextView = findViewById(R.id.dataTextView);
        dataListView = findViewById(R.id.dataListView);

        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMemberActivity.class);
                startActivity(intent);
            }
        });
        memberCursorAdapter = new ClubCursorAdapter(this, null);
        dataListView.setAdapter(memberCursorAdapter);

        dataListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, AddMemberActivity.class);
                Uri currentMemberUri = ContentUris.withAppendedId(MemberEntry.CONTENT_URI, id);
                intent.setData(currentMemberUri);
                startActivity(intent);
            }
        });

        getSupportLoaderManager().initLoader(MEMBER_LOADER, null, this);
    }
/*
    @Override
    protected void onStart() {
        super.onStart();

        displayData();
    }
*/
/* All gone to CursorLoader
    private void displayData()  {
        String[] projection = {
                MemberEntry._ID,
                MemberEntry.COLUMN_FIRST_NAME,
                MemberEntry.COLUMN_LAST_NAME,
                MemberEntry.COLUMN_GENDER,
                MemberEntry.COLUMN_SPORT
        };
        Cursor cursor = getContentResolver().query(
                MemberEntry.CONTENT_URI,
                projection,
                null,
                null,
                null
                );

        ClubCursorAdapter adapter = new ClubCursorAdapter(this, cursor);
        dataListView.setAdapter(adapter);
        //adapter.changeCursor(cursor);

        /*
        dataTextView.setText("All Members\n\n");
        dataTextView.append(
                MemberEntry._ID + " " +
                MemberEntry.COLUMN_FIRST_NAME + " " +
                MemberEntry.COLUMN_LAST_NAME + " " +
                MemberEntry.COLUMN_GENDER + " " +
                MemberEntry.COLUMN_SPORT);
        int idColumnIndex = cursor.getColumnIndex(MemberEntry._ID);
        int FirstNameColumnIndex = cursor.getColumnIndex(MemberEntry.COLUMN_FIRST_NAME);
        int LastNameColumnIndex = cursor.getColumnIndex(MemberEntry.COLUMN_LAST_NAME);
        int GenderColumnIndex = cursor.getColumnIndex(MemberEntry.COLUMN_GENDER);
        int SportColumnIndex = cursor.getColumnIndex(MemberEntry.COLUMN_SPORT);

        while (cursor.moveToNext()) {
            int currentId = cursor.getInt(idColumnIndex);
            String currentFirstName = cursor.getString(FirstNameColumnIndex);
            String currentLastName = cursor.getString(LastNameColumnIndex);
            int currentGender = cursor.getInt(GenderColumnIndex);
            String currentSport = cursor.getString(SportColumnIndex);

            dataTextView.append("\n" + currentId + " " + currentFirstName + " " + currentLastName + " " + currentGender + " " + currentSport);
        }
        cursor.close();

    }
        */

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection = {
                MemberEntry._ID,
                MemberEntry.COLUMN_FIRST_NAME,
                MemberEntry.COLUMN_LAST_NAME,
                MemberEntry.COLUMN_SPORT
        };
        CursorLoader cursorLoader = new CursorLoader(this,
                MemberEntry.CONTENT_URI,
                projection,
                null,
                null,
                null
        );
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        memberCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        memberCursorAdapter.swapCursor(null);
    }
}
