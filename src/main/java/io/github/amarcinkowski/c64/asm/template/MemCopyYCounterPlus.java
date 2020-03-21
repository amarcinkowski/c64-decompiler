package io.github.amarcinkowski.c64.asm.template;

import static io.github.amarcinkowski.c64.asm.Opcode.*;

public class MemCopyYCounterPlus extends Template {

    public static InstructionPattern[] opcodes = {b().op(LDA_ABY).arg().b(), b().op(STA_ABY).arg().b(), b().op(INY_IMP).b(), b().op(BNE_REL).data(new byte[]{(byte) 0xf7}).b()};

    public static final String DESC = "COPY n BYTES FROM %s TO %s";

    public MemCopyYCounterPlus() {
        for(InstructionPattern ip : opcodes) {
            addInstruction(ip);
        }
    }
}
