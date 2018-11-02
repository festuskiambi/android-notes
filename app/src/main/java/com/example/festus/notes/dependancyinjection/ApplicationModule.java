package com.example.festus.notes.dependancyinjection;

import android.app.Application;

import com.example.festus.notes.NoteApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Festus Kiambi on 11/2/18.
 */
@Module
class ApplicationModule {
    private final NoteApplication application;

    public ApplicationModule(NoteApplication application) {
        this.application = application;
    }

    @Provides
    NoteApplication provideNoteApplication(){
        return application;
    }

    @Provides
    Application provideApplication(){
        return  application;
    }
}
