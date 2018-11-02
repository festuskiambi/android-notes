package com.example.festus.notes.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.example.festus.notes.data.Note;
import com.example.festus.notes.data.NoteRepository;

import java.util.List;

/**
 * Created by Festus Kiambi on 11/2/18.
 */
public class NoteCollectionViewModel extends ViewModel {
    private NoteRepository repository;

    public NoteCollectionViewModel(NoteRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Note>> getListOfNotes() {
        return repository.getListOfNotes();
    }

    private void deleteNote(Note note){
        DeleteNoteTask task = new DeleteNoteTask();
        task.execute(note);
    }

    private class DeleteNoteTask extends AsyncTask<Note, Void, Void> {

        @Override
        protected Void doInBackground(Note... notes) {
            repository.deleteNote(notes[0]);
            return null;
        }
    }

}