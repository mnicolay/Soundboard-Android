package edu.depaul.csc472.nicolay_m_finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import edu.depaul.csc472.nicolay_m_finalproject.sound.Sound;
import edu.depaul.csc472.nicolay_m_finalproject.sound.SoundList;

import java.util.List;

/**
 * An activity representing a list of Sounds. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link SoundDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class SoundListActivity extends AppCompatActivity implements SoundDetailFragment.DetailCallbacks{

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_list);


        recyclerView =  (RecyclerView) findViewById(R.id.sound_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        if (findViewById(R.id.sound_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        if(mTwoPane){
            Button finish = (Button) findViewById(R.id.two_pane_finish);
            finish.setOnClickListener((v) -> finish());

        }
        else{
            Button finish = (Button) findViewById(R.id.sound_listFinish);
            finish.setOnClickListener((v) -> finish());
        }
    }

    @Override
    public void onItemChanged() {
        ((SoundRecyclerViewAdapter) recyclerView.getAdapter()).notifyDataSetChanged();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SoundRecyclerViewAdapter(SoundList.SOUNDS));
    }

    public class SoundRecyclerViewAdapter
            extends RecyclerView.Adapter<SoundRecyclerViewAdapter.ViewHolder> {

        private final List<Sound> sounds;

        public SoundRecyclerViewAdapter(List<Sound> items) {
            sounds = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sound_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.sound = sounds.get(position);
            holder.mIdView.setText(holder.sound.getLabel());
            if(holder.sound.getButtonNum() == 9)
                holder.mContentView.setText(getString(R.string.no_button));
            else
                holder.mContentView.setText(String.format(getString(R.string.button_assign), holder.sound.getButtonNum() + 1));

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString(SoundDetailFragment.ARG_ITEM_ID, holder.sound.getLabel());
                        SoundDetailFragment fragment = new SoundDetailFragment();
                        fragment.setArguments(arguments);
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.sound_detail_container, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(context, SoundDetailActivity.class);
                        intent.putExtra(SoundDetailFragment.ARG_ITEM_ID, holder.sound.getLabel());

                        context.startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return sounds.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public Sound sound;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
