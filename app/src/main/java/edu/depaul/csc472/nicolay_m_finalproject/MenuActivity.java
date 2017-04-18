package edu.depaul.csc472.nicolay_m_finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import java.util.ArrayList;

import edu.depaul.csc472.nicolay_m_finalproject.sound.Sound;
import edu.depaul.csc472.nicolay_m_finalproject.sound.SoundList;

public class MenuActivity extends AppCompatActivity{

    private Sound[] sounds = new Sound[9];
    private ArrayList<Sound> allSounds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        sounds[0] = new Sound("Tone", null, R.raw.tone, 0);
        sounds[1] = new Sound("Car Horn", null, R.raw.car_horn, 1);
        sounds[2] = new Sound("Cat Meow", null, R.raw.cat_meow, 2);
        sounds[3] = new Sound("Dog Woof", null, R.raw.dog_woof, 3);
        sounds[4] = new Sound("Doorbell", null, R.raw.doorbell, 4);
        sounds[5] = new Sound("Evil Laugh", null, R.raw.evil_laugh, 5);
        sounds[6] = new Sound("Glass Breaking", null, R.raw.glass_breaking, 6);
        sounds[7] = new Sound("Mario Coin", null, R.raw.mario_coin, 7);
        sounds[8] = new Sound("Quick Fart", null, R.raw.quick_fart, 8);


        for(int i = 0; i < sounds.length; i++)
            if(!allSounds.contains(sounds[i]))
                allSounds.add(sounds[i]);

        //initialize the SoundList
        if(SoundList.SOUNDS.isEmpty())
            SoundList.makeSoundList(allSounds);

        Button soundboard = (Button) findViewById(R.id.mButtonSoundBoard);
        Button config = (Button) findViewById(R.id.mButtonConfig);
        Button file = (Button) findViewById(R.id.mButtonFile);


        soundboard.setOnClickListener((v) -> {
            Intent intent = new Intent(this, SoundboardActivity.class);
            startActivity(intent);
        });

        config.setOnClickListener((v) -> {
            Intent intent = new Intent(this, SoundListActivity.class);
            startActivity(intent);
        });

        file.setOnClickListener((v) -> {
            Intent intent = new Intent(this, FileActivity.class);
            startActivity(intent);
        });
    }


}
