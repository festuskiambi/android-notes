package com.example.festus.notes.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by Festus Kiambi on 10/25/18.
 */
@Dao
public interface NoteDao {
    @Query("SELECT * FROM Note")
    LiveData<List<Note>> getNotes();
    
    @Query("SELECT * FROM Note WHERE itemId = :itemId")
    LiveData<Note> getNoteById(String itemId);
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long  insertNote();
    
    @Delete
    void deleteNote(Note note);
}
