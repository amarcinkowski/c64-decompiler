package io.github.amarcinkowski.c64.asm;

/**
 * Based on 6502/6510/8500/8502 Opcode matrix (http://www.oxyron.de/html/opcodes02.html)
 *
 * Branching (http://www.6502.org/tutorials/compare_instructions.html):
 *
 * To Branch If	                                Follow compare instruction with
 *                                              For unsigned numbers	For signed numbers
 * Register is less than data	                BCC THERE	            BMI THERE
 * Register is equal to data	                BEQ THERE	            BEQ THERE
 * Register is greater than data	            BEQ HERE                BEQ HERE
 *                                              BCS THERE	            BPL THERE
 * Register is less than or equal to data	    BCC THERE               BMI THERE
 *                                              BEQ THERE	            BEQ THERE
 * Register is greater than or equal to data	BCS THERE	            BPL THERE
 */
public enum Mnemonic {

    ADC("adc", "ADd with Carry", "A:=A+%s"), // ok
    ANC("anc", "", "{adr}:=A&X&H"),
    AND("and", "bitwise AND with accumulator", "A & %s"), // ok
    ANE("ane", "", "A:=A&#{imm}"),
    ARR("arr", "", "A:=A&#{imm}"),
    ASL("asl", "Arithmetic Shift Left", "A:=A&{adr}"),
    ASR("asr", "", "A:=(A&#{imm})/2"),
    BCC("bcc", "Branch on Carry Clear (unsigned numbers)", "IF CMP>0 THEN GOTO PC+%s"), // ok TODO add "labelling" instead of ADR+x
    BCS("bcs", "Branch on Carry Set (unsigned numbers)", "IF CMP<=0 THEN GOTO PC+%s"), // ok TODO add "labelling" instead of ADR+x
    BEQ("beq", "Branch on EQual", "IF CMP=0 THEN GOTO PC+%s"), // ok TODO add "labelling" instead of ADR+x
    BIT("bit", "test BITs", "sets the zero, negative, and overflow flags accordingly"), // missing some detailed info
    BMI("bmi", "Branch on MInus (signed numbers)", "IF CMP>0 THEN GOTO PC+%s"), // ok TODO add "labelling" instead of ADR+x
    BNE("bne", "Branch on Not Equal ", "IF CMP<>0 THEN GOTO PC+%s"), // ok TODO labelling
    BPL("bpl", "Branch on PLus (signed numbers)", "IF CMP<=0 THEN GOTO PC+%s"), // ok TODO add labelling
    BRK("brk", "BReaK", "BREAK (debug), PC+=2"), // OK, Note that since BRK increments the program counter by 2 instead of 1, it is advisable to use a NOP after it to avoid issues.
    BVC("bvc", "Branch on oVerflow Clear", "IF V:=0 GOTO PC+%s"), // ok
    BVS("bvs", "Branch on oVerflow Set ", "IF V:=1 GOTO PC+%s"), // ok
    CLC("clc", "CLear Carry ", "C:=0"), // ok
    CLD("cld", "CLear Decimal ", "D:=0"), // ok
    CLI("cli", "CLear Interrupt ", "I:=0"), // OK
    CLV("clv", "CLear oVerflow ", "V:=0"), // ok
    CMP("cmp", "CoMPare accumulator", "CMP:=A-%s"), // ok
    CPX("cpx", "ComPare X register", "CMP:=X-%s"), // ok
    CPY("cpy", "ComPare Y register", "CMP:=Y-%s"), // ok
    DCP("dcp", "", "X-{adr}"),
    DEC("dec", "DECrement memory", "%s--"), // ok
    DEX("dex", "DEcrement X ", "X--"), // ok
    DEY("dey", "DEcrement Y ", "Y--"), // ok
    EOR("eor", "bitwise Exclusive OR", "X:=X-1"),
    INC("inc", "INCrement memory", "%s++"), // ok
    INX("inx", "INcrement X ", "X++"), // ok
    INY("iny", "INcrement Y ", "Y++"), // ok
    ISB("isb", "", "X:=X+1"),
    JAM("jam", "", "Y:=Y+1"),
    JMP("jmp", "JuMP", "GOTO %s"), // ok
    JSR("jsr", "Jump to SubRoutine", "PC:=%s"), // ok
    LAE("lae", "", "(S)-:=PC PC:={adr}"),
    LAX("lax", "", "A,X,S:={adr}&S"),
    LDA("lda", "LoaD Accumulator", "A:=%s"), // ok
    LDX("ldx", "LoaD X register", "X:=%s"), // ok
    LDY("ldy", "LoaD Y register", "Y:=%s"), // ok
    LSR("lsr", "Logical Shift Right", "X:={adr}"),
    LXA("lxa", "", "Y:={adr}"),
    NOP("nop", "No OPeration", "REM no-op"), // ok
    ORA("ora", "bitwise OR with Accumulator", "A | %s"),
    PHA("pha", "PusH Accumulator ", "A:=A or {adr}"),
    PHP("php", "PusH Processor status ", "(S)-:=A"),
    PLA("pla", "PuLl Accumulator ", "(S)-:=P"),
    PLP("plp", "PuLl Processor status ", "A:=+(S)"),
    RLA("rla", "", "P:=+(S)"),
    ROL("rol", "ROtate Left", "{adr}:={adr}rol A:=A and {adr}"),
    ROR("ror", "ROtate Right", "{adr}:={adr}*2+C"),
    RRA("rra", "", "{adr}:={adr}/2+C*128"),
    RTI("rti", "ReTurn from Interrupt", "{adr}:={adr}ror A:=A adc {adr}"),
    RTS("rts", "ReTurn from Subroutine", "P,PC:=+(S)"),
    SAX("sax", "", "PC:=+(S)"),
    SBC("sbc", "SuBtract with Carry", "{adr}:=A&X"),
    SBX("sbx", "", "A:=A-{adr}"),
    SEC("sec", "SEt Carry ", "C:=1"), // ok
    SED("sed", "SEt Decimal ", "D:=1"), // ok
    SEI("sei", "SEt Interrupt ", "I:=1"), // ok
    SHA("sha", "", "I:=1"),
    SHS("shs", "", "{adr}:=X&H"),
    SHX("shx", "", "{adr}:=Y&H"),
    SHY("shy", "", "{adr}:={adr}*2 A:=A or {adr}"),
    SLO("slo", "", "{adr}:={adr}/2 A:=A exor {adr}"),
    SRE("sre", "", "{adr}:=A"),
    STA("sta", "STore Accumulator", "%s:=A"), // ok
    STX("stx", "STore X register", "%s:=X"), // ok
    STY("sty", "STore Y register", "%s:=Y"), // ok
    TAX("tax", "Transfer A to X ", "X:=A"), // ok
    TAY("tay", "Transfer A to Y ", "Y:=A"), // ok
    TSX("tsx", "Transfer Stack ptr to X ", "X:=S"), // ok
    TXA("txa", "Transfer X to A ", "A:=X"), // ok
    TXS("txs", "Transfer X to Stack ptr ", "S:=X"), // ok
    TYA("tya", "Transfer Y to A ", "A:=Y"), // ok
    ;
    public String mnemonic;
    public String function;

    Mnemonic(String mnemonic, String description, String function) {
        this.mnemonic = mnemonic;
        this.function = function;
    }

    @Override
    public String toString() {
        return this.mnemonic;
    }
}


