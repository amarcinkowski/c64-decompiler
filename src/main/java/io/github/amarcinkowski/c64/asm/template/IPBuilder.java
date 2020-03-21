package io.github.amarcinkowski.c64.asm.template;

import io.github.amarcinkowski.c64.asm.Opcode;

public class IPBuilder {

    private InstructionPattern ip = new InstructionPattern();

    /**
     * if no data, wildcard will be set in pattern, otherwise it'll look for specific value
     *
     * e.g. in memory-copy-template:
     * lda adr1,x;
     * sta adr2,x;
     * dex,
     * bne $f7
     *
     * bne $f7 means jump 9 length back, to lda until copied. $f7 is specific.
     * values in "adr" of lda and sta may vary so wildcard can be used.
     * @param data
     */
    public IPBuilder data(byte[] data) {
        ip.data = data;
        ip.anyData = false;
        return this;
    }

    /**
     * opcode to look for
     * @param opcode
     */
    public IPBuilder op(Opcode opcode) {
        ip.opcode = opcode;
        return this;
    }

    /**
     * extract data (instruction argument)
     */
    public IPBuilder arg() {
        ip.extractData = true;
        return this;
    }

    public InstructionPattern build() {
        return ip;
    }

    /**
     * short for build()
     */
    public InstructionPattern b() {
        return build();
    }
}

