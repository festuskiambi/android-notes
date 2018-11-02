/*
 * *
 *  * Copyright (C) 2017 Ryan Kay Open Source Project
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.example.festus.notes.dependancyinjection;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;

import com.example.festus.notes.data.NoteDao;
import com.example.festus.notes.data.NoteDatabase;
import com.example.festus.notes.data.NoteRepository;
import com.example.festus.notes.viewModel.CustomViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by R_KAY on 8/18/2017.
 */
@Module
public class RoomModule {


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
