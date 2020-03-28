package com.example.lifecycle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) { //1st
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);

        if(savedInstanceState != null) {
            textView.setText(savedInstanceState.getString("textToBundle"));
        }

        Log.d("LifecycleActivity: ", "onCreate()" );
        textView.append("onCreate()" + "\n");
    }

    @Override
    protected void onStart() {//2nd
        super.onStart();

        Log.d("LifecycleActivity: ", "onStart()" );
        textView.append("onStart()" + "\n");
    }

    @Override
    protected void onResume() {//3rd
        super.onResume();

        Log.d("LifecycleActivity: ", "onResume()" );
        textView.append("onResume()" + "\n");
    }

    @Override
    protected void onPause() { //1st after running
        super.onPause();

        Log.d("LifecycleActivity: ", "onStart()" );
        textView.append("onPause()" + "\n");
    }

    @Override
    protected void onStop() {//2nd after running
        super.onStop();

        Log.d("LifecycleActivity: ", "onStop()" );
        textView.append("onStop()" + "\n");
    }

    @Override
    protected void onDestroy() {//final after running
        super.onDestroy();

        Log.d("LifecycleActivity: ", "onDestroy()" );
        textView.append("onDestroy()" + "\n");
    }

    @Override
    protected void onRestart() {//1st after stop
        super.onRestart();

        Log.d("LifecycleActivity: ", "onRestart()" );
        textView.append("onRestart()" + "\n");
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Log.d("LifecycleActivity: ", "onSaveInstanceState()" );
        textView.append("onSaveInstanceState()" + "\n");
        outState.putString("textToBundle", textView.getText().toString());
    }
}
