package io.github.amarcinkowski.c64.output;

import io.github.amarcinkowski.c64.asm.Instruction;
import io.github.amarcinkowski.c64.asm.Parser;
import io.github.amarcinkowski.c64.memory.MemoryAddress;
import io.github.amarcinkowski.c64.registers.CBM6510CPU;

import java.util.List;

import static io.github.amarcinkowski.c64.utils.Numbers.*;

public class Assembler extends Language {

    StringBuilder decompiled = new StringBuilder();
    CBM6510CPU cbm6510CPU = new CBM6510CPU();

    public Assembler() {
        decompiled.append("\n--\nADDR    MNEMONIC HEX  LENGTH       DATA\n");
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
        MemoryAddress[] ma = MemoryAddress.getMemoryAdres(Integer.parseInt(dec));
        String mem = (ma == null ? "?" : ma.toString());
        return String.format("%04x %10s (%02x) L:%d %10s |%5s %8s //%15s %-10s %s | %s \n", c.address, c.opcode, mnemo, bytes, args, code, arg, desc, cbm6510CPU, dec, mem);
    }

    public void parse(Parser p) {
        List<Instruction> instructions = p.instructions;
        for(Instruction c : instructions) {
            decompiled.append(format(c, cbm6510CPU));
        }
    }


    @Override
    public String toString() {
        return decompiled.toString();
    }


}
