package io.github.amarcinkowski.c64;

import java.io.IOException;
import java.util.HashMap;

import static io.github.amarcinkowski.c64.utils.Arrays.range;
import static io.github.amarcinkowski.c64.utils.Numbers.hexInv;
import static io.github.amarcinkowski.c64.utils.Numbers.hex;

public class Decompiler {

    enum MemType {
        ADDRESS(2);
        int length;

        MemType(int length) {
            this.length = length;
        }
    }


    static HashMap<String, MemType> map = new HashMap<>();

    static {
        map.put("target mem addr", MemType.ADDRESS);
        map.put("code start addr", MemType.ADDRESS);
    }

    //    final static String FILE = "/home/am/git/c64asm/colors2.prg";
    final static String FILE = "src/test/resources/colors2.prg";

    static int address = 0x0801;


    static Report report = new Report();

    static void readCode(byte[] codeBlock) {
        for (int i = 0; i < codeBlock.length; i++) {
            String mnemonic = hex(codeBlock[i]);
            Opcode opcode = Opcode.get(mnemonic);
            byte[] dataArguments = range(codeBlock, i + 1, i + opcode.bytes);
            report.addCommand(opcode, dataArguments);
            i += opcode.bytes - 1;
            address += i;
        }
    }

    public static void main(String[] args) throws IOException {
        byte[] fileContent = io.github.amarcinkowski.c64.utils.Files.readFile(FILE);
        report.parseBytes(fileContent);
        String memoryStart = hexInv(range(fileContent,0,2));
        String codeStart = hexInv(range(fileContent,2,4));
        int codeStartInFile = Integer.parseInt(codeStart,16) - Integer.parseInt(memoryStart,16) + 5;
        byte[] codeBlock = range(fileContent, codeStartInFile, fileContent.length);
        readCode(codeBlock);
        report.print();
    }

}
