package edu.depaul.csc472.nicolay_m_finalproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.depaul.csc472.nicolay_m_finalproject.sound.Sound;
import edu.depaul.csc472.nicolay_m_finalproject.sound.SoundList;

public class FileActivity extends AppCompatActivity{

    private Sound newSound = new Sound();
    private static final int READ_REQUEST_CODE = 42;
    TextView selectedView;
    ArrayList<Sound> allSounds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);


        Button browse = (Button) findViewById(R.id.fButtonBrowse);
        Button cancel = (Button) findViewById(R.id.fButtonCancel);
        Button save = (Button) findViewById(R.id.fButtonSave);
        selectedView = (TextView) findViewById(R.id.selectedFile);
        EditText editLabel = (EditText) findViewById(R.id.editLabel);

        browse.setOnClickListener((v) -> {
            //TODO open file browser, set selectedView
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);

            // Filter to only show results that can be "opened", such as a
            // file (as opposed to a list of contacts or timezones)
            intent.addCategory(Intent.CATEGORY_OPENABLE);

            // Filter to show only images, using the image MIME data type.
            // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
            // To search for all documents available via installed storage providers,
            // it would be "*/*".
            intent.setType("audio/*");

            startActivityForResult(intent, READ_REQUEST_CODE);

        });

        cancel.setOnClickListener((v) -> {
            finish();
        });

        save.setOnClickListener((v) -> {
            //TODO save information to a Sound object, send back to main menu

            if(newSound.getFilePath()==null)
                Toast.makeText(this, "You must select a sound file.", Toast.LENGTH_SHORT).show();
            else if(newSound.getLabel().isEmpty())
                Toast.makeText(this, "You must give this sound a label.", Toast.LENGTH_SHORT).show();
            else if(labelExists(newSound.getLabel()))
                Toast.makeText(this, "That label already exists. Please enter a new one.", Toast.LENGTH_SHORT).show();

            else {
                SoundList.addSound(newSound);
                finish();
            }

            
        });

        editLabel.setOnEditorActionListener((v, actionId, event) -> {
            //TODO clear when selected; dismiss keyboard
            boolean handled = false;
            if(actionId == EditorInfo.IME_ACTION_DONE){
                newSound.setLabel(editLabel.getText().toString());
                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                handled = true;
            }
            return handled;
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK && requestCode == READ_REQUEST_CODE) {

            Uri uri = null;
            if (data != null) {
                uri = data.getData();
                newSound.setFilePath(uri);
                String fileName = uri.getLastPathSegment();
                selectedView.setText(fileName.substring(fileName.indexOf(':')+1));
            }


        }

    }

    private boolean labelExists(String label){
        for(Sound s : SoundList.SOUNDS)
            if(s.getLabel().equals(label))
                return true;
        return false;
    }

}
