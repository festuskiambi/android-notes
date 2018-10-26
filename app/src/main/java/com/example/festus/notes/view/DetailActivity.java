package com.example.festus.notes.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.festus.notes.R;

public class DetailActivity extends AppCompatActivity {

    private static final String EXTRA_DATE_AND_TIME = "EXTRA_DATE_AND_TIME";
    private static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private static final String EXTRA_COLOR = "EXTRA_COLOR";

    private TextView dateAndTime;
    private TextView message;
    private View coloredBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent i = getIntent();
        String dateAndTimeExtra = i.getStringExtra(EXTRA_DATE_AND_TIME);
        String messageExtra = i.getStringExtra(EXTRA_MESSAGE);
        int drawableResourceExtra = i.getIntExtra(EXTRA_COLOR, 0);

        dateAndTime =  findViewById(R.id.tv_date_and_time_header);
        dateAndTime.setText(dateAndTimeExtra);

        message = findViewById(R.id.tv_message_body);
        message.setText(messageExtra);

        coloredBackground = findViewById(R.id.cont_background_color);
        coloredBackground.setBackgroundResource(
                drawableResourceExtra
        );

    }
}
