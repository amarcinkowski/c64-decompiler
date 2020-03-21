package io.github.amarcinkowski.c64.utils;

import java.util.function.Function;

public class Strings {
    public static Function<String, String> addSpaces = Strings::putSpaceEvery4Bytes;
    public static Function<String, String> addNewLines = Strings::putNewLineEvery16bytes;

    public static String putCharEveryNth(String s, int n, String character) {
        return s.replaceAll("(.{" + n + "})", "$1" + character);
    }

    public static String putSpaceEvery4Bytes(String s) {
        return putCharEveryNth(s, 12, " ");
    }

    public static String putNewLineEvery16bytes(String s) {
        return putCharEveryNth(s, 52, "\n");
    }

    public static String times(String character, int length) {
        String s = "";
        for(int i = 0; i < length; i++) {
            s+= character;
        }
        return s;
    }
}
