package com.example.festus.notes.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.festus.notes.data.Note;
import com.example.festus.notes.data.NoteRepository;

/**
 * Created by Festus Kiambi on 11/2/18.
 */
public class NoteViewModel extends ViewModel {

    private NoteRepository repository;

    public NoteViewModel(NoteRepository repository) {
        this.repository = repository;
    }

    public LiveData<Note> getNote(String itemId){
        return  repository.getNoteById(itemId);
    }
}
