package com.example.festus.notes;

import android.app.Application;

import com.example.festus.notes.dependancyinjection.ApplicationComponent;
import com.example.festus.notes.dependancyinjection.ApplicationModule;
import com.example.festus.notes.dependancyinjection.DaggerApplicationComponent;
import com.example.festus.notes.dependancyinjection.RoomModule;


import dagger.android.DaggerApplication;

/**
 * Created by Festus Kiambi on 11/2/18.
 */
public class NoteApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .roomModule(new RoomModule(this))
                .build();

    }
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
