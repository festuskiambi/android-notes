package com.example.festus.notes.dependancyinjection;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;

import com.example.festus.notes.data.NoteDao;
import com.example.festus.notes.data.NoteDatabase;
import com.example.festus.notes.data.NoteRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Festus Kiambi on 11/2/18.
 */
@Module
class RoomModule {

    private final NoteDatabase database;

    public RoomModule(Application application) {
        this.database = Room.databaseBuilder(
                application,
                NoteDatabase.class,
                "Note.db"
        ).build();
    }

    @Provides
    @Singleton
    NoteRepository provideNoteRepository(NoteDao noteDao){
        return new NoteRepository(noteDao);
    }

    @Provides
    @Singleton
    NoteDao provideNoteDao(NoteDatabase database){
        return database.noteDao();
    }

    @Provides
    @Singleton
    NoteDatabase provideNoteDatabase(Application application){
        return database;
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(NoteRepository repository){
        return new CustomViewModelFactory(repository);
    }
}
