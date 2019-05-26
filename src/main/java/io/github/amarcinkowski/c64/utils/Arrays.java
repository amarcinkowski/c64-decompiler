package io.github.amarcinkowski.c64.utils;

public class Arrays {
    public static byte[] range(byte[] arr, int start, int end) {
        return java.util.Arrays.copyOfRange(arr, start, end);
    }
}
