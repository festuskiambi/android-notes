package com.example.festus.notes.data;

import com.example.festus.notes.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Festus Kiambi on 10/25/18.
 */
public class FakeDataSource implements DataSourceInterface {

    private static final int sizeOfCollection = 12;
    private Random random;

    private final String[] datesAndTimes = {
            "6:30AM 06/01/2017",
            "9:26PM 04/22/2013",
            "2:01PM 12/02/2015",
            "2:43AM 09/7/2018",
    };

    private final String[] messages = {
            "Check out content like Fragmented Podcast to expose yourself to the knowledge, ideas, " +
                    "and opinions of experts in your field",
            "Look at Open Source Projects like Android Architecture Blueprints to see how experts" +
                    " design and build Apps",
            "Write lots of Code and Example Apps. Writing good Quality Code in an efficient manner "
                    + "is a Skill to be practiced like any other.",
            "If at first something doesn't make any sense, find another explanation. We all " +
                    "learn/teach different from each other. Find an explanation that speaks to you."
    };

    private final int[] drawables = {
            R.drawable.blue_drawable,
            R.drawable.red_drawable,
            R.drawable.green_drawable,
            R.drawable.yellow_drawable
    };

    public FakeDataSource() {
        random = new Random();
    }

    @Override
    public List<Note> getListOfNotes() {

        ArrayList<Note> listOfNotes = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            listOfNotes.add(createNewNoteItem());
        }
        return listOfNotes;
    }

    @Override
    public Note createNewNoteItem() {
        int randOne = random.nextInt(4);
        int randTwo = random.nextInt(4);
        int randThree = random.nextInt(4);

        Note note = new Note(
                datesAndTimes[randOne],
                messages[randTwo],
                drawables[randThree]
        );
        return note;
    }

    @Override
    public void deleteNoteItem(Note note) {
        getListOfNotes().remove(note);
    }

    @Override
    public void insertNote(Note Note) {

    }
}
