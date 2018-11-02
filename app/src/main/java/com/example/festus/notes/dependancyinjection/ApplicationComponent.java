package com.example.festus.notes.dependancyinjection;

import android.app.Application;

import com.example.festus.notes.create.CreateFragment;
import com.example.festus.notes.detail.DetailFragment;
import com.example.festus.notes.list.ListFragment;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;

/**
 * Created by Festus Kiambi on 11/2/18.
 */
@Singleton
@Component(modules = {ApplicationModule.class, RoomModule.class})
public interface ApplicationComponent {

    void inject(ListFragment listFragment);
    void inject(CreateFragment createFragment);
    void inject(DetailFragment detailFragment);

    Application application();
}
