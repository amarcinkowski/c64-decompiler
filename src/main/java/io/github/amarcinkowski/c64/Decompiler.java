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
        bytecode.append("\n-- bytecode\n00 01 02 03 04 05 06 07 08 09 0a 0b 0c 0d 0e 0f\n\n");
        decompiled.append("\n--\nADDR    MNEMONIC HEX  LENGTH       DATA\n");
    }

    static {
        map.put("target mem addr", MemType.ADDRESS);
        map.put("code start addr", MemType.ADDRESS);
    }

    //    final static String FILE = "/home/am/git/c64asm/colors2.prg";
    final static String FILE = "src/test/resources/colors2.prg";

    static String hex(byte x) {
        return String.format("%02x", x);
    }

    static String hex(byte[] x) {
        String s = "";
        for (int j = 0; j < x.length; j++) {
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
        return String.format("%04x %10s (%02x) L:%d %15s ;\t%s\n", address, opcode, opcode.mnemonic, opcode.bytes, hex(value), opcode.code);
    }

    static void raport() {
        System.out.println(bytecode.toString());
        System.out.println(decompiled.toString());
    }

    static byte[] range(byte[] arr, int start, int end) {
        return Arrays.copyOfRange(arr, start, end);
    }

    static void readByteCode(byte[] byteCode) {
        for (int i = 0; i < byteCode.length; i++) {
            bytecode.append(hex(byteCode[i]));
            if (i > 0 && i % 16 == 0) {
                bytecode.append("\n");
            } else {
                bytecode.append(" ");
            }
        }
    }

    static void readCode(byte[] codeBlock) {
        for (int i = 0; i < codeBlock.length; i++) {
            String mnemonic = hex(codeBlock[i]);
            Opcode opcode = get(mnemonic);
            byte[] value = range(codeBlock, i + 1, i + opcode.bytes);
            decompiled.append(format(opcode, value));
            i += opcode.bytes - 1;
            address += i;
        }
    }

    static void getAddr() {

    }

    public static void main(String[] args) throws IOException {
        byte[] fileContent = readFile(FILE);
        readByteCode(fileContent);
        int memoryStart = 0x0801;
        int codeStart = 0x080b;
        int codeStartInFile = codeStart - memoryStart + 5;
        byte[] codeBlock = range(fileContent, codeStartInFile, fileContent.length);
        readCode(codeBlock);
        raport();
    }

}
