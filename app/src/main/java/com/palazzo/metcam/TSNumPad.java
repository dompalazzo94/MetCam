package com.palazzo.metcam;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by palaz on 7/5/2017.
 */

public class TSNumPad {
    private static Button one;
    private static Button two;
    private static Button three;
    private static Button four;
    private static Button five;
    private static Button six;
    private static Button seven;
    private static Button eight;
    private static Button nine;
    private static Button ten;
    private static Button eleven;
    private static Button twelve;
    private static Button thirteen;
    private static Button fourteen;
    private static Button fifteen;
    private static Button sixteen;

    private static Button halves;
    private static Button quarters;
    private static Button eighths;
    private static Button sixteenths;

    private static TextView beatTextView;
    private static TextView beatTextView2;
    private static TextView valueTextView;
    private static TextView valueTextView2;

    private TSNumPad tsNumPad;

    private AlertDialog dialog;

    private short beats = 0;
    private short values = 0;

    private TimeSignature ts = new TimeSignature();

    private final String TAG = "TSNumPad";


    public interface tsNumPadInterface {
        String tsNumPadInputValue(short beat, short value);
        String tsNumPadCanceled();
    }

    public void show(final Activity a, TimeSignature timeSignature, final String promptString, final tsNumPadInterface postrun) {
        tsNumPad = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(a);
        builder.setTitle(promptString);
        LayoutInflater inflater = a.getLayoutInflater();
        View view = inflater.inflate(R.layout.ts_dialog_1, null, false);

        ts = timeSignature;
        beats = timeSignature.getBeats();
        values = timeSignature.getNoteValues();

        beatTextView = (TextView) view.findViewById(R.id.beatTextView);
        beatTextView.setText("" + beats);
        valueTextView = (TextView) view.findViewById(R.id.valueTextView);
        valueTextView.setText("" + values);

        one = (Button) view.findViewById(R.id.one);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("1");
                beats = 1;
                Log.d(TAG, "Beats was changed to " + beats);
            }
        });

        two = (Button) view.findViewById(R.id.two);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("2");
                beats = 2;
                Log.d(TAG, "Beats was changed to " + beats);
            }
        });

        three = (Button) view.findViewById(R.id.three);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("3");
                beats = 3;
                Log.d(TAG, "Beats was changed to " + beats);
            }
        });

        four = (Button) view.findViewById(R.id.four);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("4");
                beats = 4;
                Log.d(TAG, "Beats was changed to " + beats);
            }
        });

        five = (Button) view.findViewById(R.id.five);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("5");
                beats = 5;
                Log.d(TAG, "Beats was changed to " + beats);
            }
        });

        six = (Button) view.findViewById(R.id.six);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("6");
                beats = 6;
                Log.d(TAG, "Beats was changed to " + beats);
            }
        });

        seven = (Button) view.findViewById(R.id.seven);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("7");
                beats = 7;
                Log.d(TAG, "Beats was changed to " + beats);
            }
        });

        eight = (Button) view.findViewById(R.id.eight);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("8");
                beats = 8;
                Log.d(TAG, "Beats was changed to " + beats);
            }
        });

        nine = (Button) view.findViewById(R.id.nine);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("9");
                beats = 9;
                Log.d(TAG, "Beats was changed to " + beats);
            }
        });

        ten = (Button) view.findViewById(R.id.ten);
        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("10");
                beats = 10;
                Log.d(TAG, "Beats was changed to " + beats);
            }
        });

        eleven = (Button) view.findViewById(R.id.eleven);
        eleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("11");
                beats = 11;
                Log.d(TAG, "Beats was changed to " + beats);
            }
        });

        twelve = (Button) view.findViewById(R.id.twelve);
        twelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("12");
                beats = 12;
                Log.d(TAG, "Beats was changed to " + beats);
            }
        });

        thirteen = (Button) view.findViewById(R.id.thirteen);
        thirteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("13");
                beats = 13;
                Log.d(TAG, "Beats was changed to " + beats);
            }
        });

        fourteen = (Button) view.findViewById(R.id.fourteen);
        fourteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("14");
                beats = 14;
                Log.d(TAG, "Beats was changed to " + beats);
            }
        });

        fifteen = (Button) view.findViewById(R.id.fifteen);
        fifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("15");
                beats = 15;
                Log.d(TAG, "Beats was changed to " + beats);
            }
        });

        sixteen = (Button) view.findViewById(R.id.sixteen);
        sixteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("16");
                beats = 16;
                Log.d(TAG, "Beats was changed to " + beats);
            }
        });

        //dialog
        builder.setView(view);
        builder.setPositiveButton(R.string.next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog, int which) {
                ts.setBeats(beats);
                ts.setNoteValues(values);
                Log.d(TAG, "Show 1:\nBeats = " + ts.getBeats() + "\nvalues = " + ts.getNoteValues());
                show2(a, ts,"Time Signature Select", new tsNumPadInterface() {
                    @Override
                    public String tsNumPadInputValue(short beat, short value) {
                        dialog.dismiss();
                        Log.d(TAG, "Show 1:\nBeats = " + beat + "\nvalues = " + value);
                        postrun.tsNumPadInputValue(beat, value);
                        return null;
                    }

                    @Override
                    public String tsNumPadCanceled() {
                        return null;
                    }
                });
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    public void show2(final Activity a, TimeSignature timeSignature, final String promptString, final tsNumPadInterface postrun) {
        tsNumPad = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(a);
        builder.setTitle(promptString);
        LayoutInflater inflater = a.getLayoutInflater();
        View view = inflater.inflate(R.layout.ts_dialog_2, null, false);

        ts = timeSignature;
        Log.d(TAG, "Show 2:\nTimeSignature is " + ts.getBeats() + "/" + ts.getNoteValues());

        beatTextView2 = (TextView) view.findViewById(R.id.beatTextView2);
        beatTextView2.setText("" + ts.getBeats());
        valueTextView2 = (TextView) view.findViewById(R.id.valueTextView2);
        valueTextView2.setText("" + ts.getNoteValues());

        halves = (Button) view.findViewById(R.id.halves);
        halves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueTextView2.setText("2");
                values = 2;
                Log.d(TAG, "Values was changed to " + values);
            }
        });

        quarters = (Button) view.findViewById(R.id.quarters);
        quarters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueTextView2.setText("4");
                values = 4;
            }
        });

        eighths = (Button) view.findViewById(R.id.eighths);
        eighths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueTextView2.setText("8");
                values = 8;
            }
        });

        sixteenths = (Button) view.findViewById(R.id.sixteenths);
        sixteenths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueTextView2.setText("16");
                values = 16;
            }
        });

        //dialog
        builder.setView(view);
        builder.setPositiveButton(R.string.set, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ts.setNoteValues(values);
                postrun.tsNumPadInputValue(ts.getBeats(), ts.getNoteValues());
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                postrun.tsNumPadCanceled();

            }
        });
        dialog = builder.create();
        dialog.show();

    }

}

