package io.github.amarcinkowski.c64.utils;

import java.nio.ByteBuffer;

import static io.github.amarcinkowski.c64.utils.Numbers.hexInv;

public class Arrays {
    public static byte[] range(byte[] arr, int start, int end) {
        return java.util.Arrays.copyOfRange(arr, start, end);
    }

    // can be done faster (no toString / parse)
    public static int read2B(byte[] arr, int adr) {
        return Integer.parseInt(hexInv(range(arr, adr, adr + 2)), 16);
    }

    public static byte[] replace(byte[] mem, byte[] copied, int to) {
        ByteBuffer buffer = ByteBuffer.wrap(mem);
        buffer.position(to);
        buffer.put(copied);
        return mem;
    }
}
