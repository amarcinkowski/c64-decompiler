package io.github.amarcinkowski.c64.asm;

import java.util.HashMap;

import static io.github.amarcinkowski.c64.asm.Addressing.*;
import static io.github.amarcinkowski.c64.asm.Mnemonic.*;
import static io.github.amarcinkowski.c64.utils.Numbers.hex;

/**
 * Opcodes taken and parsed from C=haking 1 ( http://www.ffd2.com/fridge/chacking/c=hacking1.txt )
 */

public enum Opcode {

    BRK_IMM(BRK, IMMEDIATE, 0x00,  1, 7),
    ORA_INX(ORA, INDX, 0x01,  6, 2),
    JAM1___(JAM, IMPLIED, 0x02,  1, 0),
    SLO_INX(SLO, INDX, 0x03,  2, 8),
    NOP00__(NOP, ZPAGE, 0x04,  2, 3),
    ORA_ZPG(ORA, ZPAGE, 0x05,  2, 3),
    ASL_ZPG(ASL, ZPAGE, 0x06,  2, 5),
    SLO_ZPG(SLO, ZPAGE, 0x07,  2, 5),
    PHP_IMP(PHP, IMPLIED, 0x08,  1, 3),
    ORA_IMM(ORA, IMMEDIATE, 0x09,  2, 2),
    ASL_ACC(ASL, ACCUMULATOR, 0x0A,  1, 2),
    ANC_IMM(ANC, IMMEDIATE, 0x0B,  1, 2),
    NOP01__(NOP, ABSOLUTE, 0x0C,  3, 4),
    ORA_ABS(ORA, ABSOLUTE, 0x0D,  3, 4),
    ASL_ABS(ASL, ABSOLUTE, 0x0E,  3, 6),
    SLO_ABS(SLO, ABSOLUTE, 0x0F,  3, 6),
    BPL_REL(BPL, RELATIVE, 0x10,  2, 2, 2),
    ORA_INY(ORA, INDY, 0x11,  2, 5, 1),
    JAM2___(JAM, IMPLIED, 0x12,  1, 0),
    SLO_INY(SLO, INDY, 0x13,  2, 8, 5),
    NOP02__(NOP, ZPAGEX, 0x14,  2, 4),
    ORA_ZPX(ORA, ZPAGEX, 0x15,  2, 4),
    ASL_ZPX(ASL, ZPAGEX, 0x16,  2, 6),
    SLO_ZPX(SLO, ZPAGEX, 0x17,  2, 6),
    CLC_IMP(CLC, IMPLIED, 0x18,  1, 2),
    ORA_ABY(ORA, ABSOLUTEY, 0x19,  3, 4, 1),
    NOP03__(NOP, IMPLIED, 0x1A,  1, 2),
    SLO_ABY(SLO, ABSOLUTEY, 0x1B,  3, 7),
    NOP04__(NOP, ABSOLUTEX, 0x1C,  2, 4, 1),
    ORA_ABX(ORA, ABSOLUTEX, 0x1D,  3, 4, 1),
    ASL_ABX(ASL, ABSOLUTEX, 0x1E,  3, 7),
    SLO_ABX(SLO, ABSOLUTEX, 0x1F,  3, 7),
    JSR_ABS(JSR, ABSOLUTE, 0x20,  3, 6),
    AND_INX(AND, INDX, 0x21,  2, 6),
    JAM3___(JAM, IMPLIED, 0x22,  1, 0),
    RLA_INX(RLA, INDX, 0x23,  2, 8),
    BIT_ZPG(BIT, ZPAGE, 0x24,  2, 3),
    AND_ZPG(AND, ZPAGE, 0x25,  2, 3),
    ROL_ZPG(ROL, ZPAGE, 0x26,  2, 5),
    RLA_ZPG(RLA, ZPAGE, 0x27,  2, 5, 5),
    PLP_IMP(PLP, IMPLIED, 0x28,  1, 4),
    AND_IMM(AND, IMMEDIATE, 0x29,  2, 2),
    ROL_ACC(ROL, ACCUMULATOR, 0x2A,  1, 2),
    ANC_IMM2(ANC, IMMEDIATE, 0x2B,  1, 2),
    BIT_ABS(BIT, ABSOLUTE, 0x2C,  3, 4),
    AND_ABS(AND, ABSOLUTE, 0x2D,  3, 4),
    ROL_ABS(ROL, ABSOLUTE, 0x2E,  3, 6),
    RLA_ABS(RLA, ABSOLUTE, 0x2F,  3, 6, 5),
    BMI_REL(BMI, RELATIVE, 0x30,  2, 2, 2),
    AND_INY(AND, INDY, 0x31,  2, 5, 1),
    JAM4___(JAM, IMPLIED, 0x32,  1, 0),
    RLA_INY(RLA, INDY, 0x33,  2, 8, 5),
    NOP05__(NOP, ZPAGEX, 0x34,  2, 4),
    AND_ZPX(AND, ZPAGEX, 0x35,  2, 4),
    ROL_ZPX(ROL, ZPAGEX, 0x36,  2, 6),
    RLA_ZPX(RLA, ZPAGEX, 0x37,  2, 6, 5),
    SEC_IMP(SEC, IMPLIED, 0x38,  1, 2),
    AND_ABY(AND, ABSOLUTEY, 0x39,  3, 4, 1),
    NOP06__(NOP, IMPLIED, 0x3A,  1, 2),
    RLA_ABY(RLA, ABSOLUTEY, 0x3B,  3, 7, 5),
    NOP07__(NOP, ABSOLUTEX, 0x3C,  3, 4, 1),
    AND_ABX(AND, ABSOLUTEX, 0x3D,  3, 4, 1),
    ROL_ABX(ROL, ABSOLUTEX, 0x3E,  3, 7),
    RLA_ABX(RLA, ABSOLUTEX, 0x3F,  3, 7, 5),
    RTI_IMP(RTI, IMPLIED, 0x40,  1, 6),
    EOR_INX(EOR, INDX, 0x41,  2, 6),
    JAM5___(JAM, IMPLIED, 0x42,  1, 0),
    SRE_INX(SRE, INDX, 0x43,  2, 8),
    NOP08__(NOP, ZPAGE, 0x44,  2, 3),
    EOR_ZPG(EOR, ZPAGE, 0x45,  2, 3),
    LSR_ABX(LSR, ABSOLUTEX, 0x46,  3, 7),
    SRE_ZPG(SRE, ZPAGE, 0x47,  2, 5),
    PHA_IMP(PHA, IMPLIED, 0x48,  1, 3),
    EOR_IMM(EOR, IMMEDIATE, 0x49,  2, 2),
    LSR_ACC(LSR, ACCUMULATOR, 0x4A,  1, 2),
    ASR_IMM(ASR, IMMEDIATE, 0x4B,  1, 2),
    JMP_ABS(JMP, ABSOLUTE, 0x4C,  3, 3),
    EOR_ABS(EOR, ABSOLUTE, 0x4D,  3, 4),
    LSR_ABS(LSR, ABSOLUTE, 0x4E,  3, 6),
    SRE_ABS(SRE, ABSOLUTE, 0x4F,  3, 6),
    BVC_REL(BVC, RELATIVE, 0x50,  2, 2, 2),
    EOR_INY(EOR, INDY, 0x51,  2, 5, 1),
    JAM6___(JAM, IMPLIED, 0x52,  1, 0),
    SRE_INY(SRE, INDY, 0x53,  2, 8),
    NOP09__(NOP, ZPAGEX, 0x54,  2, 4),
    EOR_ZPX(EOR, ZPAGEX, 0x55,  2, 4),
    LSR_ZPX(LSR, ZPAGEX, 0x56,  2, 6),
    SRE_ZPX(SRE, ZPAGEX, 0x57,  2, 6),
    CLI_IMP(CLI, IMPLIED, 0x58,  1, 2),
    EOR_ABY(EOR, ABSOLUTEY, 0x59,  3, 4, 1),
    NOP10__(NOP, IMPLIED, 0x5A,  1, 2),
    SRE_ABY(SRE, ABSOLUTEY, 0x5B,  3, 7),
    NOP11__(NOP, ABSOLUTEX, 0x5C,  3, 4, 1),
    EOR_ABX(EOR, ABSOLUTEX, 0x5D,  3, 4, 1),
    LSR_AX2(LSR, ABSOLUTEX, 0x5E,  3, 7),
    SRE_ABX(SRE, ABSOLUTEX, 0x5F,  3, 7),
    RTS_IMP(RTS, IMPLIED, 0x60,  1, 6),
    ADC_INX(ADC, INDX, 0x61,  2, 6),
    JAM7___(JAM, IMPLIED, 0x62,  1, 0),
    RRA_INX(RRA, INDX, 0x63,  2, 8, 5),
    NOP12__(NOP, ZPAGE, 0x64,  2, 3),
    ADC_ZPG(ADC, ZPAGE, 0x65,  2, 3),
    ROR_ZPG(ROR, ZPAGE, 0x66,  2, 5),
    RRA_ZPG(RRA, ZPAGE, 0x67,  2, 5, 5),
    PLA_IMP(PLA, IMPLIED, 0x68,  1, 4),
    ADC_IMM(ADC, IMMEDIATE, 0x69,  2, 2),
    ROR_ACC(ROR, ACCUMULATOR, 0x6A,  1, 2),
    ARR_IMM(ARR, IMMEDIATE, 0x6B,  1, 2, 5),
    JMP_IND(JMP, INDIRECT, 0x6C,  3, 5),
    ADC_ABS(ADC, ABSOLUTE, 0x6D,  3, 4),
    ROR_ABS(ROR, ABSOLUTE, 0x6E,  3, 6),
    RRA_ABS(RRA, ABSOLUTE, 0x6F,  3, 6, 5),
    BVS_REL(BVS, RELATIVE, 0x70,  2, 2, 2),
    ADC_INY(ADC, INDY, 0x71,  2, 5, 1),
    JAM8___(JAM, IMPLIED, 0x72,  1, 0),
    RRA_INY(RRA, INDY, 0x73,  2, 8, 5),
    NOP13__(NOP, ZPAGEX, 0x74,  2, 4),
    ADC_ZPX(ADC, ZPAGEX, 0x75,  2, 4),
    ROR_ZPX(ROR, ZPAGEX, 0x76,  2, 6),
    RRA_ZPX(RRA, ZPAGEX, 0x77,  2, 6, 5),
    SEI_IMP(SEI, IMPLIED, 0x78,  1, 2),
    ADC_ABY(ADC, ABSOLUTEY, 0x79,  3, 4, 1),
    NOP14__(NOP, IMPLIED, 0x7A,  1, 2),
    RRA_ABY(RRA, ABSOLUTEY, 0x7B,  3, 7, 5),
    NOP15__(NOP, ABSOLUTEX, 0x7C,  3, 4, 1),
    ADC_ABX(ADC, ABSOLUTEX, 0x7D,  3, 4, 1),
    ROR_ABX(ROR, ABSOLUTEX, 0x7E,  3, 7),
    RRA_ABX(RRA, ABSOLUTEX, 0x7F,  3, 7, 5),
    NOP16__(NOP, IMMEDIATE, 0x80,  2, 2),
    STA_INX(STA, INDX, 0x81,  2, 6),
    NOP17__(NOP, IMMEDIATE, 0x82,  2, 2),
    SAX_INX(SAX, INDX, 0x83,  2, 6),
    STY_ZPG(STY, ZPAGE, 0x84,  2, 3),
    STA_ZPG(STA, ZPAGE, 0x85,  2, 3),
    STX_ZPG(STX, ZPAGE, 0x86,  2, 3),
    SAX_ZPG(SAX, ZPAGE, 0x87,  2, 3),
    DEY_IMP(DEY, IMPLIED, 0x88,  1, 2),
    NOP18__(NOP, IMMEDIATE, 0x89,  2, 2),
    TXA_IMP(TXA, IMPLIED, 0x8A,  1, 2),
    ANE_IMM(ANE, IMMEDIATE, 0x8B,  2, 2, 4),
    STY_ABS(STY, ABSOLUTE, 0x8C,  3, 4),
    STA_ABS(STA, ABSOLUTE, 0x8D,  3, 4),
    STX_ABS(STX, ABSOLUTE, 0x8E,  3, 4),
    SAX_ABS(SAX, ABSOLUTE, 0x8F,  3, 4),
    BCC_REL(BCC, RELATIVE, 0x90,  2, 2, 2),
    STA_INY(STA, INDY, 0x91,  2, 6),
    JAM9___(JAM, IMPLIED, 0x92,  1, 0),
    SHA_ABX(SHA, ABSOLUTEX, 0x93,  3, 6, 3),
    STY_ZPX(STY, ZPAGEX, 0x94,  2, 4),
    STA_ZPX(STA, ZPAGEX, 0x95,  2, 4),
    SAX_ZPY(SAX, ZPAGEY, 0x97,  2, 4),
    STX_ZPY(STX, ZPAGEY, 0x96,  2, 4),
    TYA_IMP(TYA, IMPLIED, 0x98,  1, 2),
    STA_ABY(STA, ABSOLUTEY, 0x99,  3, 5),
    TXS_IMP(TXS, IMPLIED, 0x9A,  1, 2),
    SHS_ABY(SHS, ABSOLUTEY, 0x9B,  3, 5),
    SHY_ABY(SHY, ABSOLUTEY, 0x9C,  3, 5, 3),
    STA_ABX(STA, ABSOLUTEX, 0x9D,  3, 5),
    SHX_ABX(SHX, ABSOLUTEX, 0x9E,  3, 5, 3),
    SHA_ABY(SHA, ABSOLUTEY, 0x9F,  3, 5, 3),
    LDY_IMM(LDY, IMMEDIATE, 0xA0,  2, 2),
    LDA_INX(LDA, INDX, 0xA1,  2, 6),
    LDX_IMM(LDX, IMMEDIATE, 0xA2,  2, 2),
    LAX_INX(LAX, INDX, 0xA3,  2, 6),
    LDY_ZPG(LDY, ZPAGE, 0xA4,  2, 3),
    LDA_ZPG(LDA, ZPAGE, 0xA5,  2, 3),
    LDX_ZPG(LDX, ZPAGE, 0xA6,  2, 3),
    LAX_ZPG(LAX, ZPAGE, 0xA7,  2, 3),
    TAY_IMP(TAY, IMPLIED, 0xA8,  1, 2),
    LDA_IMM(LDA, IMMEDIATE, 0xA9,  2, 2),
    TAX_IMP(TAX, IMPLIED, 0xAA,  1, 2),
    LXA_IMM(LXA, IMMEDIATE, 0xAB,  1, 2),
    LDY_ABS(LDY, ABSOLUTE, 0xAC,  3, 4),
    LDA_ABS(LDA, ABSOLUTE, 0xAD,  3, 4),
    LDX_ABS(LDX, ABSOLUTE, 0xAE,  3, 4),
    LAX_ABS(LAX, ABSOLUTE, 0xAF,  3, 4),
    BCS_REL(BCS, RELATIVE, 0xB0,  2, 2, 2),
    LDA_INY(LDA, INDY, 0xB1,  2, 5, 1),
    JAM10__(JAM, IMPLIED, 0xB2,  1, 0),
    LAX_INY(LAX, INDY, 0xB3,  2, 5, 1),
    LDY_ZPX(LDY, ZPAGEX, 0xB4,  2, 4),
    LDA_ZPX(LDA, ZPAGEX, 0xB5,  2, 4),
    LDX_ZPY(LDX, ZPAGEY, 0xB6,  2, 4),
    LAX_ZPY(LAX, ZPAGEY, 0xB7,  2, 4),
    CLV_IMP(CLV, IMPLIED, 0xB8,  1, 2),
    LDA_ABY(LDA, ABSOLUTEY, 0xB9,  3, 4, 1),
    TSX_IMP(TSX, IMPLIED, 0xBA,  1, 2),
    LAE_ABY(LAE, ABSOLUTEY, 0xBB,  3, 4, 1),
    LDY_ABX(LDY, ABSOLUTEX, 0xBC,  3, 4, 1),
    LDA_ABX(LDA, ABSOLUTEX, 0xBD,  3, 4, 1),
    LDX_ABY(LDX, ABSOLUTEY, 0xBE,  3, 4, 1),
    LAX_ABY(LAX, ABSOLUTEY, 0xBF,  3, 4, 1),
    CPY_IMM(CPY, IMMEDIATE, 0xC0,  2, 2),
    CMP_INX(CMP, INDX, 0xC1,  2, 6),
    NOP19__(NOP, IMMEDIATE, 0xC2,  2, 2),
    DCP_INX(DCP, INDX, 0xC3,  2, 8),
    CPY_ZPG(CPY, ZPAGE, 0xC4,  2, 3),
    CMP_ZPG(CMP, ZPAGE, 0xC5,  2, 3),
    DEC_ZPG(DEC, ZPAGE, 0xC6,  2, 5),
    DCP_ZPG(DCP, ZPAGE, 0xC7,  2, 5),
    INY_IMP(INY, IMPLIED, 0xC8,  1, 2),
    CMP_IMM(CMP, IMMEDIATE, 0xC9,  2, 2),
    DEX_IMP(DEX, IMPLIED, 0xCA,  1, 2),
    SBX_IMM(SBX, IMMEDIATE, 0xCB,  2, 2),
    CPY_ABS(CPY, ABSOLUTE, 0xCC,  3, 4),
    CMP_ABS(CMP, ABSOLUTE, 0xCD,  3, 4),
    DEC_ABS(DEC, ABSOLUTE, 0xCE,  3, 6),
    DCP_ABS(DCP, ABSOLUTE, 0xCF,  3, 6),
    BNE_REL(BNE, RELATIVE, 0xD0,  2, 2, 2),
    CMP_INY(CMP, INDY, 0xD1,  2, 5, 1),
    JAM11__(JAM, IMPLIED, 0xD2,  1, 0),
    DCP_INY(DCP, INDY, 0xD3,  2, 8),
    NOP20__(NOP, ZPAGEX, 0xD4,  2, 4),
    CMP_ZPX(CMP, ZPAGEX, 0xD5,  2, 4),
    DEC_ZPX(DEC, ZPAGEX, 0xD6,  2, 6),
    DCP_ZPX(DCP, ZPAGEX, 0xD7,  2, 6),
    CLD_IMP(CLD, IMPLIED, 0xD8,  1, 2),
    CMP_ABY(CMP, ABSOLUTEY, 0xD9,  3, 4, 1),
    NOP21__(NOP, IMPLIED, 0xDA,  1, 2),
    DCP_ABY(DCP, ABSOLUTEY, 0xDB,  3, 7),
    NOP22__(NOP, ABSOLUTEX, 0xDC,  3, 4, 1),
    CMP_ABX(CMP, ABSOLUTEX, 0xDD,  3, 4, 1),
    DEC_ABX(DEC, ABSOLUTEX, 0xDE,  3, 7),
    DCP_ABX(DCP, ABSOLUTEX, 0xDF,  3, 7),
    CPX_IMM(CPX, IMMEDIATE, 0xE0,  2, 2),
    SBC_INX(SBC, INDX, 0xE1,  2, 6),
    NOP23__(NOP, IMMEDIATE, 0xE2,  2, 2),
    ISB_INX(ISB, INDX, 0xE3,  3, 8, 1),
    CPX_ZPG(CPX, ZPAGE, 0xE4,  2, 3),
    SBC_ZPG(SBC, ZPAGE, 0xE5,  2, 3),
    INC_ZPG(INC, ZPAGE, 0xE6,  2, 5),
    ISB_ZPG(ISB, ZPAGE, 0xE7,  2, 5),
    INX_IMP(INX, IMPLIED, 0xE8,  1, 2),
    SBC_IMM(SBC, IMMEDIATE, 0xE9,  2, 2),
    NOP24__(NOP, IMPLIED, 0xEA,  1, 2),
    SBC_IMM2(SBC, IMMEDIATE, 0xEB,  1, 2),
    SBC_ABS(SBC, ABSOLUTE, 0xED,  3, 4),
    CPX_ABS(CPX, ABSOLUTE, 0xEC,  3, 4),
    INC_ABS(INC, ABSOLUTE, 0xEE,  3, 6),
    ISB_ABS(ISB, ABSOLUTE, 0xEF,  3, 6),
    BEQ_REL(BEQ, RELATIVE, 0xF0,  2, 2, 2),
    SBC_INY(SBC, INDY, 0xF1,  2, 5, 1),
    JAM12__(JAM, IMPLIED, 0xF2,  1, 0),
    ISB_INY(ISB, INDY, 0xF3,  2, 8),
    NOP25__(NOP, ZPAGEX, 0xF4,  2, 4),
    SBC_ZPX(SBC, ZPAGEX, 0xF5,  2, 4),
    INC_ZPX(INC, ZPAGEX, 0xF6,  2, 6),
    ISB_ZPX(ISB, ZPAGEX, 0xF7,  2, 6),
    SED_IMP(SED, IMPLIED, 0xF8,  1, 2),
    SBC_ABY(SBC, ABSOLUTEY, 0xF9,  3, 4, 1),
    NOP26__(NOP, IMPLIED, 0xFA,  1, 2),
    ISB_ABY(ISB, ABSOLUTEY, 0xFB,  3, 7),
    NOP27__(NOP, ABSOLUTEX, 0xFC,  3, 4, 1),
    SBC_ABX(SBC, ABSOLUTEX, 0xFD,  3, 4, 1),
    INC_ABX(INC, ABSOLUTEX, 0xFE,  3, 7),
    ISB_ABX(ISB, ABSOLUTEX, 0xFF,  3, 7),
    ;

    public Mnemonic mnemonic;
    public Addressing addressing;
    public int hex;
    public int length;
    public int time;
    public int timeb;


    static HashMap<Integer, Opcode> hex2opcode = new HashMap<>();

    static {
        for (Opcode o : values()) {
            hex2opcode.put(o.hex, o);
        }
    }

    public static Opcode getByHexValue(int mnemonic) {
        return hex2opcode.get(mnemonic);
    }
    public static Opcode getByHexString(String mnemonic) {
        return getByHexValue(Integer.parseInt(mnemonic, 16));
    }
    public static Opcode getByBytecode(byte asm) {
        String hex = hex(asm);
        return getByHexString(hex);
    }

    Opcode(Mnemonic type, Addressing addressing, int hex, int bytes, int time, int timeb) {
        this.mnemonic = type;
        this.hex = hex;
        this.addressing = addressing;
        this.length = bytes;
        this.time = time;
        this.timeb = timeb;
    }

    Opcode(Mnemonic type, Addressing addressing, int hex, int bytes, int time) {
        this.mnemonic = type;
        this.hex = hex;
        this.addressing = addressing;
        this.length = bytes;
        this.time = time;
        this.timeb = 0;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
