package io.github.amarcinkowski.c64.asm;

import io.github.amarcinkowski.c64.bytecode.Bytecode;

import java.util.ArrayList;

public class Assembler {

    public ArrayList<Instruction> instructions;

    public void parse(ArrayList<Instruction> instructions) {
        this.instructions = instructions;
    }

    public void parse(byte[] code, int adr) {
        Bytecode b = new Bytecode();
        b.parse(code, adr);
        parse(b.instructions);
    }

    // FIXME here clean assembler -> decorated full descr (or add sth like logback with configuration)
    @Override
    public String toString() {
        return "\n" + instructions.toString();
    }


}
