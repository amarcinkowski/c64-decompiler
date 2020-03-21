package io.github.amarcinkowski.c64.bytecode;

import io.github.amarcinkowski.c64.asm.Instruction;
import io.github.amarcinkowski.c64.asm.Opcode;

import java.util.ArrayList;

import static io.github.amarcinkowski.c64.utils.Arrays.range;

public class Bytecode {

    public ArrayList<Instruction> instructions = new ArrayList<>();

    public Bytecode() {
    }

    public void parse(byte[] asm, int startAddress) {
        for (int i = 0; i < asm.length;) {
            Opcode opcode = Opcode.getByBytecode(asm[i]);
            Instruction instruction = new Instruction(range(asm, i, i + opcode.length), startAddress + i);
            instructions.add(instruction);
            i += instruction.opcode.length;
        }
    }

    @Override
    public String toString() {
        return instructions.toString();
    }
}
