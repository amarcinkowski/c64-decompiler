package io.github.amarcinkowski.c64.asm;

import io.github.amarcinkowski.c64.output.Bytecode;

import static io.github.amarcinkowski.c64.utils.Numbers.hex;

public class Command implements Comparable<Command> {

    public Opcode opcode;
    public byte[] data;
    public int address;

    @Override
    public String toString() {
        return hex(opcode.mnemonic) + (data.length > 0 ? Bytecode.BETWEEN_OPCODE_AND_DATA + hex(data) : "");
    }

    @Override
    public int compareTo(Command command) {
        return this.address - command.address;
    }
}
