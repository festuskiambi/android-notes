package com.example.festus.notes.detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.festus.notes.R;


public class DetailFragment extends Fragment {

    private static final String EXTRA_ITEM_ID = "EXTRA_ITEM_ID";

    private TextView dateAndTime;
    private TextView message;
    private View coloredBackground;
    private String itemId;


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
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        dateAndTime = v.findViewById(R.id.lbl_date_and_time_header);

        message = v.findViewById(R.id.lbl_message_body);

        coloredBackground = v.findViewById(R.id.imv_colored_background);
      return  v;
    }

}
