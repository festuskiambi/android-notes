package com.example.festus.notes.data;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Festus Kiambi on 11/1/18.
 */
public class NoteRepository {
    private final NoteDao noteDao;

    @Inject
    public NoteRepository(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public LiveData<List<Note>> getListOfNotes(){
        return  noteDao.getNotes();
    }

    public  LiveData<Note> getNoteById(String noteId){
        return  noteDao.getNoteById(noteId);
    }

    public void insertNote(Note note){
        noteDao.insertNote(note);
    }

    public void deleteNote(Note note){
        noteDao.deleteNote(note);
    }
}
