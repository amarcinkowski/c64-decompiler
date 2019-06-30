package io.github.amarcinkowski.c64.asm;

import java.util.ArrayList;

import static io.github.amarcinkowski.c64.utils.Arrays.range;
import static io.github.amarcinkowski.c64.utils.Numbers.hex;
import static io.github.amarcinkowski.c64.utils.Numbers.hexInv;

public class Parser {


//    enum MemType {
//        ADDRESS(2);
//        int length;
//
//        MemType(int length) {
//            this.length = length;
//        }
//    }
//
//
//    static HashMap<String, MemType> map = new HashMap<>();
//
//    static {
//        map.put("target mem addr", MemType.ADDRESS);
//        map.put("mnemonic start addr", MemType.ADDRESS);
//    }

    public ArrayList<Instruction> instructions = new ArrayList<>();

    String memoryStart;
    String codeStart;
    int codeStartInFile;

    public void readHeaders(byte[] code) {
        memoryStart = hexInv(range(code, 0, 2));
        codeStart = hexInv(range(code, 2, 4));
        codeStartInFile = Integer.parseInt(codeStart, 16) - Integer.parseInt(memoryStart, 16) + 4;
        System.out.println("memory start  " + memoryStart);
        System.out.println("code start    " + codeStart);
        System.out.println("in file       " + codeStartInFile);
    }

    public Instruction getCommand(byte[] codeBlock, int i) {
        String mnemonic = hex(codeBlock[i]);
        Opcode opcode = Opcode.get(mnemonic);
       // System.out.println("mnemo " + hex + ", opcode " + opcode);
        byte[] data = range(codeBlock, i + 1, i + opcode.bytes);
        int addr = Integer.parseInt(codeStart, 16) + i + 3;
        Instruction c = new Instruction();
        c.opcode = opcode;
        c.data = data;
        c.address = addr;
        return c;
    }

    public void parse(byte[] fileContent) {
        readHeaders(fileContent);
        byte[] codeBlock = range(fileContent, codeStartInFile, fileContent.length);
        for (int i = 0; i < codeBlock.length; i++) {
            Instruction c = getCommand(codeBlock, i);
            instructions.add(c);
            i += c.opcode.bytes - 1;
        }
    }
}
