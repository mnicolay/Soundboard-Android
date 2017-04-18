package edu.depaul.csc472.nicolay_m_finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import java.util.ArrayList;

import edu.depaul.csc472.nicolay_m_finalproject.sound.Sound;

/**
 * An activity representing a single Sound detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link SoundListActivity}.
 */
public class SoundDetailActivity extends AppCompatActivity{

    private ArrayList<Sound> allSounds;
    private Sound[] sounds = new Sound[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_detail);

//        Intent intent = new Intent();
//        allSounds = intent.getParcelableArrayListExtra("allSounds");
//        Parcelable[] parcelables = intent.getParcelableArrayExtra("sounds");
//        for(int i = 0; i < parcelables.length; i++)
//            sounds[i] = (Sound) parcelables[i];


        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(SoundDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(SoundDetailFragment.ARG_ITEM_ID));
            SoundDetailFragment fragment = new SoundDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.sound_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, SoundListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}