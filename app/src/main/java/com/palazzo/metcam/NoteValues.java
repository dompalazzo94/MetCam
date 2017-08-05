package com.palazzo.metcam;

/**
 * Created by palaz on 3/1/2017.
 */

public enum NoteValues {
    two("2"),
    four("4"),
    eight("8"),
    sixteen("16");

    private String noteValue;

    NoteValues(String noteValue) {
        this.noteValue = noteValue;
    }

    public short getNoteValue() {
        return Short.parseShort(noteValue);
    }

    @Override
    public String toString() {
        return noteValue;
    }

    public static NoteValues getNoteValue(short noteValues) {
        switch(noteValues) {
            case 2:
                return NoteValues.two;
            case 4:
                return NoteValues.four;
            case 8:
                return NoteValues.eight;
            case 16:
                return NoteValues.sixteen;
        }
        return null;
    }
}
