package com.palazzo.metcam;

/**
 * Created by palaz on 3/6/2017.
 */

//TODO: finish refactoring MainActivity to use TimeSignature instead of dealing with beats/notevalues individually


public class TimeSignature {
    private short beats;
    private short noteValues;

    public TimeSignature(Beats beat, NoteValues values) {
        beats = beat.getNum();
        noteValues = values.getNoteValue();
    }

    public short getBeats() {
        return beats;
    }

    public void setBeats(short beats) {
        this.beats = beats;
    }

    public short getNoteValues() {
        return noteValues;
    }

    public void setNoteValues(short noteValues) {
        this.noteValues = noteValues;
    }
}
