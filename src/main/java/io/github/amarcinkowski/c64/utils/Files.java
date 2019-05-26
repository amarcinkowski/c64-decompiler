package io.github.amarcinkowski.c64.utils;

import java.io.File;
import java.io.IOException;

public class Files {
    public static byte[] readFile(String path) throws IOException {
        return java.nio.file.Files.readAllBytes(new File(path).toPath());
    }
}
