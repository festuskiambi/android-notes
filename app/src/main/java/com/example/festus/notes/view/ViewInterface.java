package com.example.festus.notes.view;

import com.example.festus.notes.data.Note;

import java.util.List;

/**
 * Created by Festus Kiambi on 10/25/18.
 */
public interface ViewInterface {

    void startDetailActivity(String dateAndTime, String message, int colorResource);

    void setUpAdapterAndView(List<Note> listOfNotes);

    void addNewNoteItemToView(Note note);

    void deleteNoteItemAtPosition(int position);

    void showUndoSnackBar();

    void insertNoteItem(int position, Note testNote);
}
