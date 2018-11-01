package com.example.festus.notes;

import com.example.festus.notes.data.NoteDao;
import com.example.festus.notes.data.Note;
import com.example.festus.notes.logic.Controller;
import com.example.festus.notes.view.ViewInterface;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(MockitoJUnitRunner.class)
public class ControllerUnitTest {

    @Mock
    NoteDao dataSource;

    @Mock
    ViewInterface view;

    Controller controller;

    public static final Note testNote = new Note(
            "6:30AM 06/01/2017",
            "Check out content like Fragmented Podcast to expose yourself to the knowledge, ideas, " +
                    "and opinions of experts in your field",
            R.color.BLUE
    );
    public static final int position = 3;
    @Before
    public void setUpTest() {
        MockitoAnnotations.initMocks(this);
        controller = new Controller(view, dataSource);
    }

    @Test
    public void onGetListOfNotesSuccessfully() {

        ArrayList<Note> listOfNotes = new ArrayList<>();
        listOfNotes.add(testNote);

        Mockito.when(dataSource.getListOfNotes())
                .thenReturn(listOfNotes);

        controller.getListOfNotesFromDataStore();

        Mockito.verify(view).setUpAdapterAndView(listOfNotes);


    }

    @Test
    public void onNoteItemCclicked(){
        controller.onNoteItemClicked(testNote);

        Mockito.verify(view).startDetailActivity(
                testNote.getItemId(),
                testNote.getMessage(),
                testNote.getColorResource()
        );
    }

    @Test
    public void onCreateNewItemClicked(){
        Mockito.when(dataSource.createNewNoteItem())
                .thenReturn(testNote);

        controller.createNewNoteItem();
        Mockito.verify(view).addNewNoteItemToView(testNote);
    }

   @Test
    public void onNoteItemSwiped(){
        controller.onNoteItemSwiped(position,testNote);

        Mockito.verify(dataSource).deleteNoteItem(testNote);
        Mockito.verify(view).deleteNoteItemAtPosition(position);
        Mockito.verify(view).showUndoSnackBar();
   }

   @Test
    public void onDeleteOperation(){
//    Mockito.when(dataSource.createNewNoteItem())
//            .thenReturn(testNote);

    controller.onNoteItemSwiped(position,testNote);
    controller.onUnDoConfirmed();

    Mockito.verify(dataSource).insertNote(testNote);
    Mockito.verify(view).insertNoteItem(position,testNote);
   }

   @Test
    public void onSnackBarTimedOut(){

        controller.onNoteItemSwiped(position,testNote);

        controller.onSnackBarTimeOut();

   }
}