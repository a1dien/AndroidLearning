package com.example.timer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timer.R;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    private TextView textTimer;
    private Button buttonStart;
    private SeekBar seekBar;
    private boolean status;
    private int time;
    private CountDownTimer timer;
    private int defaultInterval;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        textTimer = findViewById(R.id.timer);
        buttonStart = findViewById(R.id.buttonStart);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(600); //maximum time
        setIntervaFromSharedPreferemces(sharedPreferences);
/*        time = 30; //default timer
        seekBar.setProgress(time);//minimum time */



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

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
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
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                    if (sharedPreferences.getBoolean("enable_sound", true)) {
                        String melodyName = sharedPreferences.getString("timer_melody", "bell");
                        if (melodyName.equals("bell")) {
                            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bell_sound);
                            mediaPlayer.start();
                        } else if (melodyName.equals("alarm_siren")) {
                            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.alarm_siren_sound);
                            mediaPlayer.start();
                        }  else if (melodyName.equals("beep")) {
                            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bip_sound);
                            mediaPlayer.start();
                        }

                    }
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
        status = false;
        setIntervaFromSharedPreferemces(sharedPreferences);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.timer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent openSettings = new Intent(this, SettingsActivity.class);
            startActivity(openSettings);
            return true;
        } else if (id == R.id.action_about) {
            Intent openAbout = new Intent(this, AboutActivity.class);
            startActivity(openAbout);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void setIntervaFromSharedPreferemces(SharedPreferences sharedPreferemces) {
        defaultInterval = Integer.valueOf(sharedPreferemces.getString("default_interval", "30"));
        setTimer(defaultInterval);
        seekBar.setProgress(defaultInterval);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("default_interval")) {
            setIntervaFromSharedPreferemces(sharedPreferences);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }
}
