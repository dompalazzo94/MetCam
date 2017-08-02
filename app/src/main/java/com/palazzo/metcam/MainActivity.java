package com.palazzo.metcam;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final short MIN_BPM = 40;
    private final short MAX_BPM = 300;

    private short bpm = 120;
    private short noteValue = 4;
    private short beats = 4;
    private short volume;
    private short initialVolume;
    private double beatSound = 6400;
    private double sound = 2440;
    private AudioManager audio;
    private MetronomeAsyncTask metroTask;

    private Button minusButton;
    private Button plusButton;
    private TextView currentBeat;
    private TextView bpmText;

    private final String TAG = "MainActivity log";

    private Handler mHandler;

    private Handler getHandler() {
        return new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String message = (String) msg.obj;
                if (message.equals("1"))
                    currentBeat.setTextColor(Color.GREEN);
                else
                    currentBeat.setTextColor(getResources().getColor(R.color.yellow));
                currentBeat.setText(message);
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        metroTask = new MetronomeAsyncTask();

        bpmText = (TextView) findViewById(R.id.bps);
        bpmText.setText(""+bpm);

        TextView timeSignatureText = (TextView) findViewById(R.id.timesignature);
        timeSignatureText.setText(""+ beats + "/"+ noteValue);

        minusButton = (Button) findViewById(R.id.minus);
        minusButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                bpm -= 10;
                if (bpm <= MIN_BPM) {
                    bpm = MIN_BPM;
                }
                TextView bpmText = (TextView) findViewById(R.id.bps);
                bpmText.setText(""+bpm);
                metroTask.setBpm(bpm);
                minBpmGuard();
                Log.d(TAG, "Tempo decreased by 10. New tempo = " + bpm);
                return true;
            }
        });

        plusButton = (Button) findViewById(R.id.plus);
        plusButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                bpm += 10;
                if (bpm >= MAX_BPM)
                    bpm = MAX_BPM;
                TextView bpmText = (TextView) findViewById(R.id.bps);
                bpmText.setText(""+bpm);
                metroTask.setBpm(bpm);
                maxBpmGuard();
                Log.d(TAG, "Tempo increased by 10. New tempo = " + bpm);
                return true;
            }
        });

        currentBeat = (TextView) findViewById(R.id.currentBeat);
        currentBeat.setTextColor(Color.GREEN);

        audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        initialVolume = (short) audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        volume = initialVolume;

        SeekBar volumeBar = (SeekBar) findViewById(R.id.volumebar);
        volumeBar.setMax(audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeBar.setProgress(volume);
        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                volume = (short) progress;
                audio.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public synchronized void onStartStopClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();
        if(buttonText.equalsIgnoreCase("start")) {
            button.setText(R.string.stop);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
                metroTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void[]) null);
            else
                metroTask.execute();
        } else {
            button.setText(R.string.start);
            currentBeat.setText("1");
            metroTask.stop();
            metroTask = new MetronomeAsyncTask();
            Runtime.getRuntime().gc();
        }
    }

    private void maxBpmGuard() {
        if (bpm >= MAX_BPM) {
            plusButton.setEnabled(false);
            minusButton.setEnabled(true);
        } else if (!minusButton.isEnabled() && bpm > MIN_BPM){
            minusButton.setEnabled(true);
            plusButton.setEnabled(false);
        }
    }

    public void onPlusClick(View view) {
        bpm++;
        TextView bpmText = (TextView) findViewById(R.id.bps);
        bpmText.setText(""+bpm);
        metroTask.setBpm(bpm);
        maxBpmGuard();
        Log.d(TAG, "Tempo increased by 1. New tempo = " + bpm);
    }

    public void onBpmClick(View view) {
        //open dialog box to edit bpm
        NumPad numPad = new NumPad();
        numPad.show(this, "Enter BPM:", new NumPad.numPadInterface() {
            @Override
            public String numPadInputValue(String value) {
                bpm = Short.parseShort(value);
                maxBpmGuard();
                minBpmGuard();
                bpmText.setText(""+bpm);
                metroTask.setBpm(bpm);
                Log.d(TAG, "New tempo = " + bpm);
                return null;
            }

            @Override
            public String numPadCanceled() {
                return null;
            }
        });
    }

    public void onTSClick(View view) {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View v = layoutInflater.inflate(R.layout.ts_dialog_1, null);

        //open dialog to select Time Signature
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v);
        builder.setTitle("Select Time Signature");

        /*//setup numerator of time signature
        Spinner beatSpinner = (Spinner) v.findViewById(R.id.notespinner);
        ArrayAdapter<Beats> arrayBeats = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Beats.values());
        beatSpinner.setAdapter(arrayBeats);
        beatSpinner.setSelection(Beats.four.ordinal());
        arrayBeats.setDropDownViewResource(R.layout.spinner_dropdown);
        beatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Beats beat = (Beats) parent.getItemAtPosition(position);
                TextView timeSignature = (TextView) findViewById(R.id.timesignature);
                timeSignature.setText("" + beat + "/" + noteValue);
                metroTask.setBeat(beat.getNum());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //setup denominator of time signature
        Spinner noteSpinner = (Spinner) v.findViewById(R.id.notespinner);
        ArrayAdapter<NoteValues> arrayValues = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, NoteValues.values());
        noteSpinner.setAdapter(arrayValues);
        noteSpinner.setSelection(NoteValues.four.ordinal());
        arrayValues.setDropDownViewResource(R.layout.spinner_dropdown);
        noteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                NoteValues noteValue = (NoteValues) parent.getItemAtPosition(position);
                TextView timeSignature = (TextView) findViewById(R.id.timesignature);
                timeSignature.setText("" + beats + "/" + noteValue);
                metroTask.setNoteValue(noteValue.getNoteValue());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/

        builder.setPositiveButton(R.string.set, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("TS Check", "Time Signature has been set");
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.i("TS Check", "Time Signature dialog has been canceled");
            }
        });

        Dialog d = builder.create();
        d.show();

    }

    private void minBpmGuard() {
        if (bpm <= MIN_BPM) {
            minusButton.setEnabled(false);
            plusButton.setEnabled(true);
        } else if (!plusButton.isEnabled() && bpm < MAX_BPM) {
            plusButton.setEnabled(true);
            minusButton.setEnabled(false);
        }
    }

    public void onMinusClick(View view) {
        bpm--;
        TextView bpmText = (TextView) findViewById(R.id.bps);
        bpmText.setText(""+bpm);
        metroTask.setBpm(bpm);
        minBpmGuard();
        Log.d(TAG, "Tempo decreased by 1. New tempo = " + bpm);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        SeekBar volumeBar = (SeekBar) findViewById(R.id.volumebar);
        volume = (short) audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        switch(keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                volumeBar.setProgress(volume);
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    public void onBackPressed() {
        //TODO: keep process running and open a notification drawer rather than closing the app
        metroTask.stop();
        Runtime.getRuntime().gc();
        audio.setStreamVolume(AudioManager.STREAM_MUSIC, initialVolume, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
        finish();
    }

    private class MetronomeAsyncTask extends AsyncTask<Void, Void, String> {
        Metronome metronome;
        MetronomeAsyncTask() {
            mHandler = getHandler();
            metronome = new Metronome(mHandler);
        }

        protected String doInBackground(Void... params) {
            metronome.setBeat(beats);
            metronome.setNoteValue(noteValue);
            metronome.setBpm(bpm);
            metronome.setBeatSound(beatSound);
            metronome.setSound(sound);

            metronome.play();
            return null;
        }

        public void stop() {
            metronome.stop();
            metronome = null;
        }

        public void setBpm(short bpm) {
            metronome.setBpm(bpm);
            metronome.calcSilence();
        }

        public void setBeat(short beat) {
            if (metronome != null)
                metronome.setBeat(beat);
        }

        public void setNoteValue(short noteValue) {
            if (metronome != null)
                metronome.setNoteValue(noteValue);
        }
    }
}