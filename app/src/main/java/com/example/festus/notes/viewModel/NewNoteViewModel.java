package com.example.festus.notes.viewModel;

import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.example.festus.notes.data.Note;
import com.example.festus.notes.data.NoteRepository;

/**
 * Created by Festus Kiambi on 11/2/18.
 */
public class NewNoteViewModel extends ViewModel {

    private NoteRepository repository;

    public NewNoteViewModel(NoteRepository repository) {
        this.repository = repository;
    }

    public void addnewNoteToDatabase(Note note) {
        new AddNoteTask().execute(note);
    }


    private class AddNoteTask extends AsyncTask<Note, Void, Void> {

        @Override
        protected Void doInBackground(Note... notes) {
            repository.insertNote(notes[0]);
            return null;
        }
    }
}
