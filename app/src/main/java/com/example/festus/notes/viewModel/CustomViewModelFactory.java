package com.example.festus.notes.viewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.festus.notes.data.NoteRepository;

/**
 * Created by Festus Kiambi on 11/2/18.
 */
public class CustomViewModelFactory implements ViewModelProvider.Factory {

    private NoteRepository repository;

    public CustomViewModelFactory(NoteRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NoteCollectionViewModel.class))
            return (T) new NoteCollectionViewModel(repository);
        else if (modelClass.isAssignableFrom(NoteViewModel.class))
            return (T) new NoteViewModel(repository);
        else if (modelClass.isAssignableFrom(NewNoteViewModel.class))
            return (T) new NewNoteViewModel(repository);
        else {
            throw new IllegalArgumentException("ViewModel not found");
        }
    }
}
