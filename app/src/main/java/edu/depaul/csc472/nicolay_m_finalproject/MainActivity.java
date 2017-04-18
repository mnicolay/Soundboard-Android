package edu.depaul.csc472.nicolay_m_finalproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.depaul.csc472.nicolay_m_finalproject.sound.Sound;
import edu.depaul.csc472.nicolay_m_finalproject.sound.SoundList;

public class MainActivity extends Activity {

    private Sound[] sounds = new Sound[9];
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private ArrayList<Sound> allSounds = new ArrayList<>();
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }

    @Override
    protected void onStart(){
        super.onStart();



        Intent intent = getIntent();
        allSounds = intent.getParcelableArrayListExtra("allSounds");
//        Bundle extras = intent.getExtras();
//        if(extras != null)
//            sounds[0] = new Sound(extras.getString("label 0"),
//                    extras.getString("filepath 0"),
//                    extras.getInt("resourceID 0", -1),
//                    extras.getInt("buttonNum 0", -1));
//        for(int i = 0; i < sounds.length; i++) {
//            sounds[i] = new Sound(intent.getStringExtra("label " + i),
//                    intent.getStringExtra("filepath " + i),
//                    intent.getIntExtra("resourceID " + i, -1),
//                    intent.getIntExtra("buttonNum " + i, -1));
//            sounds[i].setLabel();
//            sounds[i].setFilePath());
//            sounds[i].setResourceID();
//            sounds[i].setButtonNum();
//        }

//        sounds[0] = intent.getParcelableExtra("Sound 0");

//        readFromFile(getApplicationContext());
        for(Sound s : allSounds){
            if(s.getButtonNum() < 9)
                sounds[s.getButtonNum()] = s;
        }

        /**
         * Button layout:
         * 0 1 2
         * 3 4 5
         * 6 7 8
         */
        Button button0 = (Button) findViewById(R.id.sbButton0);
        Button button1 = (Button) findViewById(R.id.sbButton1);
        Button button2 = (Button) findViewById(R.id.sbButton2);
        Button button3 = (Button) findViewById(R.id.sbButton3);
        Button button4 = (Button) findViewById(R.id.sbButton4);
        Button button5 = (Button) findViewById(R.id.sbButton5);
        Button button6 = (Button) findViewById(R.id.sbButton6);
        Button button7 = (Button) findViewById(R.id.sbButton7);
        Button button8 = (Button) findViewById(R.id.sbButton8);
        Button menu = (Button) findViewById(R.id.sbButtonMenu);

        button0.setSoundEffectsEnabled(false);
        button1.setSoundEffectsEnabled(false);
        button2.setSoundEffectsEnabled(false);
        button3.setSoundEffectsEnabled(false);
        button4.setSoundEffectsEnabled(false);
        button5.setSoundEffectsEnabled(false);
        button6.setSoundEffectsEnabled(false);
        button7.setSoundEffectsEnabled(false);
        button8.setSoundEffectsEnabled(false);




        menu.setOnClickListener((v) -> {
            finish();
        });

        button0.setOnClickListener((v) -> {
            //TODO play proper sound
            setSound(mediaPlayer, 0);
        });

        button1.setOnClickListener((v) -> {
            //TODO
            setSound(mediaPlayer, 1);

        });

        button2.setOnClickListener((v) -> {
            //TODO
            setSound(mediaPlayer, 2);

        });

        button3.setOnClickListener((v) -> {
            //TODO
            setSound(mediaPlayer, 3);

        });

        button4.setOnClickListener((v) -> {
            //TODO
            setSound(mediaPlayer, 4);

        });

        button5.setOnClickListener((v) -> {
            //TODO
            setSound(mediaPlayer, 5);

        });

        button6.setOnClickListener((v) -> {
            //TODO
            setSound(mediaPlayer, 6);

        });

        button7.setOnClickListener((v) -> {
            //TODO
            setSound(mediaPlayer, 7);

        });

        button8.setOnClickListener((v) -> {
            //TODO
            setSound(mediaPlayer, 8);

        });
    }

    private void setSound(MediaPlayer mediaPlayer, int buttonNum){
        // TODO implement
        mediaPlayer.release();
        if(sounds[buttonNum].getFilePath().toString().equals(""))
            mediaPlayer = MediaPlayer.create(getApplicationContext(), sounds[buttonNum].getResourceID());
        else{
            //TODO set URI to local filepath. After figuring out the file system thing
//            Uri.Builder builder = new Uri.Builder();
//            Uri fileUri = sounds[buttonNum].getFilePath();
//            String toString = fileUri.toString();
//            String ext = Environment.getExternalStorageDirectory().getPath();
//            builder.appendPath(ext);
//            String string = fileUri.getPath();
//            builder.appendPath(string);
//            Uri uri = Uri.parse(ext + toString);
//            String str = uri.getAuthority();
//            if("com.android.externalstorage.documents".equals(uri.getAuthority()))
//            uri = Uri.parse("file:///" + ext + "/document/blast.wav");
//            Uri uri = Uri.parse(getFullPath(getApplicationContext(), sounds[buttonNum].getFilePath()));

//            try{
//                mediaPlayer.setDataSource(getApplicationContext(), fileUri);
//                mediaPlayer.prepare();
//            }
//            catch(Exception e) {
//                Toast.makeText(this, "You suck", Toast.LENGTH_SHORT).show();
//            }

            mediaPlayer = MediaPlayer.create(getApplicationContext(), sounds[buttonNum].getFilePath());

        }

        if(mediaPlayer != null)
            mediaPlayer.start();
        else
            Toast.makeText(this, "No sound assigned.", Toast.LENGTH_SHORT).show();
    }

    private void readFromFile(Context context) {


        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String current = "";

                while ( (current = bufferedReader.readLine()) != null ) {
                    Sound sound = new Sound();
                    int index = current.indexOf(',');
                    sound.setLabel(current.substring(0, index));
                    current = current.substring(index+1, current.length());
                    index = current.indexOf(',');
                    sound.setFilePath(current.substring(0, index));
                    current = current.substring(index+1, current.length());
                    index = current.indexOf(',');
                    sound.setResourceID(Integer.parseInt(current.substring(0, index)));
                    current = current.substring(index+1, current.length());
                    sound.setButtonNum(Integer.parseInt(current.substring(0, current.length())));
                    String s = sound.getFilePath().getPath();
                    SoundList.addSound(sound);
                }

                bufferedReader.close();
                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("soundlist activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("soundlist activity", "Can not read file: " + e.toString());
        }
    }


    public String getFullPath(Context context, Uri uri){
        // DocumentProvider
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if ("com.android.externalstorage.documents".equals(uri.getAuthority())) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
        }
        return uri.getEncodedPath();
    }



}
