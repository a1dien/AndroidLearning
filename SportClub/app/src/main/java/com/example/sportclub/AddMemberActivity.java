package com.example.sportclub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sportclub.data.ClubContract.MemberEntry;

import java.util.ArrayList;

public class AddMemberActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText sportNameEditText;
    private Spinner genderSpinner;
    private int gender = 0;
    private ArrayAdapter spinnerAdapter;
    //private ArrayList spinnerArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        sportNameEditText = findViewById(R.id.sportNameEditText);
        genderSpinner = findViewById(R.id.genderSpinner);

/*        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("Unknown");
        spinnerArrayList.add("Male");
        spinnerArrayList.add("Female");
        spinnerAdapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, spinnerArrayList);
        Этот способ позволяет только в коде хардкодить выбор, следующий способ через values/arrays
*/
        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.array_gender, R.layout.support_simple_spinner_dropdown_item);
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        genderSpinner.setAdapter(spinnerAdapter);

        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGender = (String)parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selectedGender)) {
                    if (selectedGender.equals("Male")) {
                        gender = MemberEntry.GENDER_MALE;
                    } else if (selectedGender.equals("Female")) {
                        gender = MemberEntry.GENDER_FEMALE;
                    } else {
                        gender = MemberEntry.GENDER_UNKOWN;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                gender = 0;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_member_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveMember:
                insertMember();
                return true;
            case R.id.deleteMember:
                return true;
            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertMember() {
        String firstName = firstNameEditText.getText().toString().trim();
        String lastName =  lastNameEditText.getText().toString().trim();
        String sport = sportNameEditText.getText().toString().trim();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MemberEntry.COLUMN_FIRST_NAME, firstName);
        contentValues.put(MemberEntry.COLUMN_LAST_NAME, lastName);
        contentValues.put(MemberEntry.COLUMN_SPORT, sport);
        contentValues.put(MemberEntry.COLUMN_GENDER, gender);

        ContentResolver contentResolver = getContentResolver();
        Uri uri = contentResolver.insert(MemberEntry.CONTENT_URI, contentValues);

        if (uri == null) {
            Toast.makeText(this, "Insertion of data in the table failed for " + uri, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Data Saved.", Toast.LENGTH_LONG).show();
        }
    }
}
