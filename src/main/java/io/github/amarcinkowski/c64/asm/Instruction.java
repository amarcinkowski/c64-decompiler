package io.github.amarcinkowski.c64.asm;

import io.github.amarcinkowski.c64.utils.BytecodeUtils;

import static io.github.amarcinkowski.c64.utils.Arrays.range;
import static io.github.amarcinkowski.c64.utils.Numbers.hex;

public class Instruction implements Comparable<Instruction> {

    public Opcode opcode;
    public byte[] data;
    public int address;

    public Instruction(Opcode opcode) {
        this.opcode = opcode;
    }

    public Instruction(Opcode opcode, byte[] data, int address) {
        this.opcode = opcode;
        this.data = data;
        this.address = address;
    }

    public Instruction(byte[] codeBlock, int addr) {
        String mnemonic = hex(codeBlock[0]);
        this.opcode = Opcode.getByHexString(mnemonic);
        this.data = range(codeBlock, 1, opcode.length);
        this.address = addr;
    }

    // FIXME move to byte code decorator
    @Override
    public String toString() {
        return hex(opcode.hex) + (data.length > 0 ? BytecodeUtils.BETWEEN_OPCODE_AND_DATA + hex(data) : "");
    }

    @Override
    public int compareTo(Instruction instruction) {
        return this.address - instruction.address;
    }
}
