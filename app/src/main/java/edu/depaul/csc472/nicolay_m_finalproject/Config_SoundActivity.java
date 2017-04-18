package edu.depaul.csc472.nicolay_m_finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import edu.depaul.csc472.nicolay_m_finalproject.sound.Sound;

public class Config_SoundActivity extends Activity {

    private static final int CONFIG_BUTTON_CODE = 300;
    private Sound selection = new Sound();
    private ArrayList<Sound> allSounds = new ArrayList<>();

    private Sound[] sounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config__sound);

        //TODO populate the listview
        ListView listView = (ListView) findViewById(R.id.listView);
        Button cancel = (Button) findViewById(R.id.configButtonCancel);
        Button next = (Button) findViewById(R.id.configButtonNext);
        allSounds = getIntent().getParcelableArrayListExtra("allSounds");
        sounds = (Sound[]) allSounds.toArray();


        cancel.setOnClickListener((v) -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        next.setOnClickListener((v) -> {
            Intent intent = new Intent(this, Config_ButtonActivity.class);
            intent.putExtra("selection", selection);
            startActivityForResult(intent, CONFIG_BUTTON_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == CONFIG_BUTTON_CODE){
            if(resultCode == RESULT_OK && data != null){
                //TODO take data from button_config and then immediately send it back to menu
                Intent sendData = new Intent();
                selection = data.getParcelableExtra("selection");
                allSounds = data.getParcelableArrayListExtra("allSounds");
                sendData.putExtra("Selection", selection);
                sendData.putParcelableArrayListExtra("allSounds", allSounds);
                setResult(RESULT_OK, sendData);
                finish();


            }
        }
    }


}
