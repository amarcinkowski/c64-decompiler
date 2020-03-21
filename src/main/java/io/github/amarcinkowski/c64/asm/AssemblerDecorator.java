package io.github.amarcinkowski.c64.asm;

import io.github.amarcinkowski.c64.memory.MemoryAddress;
import io.github.amarcinkowski.c64.memory.MemoryMap;

import static io.github.amarcinkowski.c64.utils.Numbers.*;

public class AssemblerDecorator {


    Assembler assembler;
    public AssemblerDecorator(Assembler assembler) {
        this.assembler = assembler;
    }

    public static String rawHeader() {
        return "\nADDR    MNEMONIC HEX  LENGTH       DATA\n";
    }

    public static String raw(Instruction c) {
        return String.format("%04x %10s (%02x) L:%d %10s", c.address, c.opcode, c.opcode.hex, c.opcode.length, hex(c.data));
    }

    public static String clean(Instruction c) {
        String code = c.opcode.mnemonic.toString();
        String arg = arg(c.data, c.opcode.addressing);
        return String.format("%4s %8s", code, arg);
    }

    public static String comment(Instruction c) {
        String desc = c.opcode.mnemonic.function;
        String dec = dec(c.data);
        MemoryMap mm = MemoryMap.getByAddress(MemoryAddress.getMemoryAdres(dec));
        String mem = Addressing.getFormattedData(c.opcode.addressing, mm, dec);
        return String.format(desc, mem);
    }

    public static String full(Instruction c) {
        return String.format("%s | %s // %40s.", raw(c), clean(c), comment(c));
    }

    @Override
    public String toString() {
        StringBuilder decompiled=  new StringBuilder();
        for (Instruction c : assembler.instructions) {
            decompiled.append(AssemblerDecorator.full(c) + "\n");
        }
        return rawHeader() + decompiled.toString();
    }
}
