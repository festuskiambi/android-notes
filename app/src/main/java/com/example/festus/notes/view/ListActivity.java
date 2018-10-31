package com.example.festus.notes.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.festus.notes.R;
import com.example.festus.notes.data.FakeDataSource;
import com.example.festus.notes.data.Note;
import com.example.festus.notes.logic.Controller;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListActivity extends AppCompatActivity implements ViewInterface, View.OnClickListener {

    private static final String EXTRA_DATE_AND_TIME = "EXTRA_DATE_AND_TIME";
    private static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private static final String EXTRA_DRAWBALE = "EXTRA_DRAWABLE";

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
        FloatingActionButton fab = findViewById(R.id.fab_create_new_item);
        fab.setOnClickListener(this);
        controller = new Controller(this, new FakeDataSource());

    }

    @Override
    public void startDetailActivity(String dateAndTime, String message, int colorResource) {
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra(EXTRA_DATE_AND_TIME, dateAndTime);
        i.putExtra(EXTRA_MESSAGE, message);
        i.putExtra(EXTRA_DRAWBALE, colorResource);

        startActivity(i);
    }

    @Override
    public void setUpAdapterAndView(List<Note> listOfNotes) {
        this.listOfNotes = listOfNotes;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                linearLayoutManager.getOrientation()
        );

        itemDecoration.setDrawable(
                ContextCompat.getDrawable(
                        ListActivity.this,
                        R.drawable.white_divider
                )
        );

        recyclerView.addItemDecoration(itemDecoration);


    }

    @Override
    public void addNewNoteItemToView(Note note) {
        listOfNotes.add(note);

        int endOfList = listOfNotes.size() - 1;
        adapter.notifyItemInserted(endOfList);
        recyclerView.smoothScrollToPosition(endOfList);
    }

    @Override
    public void deleteNoteItemAtPosition(int position) {
        listOfNotes.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void showUndoSnackBar() {
        Snackbar.make(
                findViewById(R.id.root_list_activity),
                getString(R.string.action_delete_item),
                Snackbar.LENGTH_LONG
        )
                .setAction(R.string.action_undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        controller.onUnDoConfirmed();
                    }
                })
                .addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                    @Override
                    public void onDismissed(Snackbar transientBottomBar, int event) {
                        super.onDismissed(transientBottomBar, event);

                        controller.onSnackBarTimeOut();
                    }
                })
                .show();
    }

    @Override
    public void insertNoteItem(int position, Note note) {
        listOfNotes.add(position, note);

        adapter.notifyItemInserted(position);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        if (viewId == R.id.fab_create_new_item) {
            controller.createNewNoteItem();
        }
    }

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.item_note, parent, false);
            return new CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
            Note currentNote = listOfNotes.get(position);

            holder.coloredCircle.setImageResource(currentNote.getColorResource());
            holder.message.setText(currentNote.getMessage());
            holder.dateAndTime.setText(currentNote.getDateAndTime());
            holder.loading.setVisibility(View.INVISIBLE);
        }

        @Override
        public int getItemCount() {
            return listOfNotes.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private CircleImageView coloredCircle;
            private TextView dateAndTime;
            private TextView message;
            private ProgressBar loading;
            private ViewGroup container;

            public CustomViewHolder(@NonNull View itemView) {
                super(itemView);
                this.coloredCircle = itemView.findViewById(R.id.imv_list_item_circle);
                this.dateAndTime = itemView.findViewById(R.id.tv_date_and_time);
                this.message = itemView.findViewById(R.id.tv_message);
                this.loading = itemView.findViewById(R.id.pro_item_data);
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
