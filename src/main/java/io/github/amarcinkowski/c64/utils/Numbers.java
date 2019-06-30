package io.github.amarcinkowski.c64.utils;

import io.github.amarcinkowski.c64.asm.Addressing;

public class Numbers {

    public static String hex(int x) {
        return String.format("%02x", x);
    }

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

//    public static String hexInBracket(byte[] x) {
//        String s = "[";
//        for (int j = 0; j < x.length; j++) {
//            s += hex(x[j]) + " ";
//        }
//        s=s.trim()+"]";
//        return s;
//    }

    // test +/- byte numbers
    public static String dec(byte[] x) {
        String h = hexInv(x);
        String d = h.length() > 0 ? "" + Long.parseLong(hexInv(x), 16) : "";
//        System.out.println("debug " + Arrays.toString(x) + " " + h + " " + d);
        return d;
    }

    public static String arg(byte[] array, Addressing addressing) {
        String a = addressing.prefix;
        if (array.length == 1) {
            a += "$" + hex(array);
        } else if (array.length == 2) {
            a += "$" + hexInv(array);
        } else a = "?";
        a += addressing.postfix;
        return a;
    }

    public static String unsigned(String num) {
        return "" + ((byte) Integer.parseInt(num));
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
