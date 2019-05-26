package io.github.amarcinkowski.c64;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;

public class Decompiler {

    enum MemType {
        ADDRESS(2);
        int length;

        MemType(int length) {
            this.length = length;
        }
    }

    static StringBuilder bytecode = new StringBuilder();
    static StringBuilder decompiled = new StringBuilder();

    static HashMap<String, MemType> map = new HashMap<>();

    // init builders
    static {
        bytecode.append("\n-- bytecode\n");
        decompiled.append("\n--\nADDR    MNEMONIC HEX  LENGTH       DATA\n");
    }

    static {
        map.put("target mem addr", MemType.ADDRESS);
        map.put("code start addr", MemType.ADDRESS);
    }

//    final static String FILE = "/home/am/git/c64asm/colors2.prg";
    final static String FILE = "/home/am/git/c64game/target/commodore.prg";

    static String hex(byte x) {
        return String.format("%02x", x);
    }

    static String hex(byte[] x) {
        String s = "";
        for (int j = x.length - 1; j >= 0; j--) {
            s += hex(x[j]) + " ";
        }
        return s;
    }

    static byte[] readFile(String path) throws IOException {
        return Files.readAllBytes(new File(path).toPath());
    }

    static Opcode get(String mnemonic) {
        return Opcode.byMnemonic(Integer.parseInt(mnemonic, 16));
    }

    static int address = 0x0801;

    static String format(Opcode opcode, byte[] value) {
        return String.format("%04x %10s (%02x) L:%d %15s\n", address, opcode, opcode.mnemonic, opcode.bytes, hex(value));
    }

    static void raport() {
        System.out.println(bytecode.toString());
        System.out.println(decompiled.toString());
    }

    public static void main(String[] args) throws IOException {
        byte[] fileContent = readFile(FILE);
        for (int i = 0; i < fileContent.length; i++) {
            String mnemonic = hex(fileContent[i]);
            Opcode opcode = get(mnemonic);
            byte[] value = Arrays.copyOfRange(fileContent, i + 1, i + opcode.bytes);
            bytecode.append(mnemonic);
            bytecode.append(" ");
            decompiled.append(format(opcode, value));
            i += opcode.bytes - 1;
            address += i;
        }
        raport();
    }

}
