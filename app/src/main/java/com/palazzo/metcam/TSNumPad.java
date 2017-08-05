package com.palazzo.metcam;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
    private static TextView valueTextView;

    private TSNumPad tsNumPad;

    private AlertDialog dialog;

    private short beats;
    private short values;

    private TimeSignature ts;


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

        beats = timeSignature.getNoteValues();
        values = timeSignature.getBeats();

        beatTextView = (TextView) view.findViewById(R.id.beatTextView);
        beatTextView.setText("" + timeSignature.getBeats());
        valueTextView = (TextView) view.findViewById(R.id.valueTextView);

        one = (Button) view.findViewById(R.id.one);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("1");
                beats = 1;
            }
        });

        two = (Button) view.findViewById(R.id.two);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("2");
                beats = 2;
            }
        });

        three = (Button) view.findViewById(R.id.three);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("3");
                beats = 3;
            }
        });

        four = (Button) view.findViewById(R.id.four);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("4");
                beats = 4;
            }
        });

        five = (Button) view.findViewById(R.id.five);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("5");
                beats = 5;
            }
        });

        six = (Button) view.findViewById(R.id.six);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("6");
                beats = 6;
            }
        });

        seven = (Button) view.findViewById(R.id.seven);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("7");
                beats = 7;
            }
        });

        eight = (Button) view.findViewById(R.id.eight);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("8");
                beats = 8;
            }
        });

        nine = (Button) view.findViewById(R.id.nine);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("9");
                beats = 9;
            }
        });

        ten = (Button) view.findViewById(R.id.ten);
        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("10");
                beats = 10;
            }
        });

        eleven = (Button) view.findViewById(R.id.eleven);
        eleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("11");
                beats = 11;
            }
        });

        twelve = (Button) view.findViewById(R.id.twelve);
        twelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("12");
                beats = 12;
            }
        });

        thirteen = (Button) view.findViewById(R.id.thirteen);
        thirteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("13");
                beats = 13;
            }
        });

        fourteen = (Button) view.findViewById(R.id.fourteen);
        fourteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("14");
                beats = 14;
            }
        });

        fifteen = (Button) view.findViewById(R.id.fifteen);
        fifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("15");
                beats = 15;
            }
        });

        sixteen = (Button) view.findViewById(R.id.sixteen);
        sixteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("16");
                beats = 16;
            }
        });

        //dialog
        builder.setView(view);
        builder.setPositiveButton(R.string.next, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialog1, int which) {
                show2(a, "Time Signature Select", new tsNumPadInterface() {
                    @Override
                    public String tsNumPadInputValue(short beat, short value) {
                        if (beat != 0) {
                            postrun.tsNumPadInputValue(beat, value);
                        }
                        else
                            dialog1.dismiss();
                        return null;
                    }

                    @Override
                    public String tsNumPadCanceled() {
                        dialog1.dismiss();
                        postrun.tsNumPadCanceled();
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

    public void show2(final Activity a, final String promptString, final tsNumPadInterface postrun) {
        tsNumPad = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(a);
        builder.setTitle(promptString);
        LayoutInflater inflater = a.getLayoutInflater();
        View view = inflater.inflate(R.layout.ts_dialog_2, null, false);

        beatTextView = (TextView) view.findViewById(R.id.beatTextView);
        beatTextView.setText("" + beats);
        valueTextView = (TextView) view.findViewById(R.id.valueTextView);

        halves = (Button) view.findViewById(R.id.halves);
        halves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueTextView.setText("2");
                values = 2;
            }
        });

        quarters = (Button) view.findViewById(R.id.quarters);
        quarters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueTextView.setText("4");
                values = 4;
            }
        });

        eighths = (Button) view.findViewById(R.id.eighths);
        eighths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueTextView.setText("8");
                values = 8;
            }
        });

        sixteenths = (Button) view.findViewById(R.id.sixteenths);
        sixteenths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueTextView.setText("16");
                values = 16;
            }
        });

        //dialog
        builder.setView(view);
        builder.setPositiveButton(R.string.set, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (values != 0) {
                    ts = new TimeSignature(Beats.getBeat(beats), NoteValues.getNoteValue(values));
                    postrun.tsNumPadInputValue(ts.getBeats(), ts.getNoteValues());
                }
                else {
                    ts = new TimeSignature(Beats.getBeat(beats), NoteValues.four);
                    postrun.tsNumPadInputValue(ts.getBeats(), ts.getNoteValues());
                }
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

