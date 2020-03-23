package com.example.hellojava;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setChangesInText(View view) {
        int red = Color.parseColor("#FF0000");

        TextView setChangesInText = findViewById(R.id.textViewHello);
        setChangesInText.setText("Hello Java!");
        setChangesInText.setBackgroundColor(red);
        setChangesInText.setTextSize(20);
    }
}
