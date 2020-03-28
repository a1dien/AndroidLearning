package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textTimer;
    private Button buttonStart;
    private SeekBar seekBar;
    private boolean status;
    private int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textTimer = findViewById(R.id.timer);
        buttonStart = findViewById(R.id.buttonStart);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(600); //maximum time
        seekBar.setProgress(60);//minimum time
        time = 60000; //default timer

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(v);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    public void click(View view) {
        if (status) {
            Log.d("click", String.valueOf(status) + " " + buttonStart.getText());
            buttonStart.setText("START");
            status = false;
        } else {
            Log.d("click", String.valueOf(status) + " " + buttonStart.getText());
            buttonStart.setText("STOP");
            startTimer(view);
            status = true;
        }
    }

    public void startTimer(View view) {
        CountDownTimer timer = new CountDownTimer(seekBar.getProgress()*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setTimer((int)millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                Log.d("myTimer", "Timer finished.");
                textTimer.setText("00:60");
            }
        }.start();
    }
    public void setTimer(int progress) {
        int minutes = progress/60;
        int seconds = progress - (minutes*60);
        String minutesString = "";
        String secondsString = "";
        if (minutes<10) {
            minutesString = "0" + minutes;
        } else {minutesString = String.valueOf(minutes);}
        if (seconds<10) {
            secondsString = "0" + seconds;
        } else {secondsString = String.valueOf(seconds);}
        textTimer.setText(minutesString+":"+secondsString);

    }
}
