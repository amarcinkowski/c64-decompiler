package io.github.amarcinkowski.c64.output;

import io.github.amarcinkowski.c64.asm.Command;
import io.github.amarcinkowski.c64.asm.Parser;

import java.util.List;

import static io.github.amarcinkowski.c64.utils.Numbers.*;

public class Assembler extends Language {

    StringBuilder decompiled = new StringBuilder();

    public Assembler() {
        decompiled.append("\n--\nADDR    MNEMONIC HEX  LENGTH       DATA\n");
    }

    static String format(Command c) {
        // TODO add address
        int mnemo = c.opcode.hex;
        int bytes = c.opcode.bytes;
        String args = hex(c.data);
        String code = c.opcode.mnemonic;
        String arg = arg(c.data, c.opcode.addressing);
        String desc = c.opcode.description;
//        String dec = dec(c.data);
        return String.format("%04x %10s (%02x) L:%d %15s |%10s %8s //%25s %-8s | \n", c.address, c.opcode, mnemo, bytes, args, code, arg, desc, /*dec*/"");
    }

    public void parse(Parser p) {
        List<Command> commands = p.commands;
        for(Command c : commands) {
            decompiled.append(format(c));
        }
    }


    @Override
    public String toString() {
        return decompiled.toString();
    }


}
