package com.example.festus.notes.logic;

import android.view.View;

import com.example.festus.notes.data.DataSourceInterface;
import com.example.festus.notes.data.Note;
import com.example.festus.notes.view.ViewInterface;


/**
 * Created by Festus Kiambi on 10/25/18.
 */

public class Controller {

    private ViewInterface view;
    private DataSourceInterface dataSource;

    private Note temporaryNote;
    private int temporaryPosition;

    public Controller(ViewInterface view, DataSourceInterface dataSource) {
        this.view = view;
        this.dataSource = dataSource;

        getListOfNotesFromDataStore();
    }

    public void getListOfNotesFromDataStore() {

        view.setUpAdapterAndView(dataSource.getListOfNotes());
    }

    public void onNoteItemClicked(Note selectedNote, View viewRoot) {

        view.startDetailActivity(
                selectedNote.getDateAndTime(),
                selectedNote.getMessage(),
                selectedNote.getColorResource(),
                viewRoot
        );
    }

    public void createNewNoteItem() {
        Note newNote = dataSource.createNewNoteItem();

        view.addNewNoteItemToView(newNote);
    }

    public void onNoteItemSwiped(int position, Note note) {
        dataSource.deleteNoteItem(note);
        view.deleteNoteItemAtPosition(position);

        temporaryNote = note;
        temporaryPosition = position;

        view.showUndoSnackBar();

    }

    public void onUnDoConfirmed() {
         if(temporaryNote !=null){
             dataSource.insertNote(temporaryNote);
             view.insertNoteItem(temporaryPosition,temporaryNote);

             temporaryNote = null;
             temporaryPosition = 0;
         }
         else {

         }
    }

    public void onSnackBarTimeOut() {
        temporaryPosition = 0;
        temporaryNote = null;
    }
}
