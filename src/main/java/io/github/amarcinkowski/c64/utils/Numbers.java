package io.github.amarcinkowski.c64.utils;

import io.github.amarcinkowski.c64.Addressing;

public class Numbers {

    public static String hex(byte x) {
        return String.format("%02x", x);
    }

    public static String hex(byte[] x) {
        String s = "";
        for (int j = 0; j < x.length; j++) {
            s += hex(x[j]) + " ";
        }
        return s;
    }

    public static String arg(byte[] array, Addressing addressing) {
        String a = addressing.prefix;
        if (array.length == 1) {
            a += "$" + hex(array);
        } else if (array.length == 2) {
            a += "$" + getAddress(array);
        } else a = "??";
        a += addressing.postfix;
        return a;
    }


    // +test 00 01 == 256 + 1
    public static String getAddress(byte[] array) {
        String s = String.format("%02x%02x", array[1], array[0]);
        System.out.println("debug " + s);
        return s;
    }
}
