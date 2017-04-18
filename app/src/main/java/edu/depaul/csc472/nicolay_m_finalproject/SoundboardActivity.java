package edu.depaul.csc472.nicolay_m_finalproject;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import edu.depaul.csc472.nicolay_m_finalproject.sound.Sound;
import edu.depaul.csc472.nicolay_m_finalproject.sound.SoundList;

public class SoundboardActivity extends Activity {

    private Sound[] sounds = new Sound[9];
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private ArrayList<Sound> allSounds = new ArrayList<>();
    private static final String TAG = "SoundboardActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundboard);



    }

    @Override
    protected void onStart(){
        super.onStart();
        for(Sound s : SoundList.SOUNDS){
            if(s.getButtonNum() < 9)
                sounds[s.getButtonNum()] = s;
        }

        /**
         * Button layout:
         * 0 1 2
         * 3 4 5
         * 6 7 8
         */
        Button[] button = new Button[9];
        button[0] = (Button) findViewById(R.id.sbButton0);
        button[1] = (Button) findViewById(R.id.sbButton1);
        button[2] = (Button) findViewById(R.id.sbButton2);
        button[3] = (Button) findViewById(R.id.sbButton3);
        button[4] = (Button) findViewById(R.id.sbButton4);
        button[5] = (Button) findViewById(R.id.sbButton5);
        button[6] = (Button) findViewById(R.id.sbButton6);
        button[7] = (Button) findViewById(R.id.sbButton7);
        button[8] = (Button) findViewById(R.id.sbButton8);
        Button menu = (Button) findViewById(R.id.sbButtonMenu);

        button[0].setSoundEffectsEnabled(false);
        button[1].setSoundEffectsEnabled(false);
        button[2].setSoundEffectsEnabled(false);
        button[3].setSoundEffectsEnabled(false);
        button[4].setSoundEffectsEnabled(false);
        button[5].setSoundEffectsEnabled(false);
        button[6].setSoundEffectsEnabled(false);
        button[7].setSoundEffectsEnabled(false);
        button[8].setSoundEffectsEnabled(false);




        menu.setOnClickListener((v) -> {
            mediaPlayer.release();
            finish();
        });

        for(int i = 0; i < button.length; i++){
            final int fi = i;
            button[i].setOnClickListener((v) -> setSound(fi));
        }
//
//        button0.setOnClickListener((v) -> {
//            setSound(mediaPlayer, 0);
//        });
//
//        button1.setOnClickListener((v) -> {
//            setSound(mediaPlayer, 1);
//
//        });
//
//        button2.setOnClickListener((v) -> {
//            setSound(mediaPlayer, 2);
//
//        });
//
//        button3.setOnClickListener((v) -> {
//            //TODO
//            setSound(mediaPlayer, 3);
//
//        });
//
//        button4.setOnClickListener((v) -> {
//            //TODO
//            setSound(mediaPlayer, 4);
//
//        });
//
//        button5.setOnClickListener((v) -> {
//            //TODO
//            setSound(mediaPlayer, 5);
//
//        });
//
//        button6.setOnClickListener((v) -> {
//            //TODO
//            setSound(mediaPlayer, 6);
//
//        });
//
//        button7.setOnClickListener((v) -> {
//            //TODO
//            setSound(mediaPlayer, 7);
//
//        });
//
//        button8.setOnClickListener((v) -> {
//            //TODO
//            setSound(mediaPlayer, 8);
//
//        });
    }

    private void setSound(int buttonNum){
        // TODO implement
        if(sounds[buttonNum] != null) {
            if (sounds[buttonNum].getFilePath() == null)
                mediaPlayer = MediaPlayer.create(getApplicationContext(), sounds[buttonNum].getResourceID());
            else {
                mediaPlayer = MediaPlayer.create(getApplicationContext(), sounds[buttonNum].getFilePath());
            }
            if(mediaPlayer != null)
                mediaPlayer.start();
        }
        else
            Toast.makeText(this, "No sound assigned.", Toast.LENGTH_SHORT).show();



    }



}
