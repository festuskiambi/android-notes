package com.example.festus.notes.data;

import java.util.List;

/**
 * Created by Festus Kiambi on 10/25/18.
 */
public interface DataSourceInterface {
    List<Note> getListOfNotes();

    Note createNewNoteItem();

    void deleteNoteItem(Note testNote);

    void insertNote(Note testNote);
}
