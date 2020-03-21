package io.github.amarcinkowski.c64.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Files {

    private final static Logger logger = LoggerFactory.getLogger(Files.class);

    public static byte[] readFile(String path) {
        File f = new File(path);
        logger.info("reading file " + f.getAbsolutePath());
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
            java.nio.file.Files.write(Paths.get("bytecode.txt"), s.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
