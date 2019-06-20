package io.github.amarcinkowski.c64.registers;

/**
 * http://www.zimmers.net/anonftp/pub/cbm/documents/chipdata/64doc
 */
public enum Register6502 {

    // TODO FIXME default values (?)

    /**
     * This register points the address from which the next
     * instruction byte (opcode or parameter) will be fetched.
     * Unlike other registers, this one is 16 bits in length. The
     * low and high 8-bit halves of the register are called PCL
     * and PCH, respectively.
     * <p>
     * The Program Counter may be read by pushing its value on
     * the stack. This can be done either by jumping to a
     * subroutine or by causing an interrupt.
     */
    PC("Program Counter", 0),


    /**
     * The NMOS 65xx processors have 256 bytes of stack memory,
     * ranging from $0100 to $01FF. The S register is a 8-bit
     * offset to the stack page. In other words, whenever
     * anything is being pushed on the stack, it will be stored
     * to the address $0100+S.
     * <p>
     * The Stack pointer can be read and written by transfering
     * its value to or from the index register X (see below) with
     * the TSX and TXS instructions.
     */
    S("Stack pointer", 0xFF),


    /**
     * This 8-bit register stores the state of the processor. The
     * bits in this register are called flags. Most of the flags
     * have something to do with arithmetic operations.
     */
    P("Processor status", 0),


    /**
     * The accumulator is the main register for arithmetic and
     * logic operations. Unlike the index registers X and Y, it
     * has a direct connection to the Arithmetic and Logic Unit
     * (ALU). This is why many operations are only available for
     * the accumulator, not the index registers.
     */
    A("Accumulator", 0),


    /**
     * This is the main register for addressing data with
     * indices. It has a special addressing mode, indexed
     * indirect, which lets you to have a vector table on the
     * zero page.
     */
    X("Index register X", 0),


    /**
     * The Y register has the least operations available. On the
     * other hand, only it has the indirect indexed addressing
     * mode that enables access to any memory place without
     * having to use self-modifying code.
     */
    Y("Index register Y", 0),
    ;

    public String desc;
    public Integer defaultValue;

    Register6502(String desc, Integer def) {
        this.desc = desc;
        this.defaultValue = def;
    }

}
