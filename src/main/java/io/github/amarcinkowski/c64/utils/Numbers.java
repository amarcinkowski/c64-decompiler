package io.github.amarcinkowski.c64.utils;

import io.github.amarcinkowski.c64.Addressing;

import java.util.Arrays;

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

    // test +/- byte numbers
    public static int dec(byte[] x) {
        System.out.println("debug " + Arrays.toString(x) + " " + hexInv(x) + " " + Integer.parseInt(hexInv(x), 16));
        return Integer.parseInt(hexInv(x), 16);
    }

    public static String arg(byte[] array, Addressing addressing) {
        String a = addressing.prefix;
        if (array.length == 1) {
            a += "$" + hex(array);
        } else if (array.length == 2) {
            a += "$" + hexInv(array);
        } else a = "??";
        a += addressing.postfix;
        return a;
    }


    // +test 00 01 == 256 + 1
    public static String hexInv(byte[] array) {
        String s = "";
        for (int j = array.length - 1; j >= 0; j--) {
            s += hex(array[j]);
        }
        return s;
    }
}
