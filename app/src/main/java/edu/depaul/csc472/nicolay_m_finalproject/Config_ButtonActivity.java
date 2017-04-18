package edu.depaul.csc472.nicolay_m_finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import java.util.ArrayList;

import edu.depaul.csc472.nicolay_m_finalproject.sound.Sound;

public class Config_ButtonActivity extends Activity {

    private int choice = -1;
    Sound selection = new Sound();
    ArrayList<Sound> allSounds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config__button);

        Intent receiving = getIntent();
        selection = receiving.getParcelableExtra("selection");
        allSounds = receiving.getParcelableArrayListExtra("allSounds");
        int index = allSounds.indexOf(selection);


        RadioGroup buttonChoices = (RadioGroup) findViewById(R.id.configButtonChoices);
        Button back  = (Button) findViewById(R.id.configButtonBack);
        Button finish = (Button) findViewById(R.id.configButtonFinish);

        buttonChoices.setOnCheckedChangeListener((b, isChecked) -> {
            choice = buttonChoices.getCheckedRadioButtonId();
        });

        back.setOnClickListener((v) -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        finish.setOnClickListener((v) -> {
            //TODO send data back
            selection.setButtonNum(getButtonNum(choice));
            allSounds.set(index, selection);
            Intent data = new Intent();
            data.putExtra("selection", selection);
            data.putParcelableArrayListExtra("allSounds", allSounds);
            setResult(RESULT_OK, data);
            finish();
        });


    }

    private int getButtonNum(int choice){
        switch(choice){
            case R.id.configRadio0:
                return 0;
            case R.id.detailRadio1:
                return 1;
            case R.id.configRadio2:
                return 2;
            case R.id.configRadio3:
                return 3;
            case R.id.detailRadio4:
                return 4;
            case R.id.configRadio5:
                return 5;
            case R.id.configRadio6:
                return 6;
            case R.id.detailRadio7:
                return 7;
            case R.id.configRadio8:
                return 8;
            default:
                return -1;
        }
    }
}
