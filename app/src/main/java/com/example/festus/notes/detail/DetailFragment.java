package com.example.festus.notes.detail;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.festus.notes.NoteApplication;
import com.example.festus.notes.R;
import com.example.festus.notes.data.Note;
import com.example.festus.notes.viewModel.NoteViewModel;

import javax.inject.Inject;


public class DetailFragment extends Fragment {

    private static final String EXTRA_ITEM_ID = "EXTRA_ITEM_ID";

    private TextView dateAndTime;
    private TextView message;
    private ImageView coloredBackground;
    private String itemId;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    NoteViewModel noteViewModel;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(String itemId) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_ITEM_ID, itemId);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((NoteApplication) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);

        Bundle args = getArguments();

        this.itemId = args.getString(EXTRA_ITEM_ID);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Set up and subscribe (observe) to the ViewModel
        noteViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(NoteViewModel.class);

        noteViewModel.getNote(itemId).observe(this, new Observer<Note>() {
            @Override
            public void onChanged(@Nullable Note note) {
                if (note != null) {
                    dateAndTime.setText(note.getItemId());
                    message.setText(note.getMessage());
                    coloredBackground.setImageResource(note.getColorResource());
                }

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        dateAndTime = v.findViewById(R.id.lbl_date_and_time_header);

        message = v.findViewById(R.id.lbl_message_body);

        coloredBackground = v.findViewById(R.id.imv_colored_background);
        return v;
    }

}
