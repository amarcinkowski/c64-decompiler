package io.github.amarcinkowski.c64.asm.template;

import static io.github.amarcinkowski.c64.asm.Opcode.*;

public class MemCopyXCounterMinus extends Template {

    // TODO add optional e.g. x=100 on the beginning
    // TODO add variants e.g. x++ x=p to 100, x-- x=100 to 0
    public static InstructionPattern[] opcodes = {b().op(LDA_ABX).arg().b(), b().op(STA_ABX).arg().b(), b().op(DEX_IMP).b(), b().op(BNE_REL).data(new byte[]{(byte) 0xf7}).b()};

    public static final String DESC = "COPY n BYTES FROM %s TO %s";

    public MemCopyXCounterMinus() {
        for(InstructionPattern ip : opcodes) {
            addInstruction(ip);
        }
    }
}
