package com.palazzo.metcam;

import android.os.Handler;
import android.os.Message;

/**
 * Created by palaz on 3/1/2017.
 */

//TODO: add accents to each timesignature

public class Metronome {

    private double bpm;
    private int beat;
    private int noteValue;
    private int silence;

    private double beatSound;
    private double sound;
    private final int tick = 1000; // samples of tick

    private boolean play = true;

    private AudioGenerator audioGenerator = new AudioGenerator(8000);
    private Handler mHandler;
    private double[] soundTickArray;
    private double[] soundTockArray;
    private double[] silenceSoundArray;
    private Message msg;
    private int currentBeat = 1;

    public Metronome(Handler handler) {
        audioGenerator.createPlayer();
        this.mHandler = handler;
    }

    public void calcSilence() {
        silence = (int) (((60/bpm)*8000)-tick);
        soundTickArray = new double[this.tick];
        soundTockArray = new double[this.tick];
        silenceSoundArray = new double[this.silence];
        msg = new Message();
        msg.obj = "" + currentBeat;
        double[] tick = audioGenerator.getSineWave(this.tick, 8000, beatSound);
        double[] tock = audioGenerator.getSineWave(this.tick, 8000, sound);
        for(int i = 0; i < this.tick; i++) {
            soundTickArray[i] = tick[i];
            soundTockArray[i] = tock[i];
        }
        for(int i = 0; i < silence; i++) {
            silenceSoundArray[i] = 0;
        }
    }

    public void play() {
        calcSilence();
        do {
            msg = new Message();
            msg.obj = "" + currentBeat;
            if (currentBeat == 1) {
                audioGenerator.writeSound(soundTockArray);
            } else {
                audioGenerator.writeSound(soundTickArray);
            }

            if (bpm <= 120) {
                mHandler.sendMessage(msg);
            }
            audioGenerator.writeSound(silenceSoundArray);

            if (bpm > 120) {
                mHandler.sendMessage(msg);
            }
            currentBeat++;

            if (currentBeat > beat) {
                currentBeat = 1;
            }
        } while (play);
    }

    public void stop() {
        play = false;
        audioGenerator.destroyAudioTrack();
    }

    public double getBpm() {
        return bpm;
    }

    public void setBpm(double bpm) {
        this.bpm = bpm;
    }

    public int getBeat() {
        return beat;
    }

    public void setBeat(int beat) {
        this.beat = beat;
    }

    public int getNoteValue() {
        return noteValue;
    }

    public void setNoteValue(int noteValue) {
        this.noteValue = noteValue;
    }

    public double getBeatSound() {
        return beatSound;
    }

    public void setBeatSound(double beatSound) {
        this.beatSound = beatSound;
    }

    public double getSound() {
        return sound;
    }

    public void setSound(double sound) {
        this.sound = sound;
    }
}
