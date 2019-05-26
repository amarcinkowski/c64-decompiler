package io.github.amarcinkowski.c64;

import io.github.amarcinkowski.c64.utils.Numbers;

import java.io.IOException;
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

    static int address = 0x0801;

    static String format(Opcode opcode, byte[] value) {
        return String.format("%04x %10s (%02x) L:%d %15s ;\t%s\n", address, opcode, opcode.mnemonic, opcode.bytes, Numbers.hex(value), opcode.code);
    }

    static void raport() {
        System.out.println(bytecode.toString());
        System.out.println(decompiled.toString());
    }

    static byte[] range(byte[] arr, int start, int end) {
        return Arrays.copyOfRange(arr, start, end);
    }

    static void readByteCode(byte[] array) {
        for (int i = 0; i < array.length; i++) {
            bytecode.append(Numbers.hex(array[i]));
            if (i > 0 && i % 16 == 0) {
                bytecode.append("\n");
            } else {
                bytecode.append(" ");
            }
        }
    }

    static void readCode(byte[] codeBlock) {
        for (int i = 0; i < codeBlock.length; i++) {
            String mnemonic = Numbers.hex(codeBlock[i]);
            Opcode opcode = Opcode.get(mnemonic);
            byte[] value = range(codeBlock, i + 1, i + opcode.bytes);
            decompiled.append(format(opcode, value));
            i += opcode.bytes - 1;
            address += i;
        }
    }

    public static void main(String[] args) throws IOException {
        byte[] fileContent = io.github.amarcinkowski.c64.utils.Files.readFile(FILE);
        readByteCode(fileContent);
        String memoryStart = Numbers.getAddress(range(fileContent,0,2));
        String codeStart = Numbers.getAddress(range(fileContent,2,4));
        int codeStartInFile = Integer.parseInt(codeStart,16) - Integer.parseInt(memoryStart,16) + 5;
        byte[] codeBlock = range(fileContent, codeStartInFile, fileContent.length);
        readCode(codeBlock);
        raport();
    }

}
