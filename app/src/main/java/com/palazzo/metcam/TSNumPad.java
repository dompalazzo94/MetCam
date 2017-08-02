package com.palazzo.metcam;

import android.app.Activity;
import android.app.AlertDialog;
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

    public interface tsNumPadInterface {
        String tsNumPadInputValue(String beat, String value);
        String tsNumPadCanceled();
    }

    public void show(final Activity a, final String promptString, final tsNumPadInterface postrun) {
        tsNumPad = this;
        AlertDialog.Builder builder = new AlertDialog.Builder(a);
        builder.setTitle(promptString);
        LayoutInflater inflater = a.getLayoutInflater();
        View view = inflater.inflate(R.layout.ts_dialog_1, null, false);

        beatTextView = (TextView) view.findViewById(R.id.beatTextView);
        valueTextView = (TextView) view.findViewById(R.id.valueTextView);

        one = (Button) view.findViewById(R.id.one);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("1");
            }
        });

        two = (Button) view.findViewById(R.id.two);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("2");
            }
        });

        three = (Button) view.findViewById(R.id.three);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("3");
            }
        });

        four = (Button) view.findViewById(R.id.four);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("4");
            }
        });

        five = (Button) view.findViewById(R.id.five);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("5");
            }
        });

        six = (Button) view.findViewById(R.id.six);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("6");
            }
        });

        seven = (Button) view.findViewById(R.id.seven);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("7");
            }
        });

        eight = (Button) view.findViewById(R.id.eight);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("8");
            }
        });

        nine = (Button) view.findViewById(R.id.nine);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("9");
            }
        });

        ten = (Button) view.findViewById(R.id.ten);
        ten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("10");
            }
        });

        eleven = (Button) view.findViewById(R.id.eleven);
        eleven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("11");
            }
        });

        twelve = (Button) view.findViewById(R.id.twelve);
        twelve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("12");
            }
        });

        thirteen = (Button) view.findViewById(R.id.thirteen);
        thirteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("13");
            }
        });

        fourteen = (Button) view.findViewById(R.id.fourteen);
        fourteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("14");
            }
        });

        fifteen = (Button) view.findViewById(R.id.fifteen);
        fifteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("15");
            }
        });

        sixteen = (Button) view.findViewById(R.id.sixteen);
        sixteen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beatTextView.setText("16");
            }
        });
    }

}

