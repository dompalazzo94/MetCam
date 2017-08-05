package com.palazzo.metcam;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by palaz on 3/2/2017.
 */

public class NumPad {

    private final short MIN_BPM = 40;
    private final short MAX_BPM = 300;

    private static Button zero;
    private static Button one;
    private static Button two;
    private static Button three;
    private static Button four;
    private static Button five;
    private static Button six;
    private static Button seven;
    private static Button eight;
    private static Button nine;
    private static Button clear;
    private static Button back;

    private static TextView bpmTextView;
    private NumPad numPad;

    private AlertDialog dialog;

    private String value;

    public interface numPadInterface {
        String numPadInputValue(String value);
        String numPadCanceled();
    }

    public String getValue() {
        return value;
    }

    public void show(final Activity a, final String promptString, final numPadInterface postrun) {
        numPad = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(a);
        builder.setTitle(promptString);
        LayoutInflater inflater = a.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog, null, false);

        bpmTextView = (TextView) view.findViewById(R.id.bpmTextView);
        value = "";
        bpmTextView.setText(value);

        zero = (Button) view.findViewById(R.id.zero);
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("0");
                calculateButtons();
            }
        });
        one = (Button) view.findViewById(R.id.one);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("1");
                calculateButtons();
            }
        });
        two = (Button) view.findViewById(R.id.two);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("2");
                calculateButtons();
            }
        });
        three = (Button) view.findViewById(R.id.three);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("3");
                calculateButtons();
            }
        });
        four = (Button) view.findViewById(R.id.four);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("4");
                calculateButtons();
            }
        });
        five = (Button) view.findViewById(R.id.five);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("5");
                calculateButtons();
            }
        });
        six = (Button) view.findViewById(R.id.six);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("6");
                calculateButtons();
            }
        });
        seven = (Button) view.findViewById(R.id.seven);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("7");
                calculateButtons();
            }
        });
        eight = (Button) view.findViewById(R.id.eight);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("8");
                calculateButtons();
            }
        });
        nine = (Button) view.findViewById(R.id.nine);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendNumber("9");
                calculateButtons();
            }
        });
        clear = (Button) view.findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = "";
                bpmTextView.setText("");
                enableAll();
                zero.setEnabled(false);
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
            }
        });

        back = (Button) view.findViewById(R.id.backspace);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeNumber();
                calculateButtons();
                enableAll();
                if (value.length() == 0)
                    zero.setEnabled(false);
                else if (value.equals("30")) {
                    disableAll();
                    zero.setEnabled(true);
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            }
        });

        //dialog
        builder.setView(view);
        builder.setPositiveButton(R.string.set, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (numPad.getValue().equals("")) {
                    postrun.numPadCanceled();
                } else {
                    postrun.numPadInputValue(numPad.getValue());
                }
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                postrun.numPadCanceled();
            }
        });
        dialog = builder.create();
        dialog.show();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                if (isValueValid(value))
                    ((AlertDialog)dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
            }
        });

        // check if value entered is valid and disable buttons accordingly
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        zero.setEnabled(false);
    }

    public void appendNumber(String num) {
        value = value + num;
        bpmTextView.setText(value);
    }

    public void removeNumber() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < value.length()-1; i++){
            builder.append(value.charAt(i));
        }
        value = builder.toString();
        bpmTextView.setText(value);
    }

    public void calculateButtons() {
        String val;
        if (value.equals(""))
            val = "0";
        else
            val = value;

        char charAtIndex;

        // index 0
        charAtIndex = val.charAt(0);
        if (val.equals(0)) // first character is null
            zero.setEnabled(false);
        else {
            zero.setEnabled(true);
            switch(charAtIndex) {
                case '1':
                case '2':
                    if (value.length() == 3) {
                        disableAll();
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    }
                    break;
                case '3':
                    if (value.length() == 2 && val.charAt(1) == '0') {
                        disableAll();
                        zero.setEnabled(true);
                    }
                    else if (value.length() == 3 && val.charAt(2) == '0') {
                        disableAll();
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    }

                    break;
                default:
                    if (value.length() == 2) {
                        disableAll();
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                    }
                    break;
            }
        }
    }

    private void disableAll() {
        zero.setEnabled(false);
        one.setEnabled(false);
        two.setEnabled(false);
        three.setEnabled(false);
        four.setEnabled(false);
        five.setEnabled(false);
        six.setEnabled(false);
        seven.setEnabled(false);
        eight.setEnabled(false);
        nine.setEnabled(false);
    }

    private void enableAll() {
        zero.setEnabled(true);
        one.setEnabled(true);
        two.setEnabled(true);
        three.setEnabled(true);
        four.setEnabled(true);
        five.setEnabled(true);
        six.setEnabled(true);
        seven.setEnabled(true);
        eight.setEnabled(true);
        nine.setEnabled(true);
    }

    private boolean isValueValid(String value) {
        if (value.equals(""))
            return true;

        if (MAX_BPM > Short.parseShort(value)) {
            if (Short.parseShort(value) < MIN_BPM) {
                return true;
            }
        }
        return false;
    }
}
















