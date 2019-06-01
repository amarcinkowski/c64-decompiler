package io.github.amarcinkowski.c64.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Files {
    public static byte[] readFile(String path) {
        try {
            return java.nio.file.Files.readAllBytes(new File(path).toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

    public static void toFile(String s) {
        try {
            java.nio.file.Files.write(Paths.get("output.txt"), s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
