package edu.depaul.csc472.nicolay_m_finalproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

import edu.depaul.csc472.nicolay_m_finalproject.sound.Sound;
import edu.depaul.csc472.nicolay_m_finalproject.sound.SoundList;



/**
 * A fragment representing a single Sound detail screen.
 * This fragment is either contained in a {@link SoundListActivity}
 * in two-pane mode (on tablets) or a {@link SoundDetailActivity}
 * on handsets.
 */
public class SoundDetailFragment extends Fragment{
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    private int buttonNum = -1;
    private ArrayList<Sound> allSounds = new ArrayList<>();

    /**
     * The dummy content this fragment is presenting.
     */
    private Sound sound;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SoundDetailFragment() {
    }

    public interface DetailCallbacks{
        public void onItemChanged();
    }

    private DetailCallbacks mCallbacks;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            sound = SoundList.SOUND_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.sound_detail, container, false);





        Button[] button = new Button[9];
        button[0] = (Button) rootView.findViewById(R.id.detailButton0);
        button[1] = (Button) rootView.findViewById(R.id.detailButton1);
        button[2] = (Button) rootView.findViewById(R.id.detailButton2);
        button[3] = (Button) rootView.findViewById(R.id.detailButton3);
        button[4] = (Button) rootView.findViewById(R.id.detailButton4);
        button[5] = (Button) rootView.findViewById(R.id.detailButton5);
        button[6] = (Button) rootView.findViewById(R.id.detailButton6);
        button[7] = (Button) rootView.findViewById(R.id.detailButton7);
        button[8] = (Button) rootView.findViewById(R.id.detailButton8);


        int disabled = -1;
        for(int i = 0; i < button.length; i++) {
            final int fi = i;
            button[i].setOnClickListener((v) -> update(sound, fi));
        }

        // Show the dummy content as text in a TextView.
        if (sound != null) {
            ((TextView) rootView.findViewById(R.id.sound_detail)).setText(String.format(getString(R.string.selectedSound), sound.getLabel()));
        }

        return rootView;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof DetailCallbacks) {
            mCallbacks = (DetailCallbacks) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mCallbacks = null;
    }

    public void update(Sound sound, int buttonNum){
        sound.setButtonNum(buttonNum);
        SoundList.changeButtonNum(sound);
        if(mCallbacks != null) {
            mCallbacks.onItemChanged();
        }
    }


}
