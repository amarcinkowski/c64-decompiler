package io.github.amarcinkowski.c64.asm;

/**
 * Based on 6502/6510/8500/8502 Opcode matrix ( http://www.oxyron.de/html/opcodes02.html )
 * <p>
 * The processor status register has 8 bits, where 7 are used as flags
 * </p>
 */
public enum Flag {

    N("negative flag", "1 when result is negative"),
    V("overflow flag", "1 on signed overflow"),
    x("unused", "always 1"),
    B("break flag", "1 when interupt was caused by a BRK"),
    D("decimal flag", "1 when CPU in BCD mode"),
    I("IRQ flag", "when 1, no interupts will occur, exceptions are IRQs forced by BRK and NMIs"),
    Z("zero flag", "1 when all bits of a result are 0"),
    C("carry flag", "1 on unsigned overflow"),
    ;
    public String name;
    public String description;

    Flag(String name, String description) {
        this.name = name;
        this.description = description;
    }

}
