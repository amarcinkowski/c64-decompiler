package io.github.amarcinkowski.c64.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Files {
    public static byte[] readFile(String path) {
        File f = new File(path);
        System.out.println("file " + f.getAbsolutePath());
        try {
            return java.nio.file.Files.readAllBytes(f.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

    public static byte[] readNBytes(String path, int n) {
        return Arrays.range(readFile(path), 0, n);
    }

    public static void toFile(String s) {
        try {
            java.nio.file.Files.write(Paths.get("output.txt"), s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
