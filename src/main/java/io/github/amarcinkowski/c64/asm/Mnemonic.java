package io.github.amarcinkowski.c64.asm;

/**
 * Based on 6502/6510/8500/8502 Opcode matrix (http://www.oxyron.de/html/opcodes02.html)
 */
public enum Mnemonic {

    ADC("adc", "ADd with Carry", "A:=A+{adr}"),
    ANC("anc", "", "{adr}:=A&X&H"),
    AND("and", "bitwise AND with accumulator", "A:=(A&#{imm})/2"),
    ANE("ane", "", "A:=A&#{imm}"),
    ARR("arr", "", "A:=A&#{imm}"),
    ASL("asl", "Arithmetic Shift Left", "A:=A&{adr}"),
    ASR("asr", "", "A:=(A&#{imm})/2"),
    BCC("bcc", "Branch on Carry Clear ", "{adr}:={adr}*2"),
    BCS("bcs", "Branch on Carry Set ", "X:=A&X-#{imm}"),
    BEQ("beq", "Branch on EQual ", "branch on C=0"),
    BIT("bit", "test BITs", "branch on C=1"),
    BMI("bmi", "Branch on MInus ", "branch on Z=1"),
    BNE("bne", "Branch on Not Equal ", "N:=b7 V:=b6 Z:=A&{adr}"),
    BPL("bpl", "Branch on PLus ", "branch on N=1"),
    BRK("brk", "BReaK", "branch on Z=0"),
    BVC("bvc", "Branch on oVerflow Clear", "branch on N=0"),
    BVS("bvs", "Branch on oVerflow Set ", "(S)-:=PC,P PC:=($FFFE)"),
    CLC("clc", "CLear Carry ", "branch on V=0"),
    CLD("cld", "CLear Decimal ", "branch on V=1"),
    CLI("cli", "CLear Interrupt ", "C:=0"),
    CLV("clv", "CLear oVerflow ", "D:=0"),
    CMP("cmp", "CoMPare accumulator", "I:=0"),
    CPX("cpx", "ComPare X register", "V:=0"),
    CPY("cpy", "ComPare Y register", "A-{adr}"),
    DCP("dcp", "", "X-{adr}"),
    DEC("dec", "DECrement memory", "Y-{adr}"),
    DEX("dex", "DEcrement X ", "{adr}:={adr}-1 A-{adr}"),
    DEY("dey", "DEcrement Y ", "{adr}:={adr}-1"),
    EOR("eor", "bitwise Exclusive OR", "X:=X-1"),
    INC("inc", "INCrement memory", "Y:=Y-1"),
    INX("inx", "INcrement X ", "A:=A exor {adr}"),
    INY("iny", "INcrement Y ", "{adr}:={adr}+1"),
    ISB("isb", "", "X:=X+1"),
    JAM("jam", "", "Y:=Y+1"),
    JMP("jmp", "JuMP", "GOTO %s"),
    JSR("jsr", "Jump to SubRoutine", "PC:={adr}"),
    LAE("lae", "", "(S)-:=PC PC:={adr}"),
    LAX("lax", "", "A,X,S:={adr}&S"),
    LDA("lda", "LoaD Accumulator", "A:=%s"),
    LDX("ldx", "LoaD X register", "A,X:=#{imm}"),
    LDY("ldy", "LoaD Y register", "A:={adr}"),
    LSR("lsr", "Logical Shift Right", "X:={adr}"),
    LXA("lxa", "", "Y:={adr}"),
    NOP("nop", "No OPeration", "{adr}:={adr}/2"),
    ORA("ora", "bitwise OR with Accumulator", ""),
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
    SEC("sec", "SEt Carry ", "A:=A-#{imm}"),
    SED("sed", "SEt Decimal ", "C:=1"),
    SEI("sei", "SEt Interrupt ", "D:=1"),
    SHA("sha", "", "I:=1"),
    SHS("shs", "", "{adr}:=X&H"),
    SHX("shx", "", "{adr}:=Y&H"),
    SHY("shy", "", "{adr}:={adr}*2 A:=A or {adr}"),
    SLO("slo", "", "{adr}:={adr}/2 A:=A exor {adr}"),
    SRE("sre", "", "{adr}:=A"),
    STA("sta", "STore Accumulator", "%s:=A"),
    STX("stx", "STore X register", "{adr}:=Y"),
    STY("sty", "STore Y register", "S:=A&X {adr}:=S&H"),
    TAX("tax", "Transfer A to X ", "X:=A"),
    TAY("tay", "Transfer A to Y ", "Y:=A"),
    TSX("tsx", "Transfer Stack ptr to X ", "X:=S"),
    TXA("txa", "Transfer X to A ", "A:=X"),
    TXS("txs", "Transfer X to Stack ptr ", "S:=X"),
    TYA("tya", "Transfer Y to A ", "A:=Y"),
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


