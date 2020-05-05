package com.example.sound_player_mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    int[] soundIDs = {R.raw.fx1, R.raw.fx2, R.raw.fx3, R.raw.fx4, R.raw.fx5, R.raw.fx6};
    Button soundBtn1, soundBtn2, soundBtn3, soundBtn4, soundBtn5, soundBtn6;
    MediaPlayer mp;

    public void playSound(Context context, int sound) {
        mp = MediaPlayer.create(context, sound);
        mp.start();
        onCompletionReset(mp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        soundBtn1 = (Button)findViewById(R.id.buttonSound1);
        soundBtn2 = (Button)findViewById(R.id.buttonSound2);
        soundBtn3 = (Button)findViewById(R.id.buttonSound3);
        soundBtn4 = (Button)findViewById(R.id.buttonSound4);
        soundBtn5 = (Button)findViewById(R.id.buttonSound5);
        soundBtn6 = (Button)findViewById(R.id.buttonSound6);
    }

    public void playSound(View view) throws Exception {
        Button[] buttons = {soundBtn1, soundBtn2, soundBtn3, soundBtn4, soundBtn5, soundBtn6};

        for (int i = 0; i < soundIDs.length; i++) {
            if (view.getId() == buttons[i].getId()) {
                playSound(this, soundIDs[i]);

                buttons[i].getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
                buttons[i].setEnabled(false);

                for (int j = 0; j < buttons.length; j++) {
                    if (buttons[j] == buttons[i]) {
                        continue;
                    }
                    buttons[j].getBackground().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
                    buttons[j].setEnabled(false);
                }
            }
        }
    }

    public void stopSound(View view) {
        mp.stop();

        Button[] buttons = {soundBtn1, soundBtn2, soundBtn3, soundBtn4, soundBtn5, soundBtn6};
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setEnabled(true);
            buttons[i].getBackground().clearColorFilter();
        }
    }

    public void onCompletionReset(MediaPlayer mp){
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                Button[] buttons = {soundBtn1, soundBtn2, soundBtn3, soundBtn4, soundBtn5, soundBtn6};
                for (int i = 0; i < buttons.length; i++) {
                    buttons[i].setEnabled(true);
                    buttons[i].getBackground().clearColorFilter();
                }
            }
        });
    }
}
