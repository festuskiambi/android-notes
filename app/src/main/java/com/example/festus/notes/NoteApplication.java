package com.example.festus.notes;

import android.app.Application;

import com.example.festus.notes.dependancyinjection.ApplicationComponent;

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
