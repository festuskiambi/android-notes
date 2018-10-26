package com.example.festus.notes.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.festus.notes.R;
import com.example.festus.notes.data.FakeDataSource;
import com.example.festus.notes.data.Note;
import com.example.festus.notes.logic.Controller;

import java.util.List;

public class ListActivity extends AppCompatActivity implements ViewInterface {

    private static final String EXTRA_DATE_AND_TIME = "EXTRA_DATE_AND_TIME";
    private static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private static final String EXTRA_COLOR = "EXTRA_COLOR";

    private List<Note> listOfNotes;

    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;

    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.rv_notes);
        layoutInflater = getLayoutInflater();
        controller = new Controller(this, new FakeDataSource());
    }

    @Override
    public void startDetailActivity(String dateAndTime, String message, int colorResource) {
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra(EXTRA_DATE_AND_TIME, dateAndTime);
        i.putExtra(EXTRA_MESSAGE, message);
        i.putExtra(EXTRA_COLOR, colorResource);

        startActivity(i);
    }

    @Override
    public void setUpAdapterAndView(List<Note> listOfNotes) {
     this.listOfNotes = listOfNotes;

     recyclerView.setLayoutManager(new LinearLayoutManager(this));
     adapter= new CustomAdapter();
     recyclerView.setAdapter(adapter);
    }

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.item_note,parent,false);
            return new CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
            Note currentNote = listOfNotes.get(position);

            holder.coloredCircle.setBackgroundResource(
                    currentNote.getColorResource()
            );
            holder.message.setText(currentNote.getMessage());
            holder.dateAndTime.setText(currentNote.getDateAndTime());
        }

        @Override
        public int getItemCount() {
            return listOfNotes.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private ImageView coloredCircle;
            private TextView dateAndTime;
            private TextView message;
            private ViewGroup container;
            public CustomViewHolder(@NonNull View itemView) {
                super(itemView);
                this.coloredCircle = itemView.findViewById(R.id.imv_list_item_circle);
                this.dateAndTime = itemView.findViewById(R.id.tv_date_and_time);
                this.message = itemView.findViewById(R.id.tv_message);
                this.container = itemView.findViewById(R.id.root_list_item);

                this.container.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                Note note = listOfNotes.get(
                        this.getAdapterPosition()
                );

                controller.onNoteItemClicked(note);
            }
        }
    }
}
