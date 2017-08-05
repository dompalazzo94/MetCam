package com.palazzo.metcam;

/**
 * Created by palaz on 3/1/2017.
 */

public enum Beats {
    one("1"),
    two("2"),
    three("3"),
    four("4"),
    five("5"),
    six("6"),
    seven("7"),
    eight("8"),
    nine("9"),
    ten("10"),
    eleven("11"),
    twelve("12"),
    thirteen("13"),
    fourteen("14"),
    fifteen("15"),
    sixteen("16");

    private String beat;

    Beats(String beat) {
        this.beat = beat;
    }

    @Override
    public String toString() {
        return beat;
    }

    public short getNum() {
        return Short.parseShort(beat);
    }

    public static Beats getBeat(short beat) {
        switch(beat) {
            case 1:
                return Beats.one;
            case 2:
                return Beats.two;
            case 3:
                return Beats.three;
            case 4:
                return Beats.four;
            case 5:
                return Beats.five;
            case 6:
                return Beats.six;
            case 7:
                return Beats.seven;
            case 8:
                return Beats.eight;
            case 9:
                return Beats.nine;
            case 10:
                return Beats.ten;
            case 11:
                return Beats.eleven;
            case 12:
                return Beats.twelve;
            case 13:
                return Beats.thirteen;
            case 14:
                return Beats.fourteen;
            case 15:
                return Beats.fifteen;
            case 16:
                return Beats.sixteen;
        }
        return null;
    }
}
