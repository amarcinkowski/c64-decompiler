package io.github.amarcinkowski.c64;

import java.util.Arrays;

import static io.github.amarcinkowski.c64.utils.Numbers.*;

public class Report {

    StringBuilder bytecode = new StringBuilder();
    StringBuilder decompiled = new StringBuilder();

    public Report() {
        // init builders
        bytecode.append("\n-- bytecode\n00 01 02 03 04 05 06 07 08 09 0a 0b 0c 0d 0e 0f\n\n");
        decompiled.append("\n--\nADDR    MNEMONIC HEX  LENGTH       DATA\n");
    }

    static String format(Opcode opcode, byte[] value) {
        // TODO add address
        int address = 0;
        return String.format("%04x %10s (%02x) L:%d %15s ;\t%s %s %20s %s\n", address, opcode, opcode.mnemonic, opcode.bytes, hex(value), opcode.code, arg(value, opcode.addressing), opcode.description, Arrays.toString(value));
    }

    void parseBytes(byte[] array) {
        for (int i = 0; i < array.length; i++) {
            bytecode.append(hex(array[i]));
            if (i > 0 && i % 15 == 0) {
                bytecode.append("\n");
            } else {
                bytecode.append(" ");
            }
        }
    }

    public void addCommand(Opcode opcode, byte[] dataArguments) {
        decompiled.append(format(opcode, dataArguments));
    }

    public void print() {
        System.out.println(bytecode.toString());
        System.out.println(decompiled.toString());
    }

}
