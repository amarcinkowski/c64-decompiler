package io.github.amarcinkowski.c64.output;

import io.github.amarcinkowski.c64.asm.Addressing;
import io.github.amarcinkowski.c64.asm.Instruction;
import io.github.amarcinkowski.c64.asm.Parser;
import io.github.amarcinkowski.c64.memory.MemoryAddress;
import io.github.amarcinkowski.c64.memory.MemoryMap;
import io.github.amarcinkowski.c64.registers.CBM6510CPU;

import java.util.List;

import static io.github.amarcinkowski.c64.utils.Numbers.*;

public class Assembler extends Language {

    StringBuilder decompiled = new StringBuilder();
    CBM6510CPU cbm6510CPU = new CBM6510CPU();

    public Assembler() {
        decompiled.append("\n--\nADDR    MNEMONIC HEX  LENGTH       DATA\n");
    }

    static String getMem(Addressing addressing, MemoryMap ma, String dec) {
        String mem = "";
        switch (addressing) {
            case ABSOLUTEX:
                // TODO
            case ABSOLUTEY:
            case ABSOLUTE:
                mem = (ma == null ? "?" : ma.toString());
                break;
            default:
                mem = dec;
        }
        return mem;
    }

    static String format(Instruction c, CBM6510CPU cbm6510CPU) {
        // TODO add address
        int mnemo = c.opcode.hex;
        int bytes = c.opcode.bytes;
        String args = hex(c.data);
        String code = c.opcode.mnemonic.toString();
        String arg = arg(c.data, c.opcode.addressing);
        String desc = c.opcode.mnemonic.function;
        String dec = dec(c.data);
        MemoryMap mm = MemoryMap.getByAddress(MemoryAddress.getMemoryAdres(dec));
        String mem = getMem(c.opcode.addressing, mm, dec);
        String comment = String.format(desc, mem);
        return String.format("%04x %10s (%02x) L:%d %10s |%5s %8s // %s\n", c.address, c.opcode, mnemo, bytes, args, code, arg, comment);
    }

    public void parse(Parser p) {
        List<Instruction> instructions = p.instructions;
        for (Instruction c : instructions) {
            decompiled.append(format(c, cbm6510CPU));
        }
    }


    @Override
    public String toString() {
        return decompiled.toString();
    }


}
