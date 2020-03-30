package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.timer.R;

public class MainActivity extends AppCompatActivity {

    private TextView textTimer;
    private Button buttonStart;
    private SeekBar seekBar;
    private boolean status;
    private int time;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textTimer = findViewById(R.id.timer);
        buttonStart = findViewById(R.id.buttonStart);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(600); //maximum time
        time = 30; //default timer
        seekBar.setProgress(time);//minimum time


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

    public void start(View view) {
        if (!status) {
            buttonStart.setText("STOP");
            seekBar.setEnabled(false);
            status = true;
            timer = new CountDownTimer(seekBar.getProgress()*1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    setTimer((int)millisUntilFinished/1000);
                }

                @Override
                public void onFinish() {
                    Log.d("myTimer", "Timer finished.");
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bell_sound);
                    mediaPlayer.start();
                    resetTimer();
                }
            };
            timer.start();
        } else {
            resetTimer();
        }
    }

     private void setTimer(int progress) {
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
    private void resetTimer() {
        timer.cancel();
        buttonStart.setText("START");
        seekBar.setEnabled(true);
        seekBar.setProgress(time);
        setTimer(time);
        status = false;
    }


}
