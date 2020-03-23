package com.example.tomjerry;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ImageView iconPlayPause;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.original);
        iconPlayPause = findViewById(R.id.play);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(mediaPlayer.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
        }, 0, 1000);
    }

    public void play(View View) {
        Log.d("isPlay", String.valueOf(mediaPlayer.isPlaying()));
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            iconPlayPause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
        } else {
            mediaPlayer.start();
            iconPlayPause.setImageResource(R.drawable.ic_pause_black_24dp);
        }

    }

    public void previous(View view) {
        seekBar.setProgress(0);
        mediaPlayer.seekTo(0);
        mediaPlayer.pause();
        iconPlayPause.setImageResource(R.drawable.ic_play_arrow_black_24dp);

    }

    public void next(View view) {
        seekBar.setProgress(mediaPlayer.getDuration());
        mediaPlayer.seekTo(mediaPlayer.getDuration());
        mediaPlayer.pause();
        iconPlayPause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
    }
/*
    public void changePicture(View view) {
        if (check == 0) {
            ImageView tomImage = findViewById(R.id.tom);
            tomImage.animate().alpha(0).rotation(tomImage.getRotation() + 3600).scaleX(0.0f).scaleY(0.0f).setDuration(3000);
            ImageView jerryImage = findViewById(R.id.jerry);
            jerryImage.animate().alpha(1).rotation(jerryImage.getRotation() + 3600).scaleX(1.0f).scaleY(1.0f).setDuration(3000);
            check++;
        } else if (check == 1) {
            ImageView jerryImage = findViewById(R.id.jerry);
            jerryImage.animate().alpha(0).scaleX(0.0f).scaleY(0.0f).rotation(jerryImage.getRotation() + 3600).setDuration(3000);
            ImageView tomImage = findViewById(R.id.tom);
            tomImage.animate().alpha(1).rotation(tomImage.getRotation() + 3600).scaleX(1.0f).scaleY(1.0f).setDuration(3000);
            check--;
        }


        mediaPlayer = MediaPlayer.create(this, R.raw.original);
        audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        volumeSeekBar = findViewById(R.id.seekBar);
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void play(View view) {
        TextView button = findViewById(R.id.play);
        if (!isPlay) {
            button.setText("Pause");
            mediaPlayer.start();
            isPlay = true;
        } else if (isPlay) {
            button.setText("Play");
            mediaPlayer.pause();
            isPlay = false;
        }
    }

    public void previous(View view) {
    }
    }*/


}
