package io.github.amarcinkowski.c64.asm;

import io.github.amarcinkowski.c64.output.Bytecode;

import static io.github.amarcinkowski.c64.utils.Numbers.hex;

public class Instruction implements Comparable<Instruction> {

    public Opcode opcode;
    public byte[] data;
    public int address;

    @Override
    public String toString() {
        return hex(opcode.hex) + (data.length > 0 ? Bytecode.BETWEEN_OPCODE_AND_DATA + hex(data) : "");
    }

    @Override
    public int compareTo(Instruction instruction) {
        return this.address - instruction.address;
    }
}
