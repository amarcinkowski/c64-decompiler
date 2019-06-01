package io.github.amarcinkowski.c64.asm;

import java.util.HashMap;

import static io.github.amarcinkowski.c64.asm.Addressing.*;
import static io.github.amarcinkowski.c64.asm.Mnemonic.*;

/**
 * Opcodes taken and parsed from C=haking 1 ( http://www.ffd2.com/fridge/chacking/c=hacking1.txt )
 */

@SuppressWarnings("unused")
public enum Opcode {

    BRK_IMM(BRK, IMMEDIATE, true, 0x00, "Stack <- PC, PC <- ($fffe)", 1, 7),
    ORA_INX(ORA, INDX, true, 0x01, "A <- (A) V M", 6, 2),
    JAM1___(JAM, IMPLIED, false, 0x02, "[locks up machine]", 1, 0),
    SLO_INX(SLO, INDX, false, 0x03, "M <- (M >> 1) + A + C", 2, 8),
    NOP00__(NOP, ZPAGE, false, 0x04, "[no operation]", 2, 3),
    ORA_ZPG(ORA, ZPAGE, true, 0x05, "A <- (A) V M", 2, 3),
    ASL_ZPG(ASL, ZPAGE, true, 0x06, "C <- A7, A <- (A) << 1", 2, 5),
    SLO_ZPG(SLO, ZPAGE, false, 0x07, "M <- (M >> 1) + A + C", 2, 5),
    PHP_IMP(PHP, IMPLIED, true, 0x08, "Stack <- (P)", 1, 3),
    ORA_IMM(ORA, IMMEDIATE, true, 0x09, "A <- (A) V M", 2, 2),
    ASL_ACC(ASL, ACCUMULATOR, true, 0x0A, "C <- A7, A <- (A) << 1", 1, 2),
    ANC_IMM(ANC, IMMEDIATE, false, 0x0B, "A <- A^M, C=~A7", 1, 2),
    NOP01__(NOP, ABSOLUTE, false, 0x0C, "[no operation]", 3, 4),
    ORA_ABS(ORA, ABSOLUTE, true, 0x0D, "A <- (A) V M", 3, 4),
    ASL_ABS(ASL, ABSOLUTE, true, 0x0E, "C <- A7, A <- (A) << 1", 3, 6),
    SLO_ABS(SLO, ABSOLUTE, false, 0x0F, "M <- (M >> 1) + A + C", 3, 6),
    BPL_REL(BPL, RELATIVE, true, 0x10, "if N=0, PC = PC + offset", 2, 2, 2),
    ORA_INY(ORA, INDY, true, 0x11, "A <- (A) V M", 2, 5, 1),
    JAM2___(JAM, IMPLIED, false, 0x12, "[locks up machine]", 1, 0),
    SLO_INY(SLO, INDY, false, 0x13, "M <- (M >. 1) + A + C", 2, 8, 5),
    NOP02__(NOP, ZPAGEX, false, 0x14, "[no operation]", 2, 4),
    ORA_ZPX(ORA, ZPAGEX, true, 0x15, "A <- (A) V M", 2, 4),
    ASL_ZPX(ASL, ZPAGEX, true, 0x16, "C <- A7, A <- (A) << 1", 2, 6),
    SLO_ZPX(SLO, ZPAGEX, false, 0x17, "M <- (M >> 1) + A + C", 2, 6),
    CLC_IMP(CLC, IMPLIED, true, 0x18, "C <- 0", 1, 2),
    ORA_ABY(ORA, ABSOLUTEY, true, 0x19, "A <- (A) V M", 3, 4, 1),
    NOP03__(NOP, IMPLIED, false, 0x1A, "[no operation]", 1, 2),
    SLO_ABY(SLO, ABSOLUTEY, false, 0x1B, "M <- (M >> 1) + A + C", 3, 7),
    NOP04__(NOP, ABSOLUTEX, false, 0x1C, "[no operation]", 2, 4, 1),
    ORA_ABX(ORA, ABSOLUTEX, true, 0x1D, "A <- (A) V M", 3, 4, 1),
    ASL_ABX(ASL, ABSOLUTEX, true, 0x1E, "C <- A7, A <- (A) << 1", 3, 7),
    SLO_ABX(SLO, ABSOLUTEX, false, 0x1F, "M <- (M >> 1) + A + C", 3, 7),
    JSR_ABS(JSR, ABSOLUTE, true, 0x20, "Stack <- PC, PC <- Address", 3, 6),
    AND_INX(AND, INDX, true, 0x21, "A <- (A)^M", 2, 6),
    JAM3___(JAM, IMPLIED, false, 0x22, "[locks up machine]", 1, 0),
    RLA_INX(RLA, INDX, false, 0x23, "M <- (M << 1)^(A)", 2, 8),
    BIT_ZPG(BIT, ZPAGE, true, 0x24, "Z <- ~(A^M) N<-M7 V<-M6", 2, 3),
    AND_ZPG(AND, ZPAGE, true, 0x25, "A <- (A)^M", 2, 3),
    ROL_ZPG(ROL, ZPAGE, true, 0x26, "C <- A7 & A <- A << 1 + C", 2, 5),
    RLA_ZPG(RLA, ZPAGE, false, 0x27, "M <- (M << 1)^(A)", 2, 5, 5),
    PLP_IMP(PLP, IMPLIED, true, 0x28, "A <- (Stack)", 1, 4),
    AND_IMM(AND, IMMEDIATE, true, 0x29, "A <- (A)^M", 2, 2),
    ROL_ACC(ROL, ACCUMULATOR, true, 0x2A, "C <- A7 & A <- A << 1 + C", 1, 2),
    ANC_IMM2(ANC, IMMEDIATE, false, 0x2B, "A <- A^M, C <- ~A7", 1, 2),
    BIT_ABS(BIT, ABSOLUTE, true, 0x2C, "Z <- ~(A^M) N<-M7 V<-M6", 3, 4),
    AND_ABS(AND, ABSOLUTE, true, 0x2D, "A <- (A)^M", 3, 4),
    ROL_ABS(ROL, ABSOLUTE, true, 0x2E, "C <- A7 & A <- A << 1 + C", 3, 6),
    RLA_ABS(RLA, ABSOLUTE, false, 0x2F, "M <- (M << 1)^(A)", 3, 6, 5),
    BMI_REL(BMI, RELATIVE, true, 0x30, "if N=1, PC = PC + offset", 2, 2, 2),
    AND_INY(AND, INDY, true, 0x31, "A <- (A)^M", 2, 5, 1),
    JAM4___(JAM, IMPLIED, false, 0x32, "[locks up machine]", 1, 0),
    RLA_INY(RLA, INDY, false, 0x33, "M <- (M << 1)^(A)", 2, 8, 5),
    NOP05__(NOP, ZPAGEX, false, 0x34, "[no operation]", 2, 4),
    AND_ZPX(AND, ZPAGEX, true, 0x35, "A <- (A)^M", 2, 4),
    ROL_ZPX(ROL, ZPAGEX, true, 0x36, "C <- A7 & A <- A << 1 + C", 2, 6),
    RLA_ZPX(RLA, ZPAGEX, false, 0x37, "M <- (M << 1)^(A)", 2, 6, 5),
    SEC_IMP(SEC, IMPLIED, true, 0x38, "C <- 1", 1, 2),
    AND_ABY(AND, ABSOLUTEY, true, 0x39, "A <- (A)^M", 3, 4, 1),
    NOP06__(NOP, IMPLIED, false, 0x3A, "[no operation]", 1, 2),
    RLA_ABY(RLA, ABSOLUTEY, false, 0x3B, "M <- (M << 1)^(A)", 3, 7, 5),
    NOP07__(NOP, ABSOLUTEX, false, 0x3C, "[no operation]", 3, 4, 1),
    AND_ABX(AND, ABSOLUTEX, true, 0x3D, "A <- (A)^M", 3, 4, 1),
    ROL_ABX(ROL, ABSOLUTEX, true, 0x3E, "C <- A7 & A <- A << 1 + C", 3, 7),
    RLA_ABX(RLA, ABSOLUTEX, false, 0x3F, "M <- (M << 1)^(A)", 3, 7, 5),
    RTI_IMP(RTI, IMPLIED, true, 0x40, "P <- (Stack), PC <-(Stack)", 1, 6),
    EOR_INX(EOR, INDX, true, 0x41, "A <- (A) ⊻ M", 2, 6),
    JAM5___(JAM, IMPLIED, false, 0x42, "[locks up machine]", 1, 0),
    SRE_INX(SRE, INDX, false, 0x43, "M <- (M >> 1) ⊻ A", 2, 8),
    NOP08__(NOP, ZPAGE, false, 0x44, "[no operation]", 2, 3),
    EOR_ZPG(EOR, ZPAGE, true, 0x45, "A <- (A) ⊻ M", 2, 3),
    LSR_ABX(LSR, ABSOLUTEX, true, 0x46, "C <- A0, A <- (A) >> 1", 3, 7),
    SRE_ZPG(SRE, ZPAGE, false, 0x47, "M <- (M >> 1) ⊻ A", 2, 5),
    PHA_IMP(PHA, IMPLIED, true, 0x48, "Stack <- (A)", 1, 3),
    EOR_IMM(EOR, IMMEDIATE, true, 0x49, "A <- (A) ⊻ M", 2, 2),
    LSR_ACC(LSR, ACCUMULATOR, true, 0x4A, "C <- A0, A <- (A) >> 1", 1, 2),
    ASR_IMM(ASR, IMMEDIATE, false, 0x4B, "A <- [(A^M) >> 1]", 1, 2),
    JMP_ABS(JMP, ABSOLUTE, true, 0x4C, "PC <- Address", 3, 3),
    EOR_ABS(EOR, ABSOLUTE, true, 0x4D, "A <- (A) ⊻ M", 3, 4),
    LSR_ABS(LSR, ABSOLUTE, true, 0x4E, "C <- A0, A <- (A) >> 1", 3, 6),
    SRE_ABS(SRE, ABSOLUTE, false, 0x4F, "M <- (M >> 1) ⊻ A", 3, 6),
    BVC_REL(BVC, RELATIVE, true, 0x50, "if V=0, PC = PC + offset", 2, 2, 2),
    EOR_INY(EOR, INDY, true, 0x51, "A <- (A) ⊻ M", 2, 5, 1),
    JAM6___(JAM, IMPLIED, false, 0x52, "[locks up machine]", 1, 0),
    SRE_INY(SRE, INDY, false, 0x53, "M <- (M >> 1) ⊻ A", 2, 8),
    NOP09__(NOP, ZPAGEX, false, 0x54, "[no operation]", 2, 4),
    EOR_ZPX(EOR, ZPAGEX, true, 0x55, "A <- (A) ⊻ M", 2, 4),
    LSR_ZPX(LSR, ZPAGEX, true, 0x56, "C <- A0, A <- (A) >> 1", 2, 6),
    SRE_ZPX(SRE, ZPAGEX, false, 0x57, "M <- (M >> 1) ⊻ A", 2, 6),
    CLI_IMP(CLI, IMPLIED, true, 0x58, "I <- 0", 1, 2),
    EOR_ABY(EOR, ABSOLUTEY, true, 0x59, "A <- (A) ⊻ M", 3, 4, 1),
    NOP10__(NOP, IMPLIED, false, 0x5A, "[no operation]", 1, 2),
    SRE_ABY(SRE, ABSOLUTEY, false, 0x5B, "M <- (M >> 1) ⊻ A", 3, 7),
    NOP11__(NOP, ABSOLUTEX, false, 0x5C, "[no operation]", 3, 4, 1),
    EOR_ABX(EOR, ABSOLUTEX, true, 0x5D, "A <- (A) ⊻ M", 3, 4, 1),
    // MISSING ???
    LSR_AXxxx(LSR, ABSOLUTEX, true, 0x5E, "??", 3, 7),
    SRE_ABX(SRE, ABSOLUTEX, false, 0x5F, "M <- (M >> 1) ⊻ A", 3, 7),
    RTS_IMP(RTS, IMPLIED, true, 0x60, "PC <- (Stack)", 1, 6),
    ADC_INX(ADC, INDX, true, 0x61, "A <- (A) + M + C", 2, 6),
    JAM7___(JAM, IMPLIED, false, 0x62, "[locks up machine]", 1, 0),
    RRA_INX(RRA, INDX, false, 0x63, "M <- (M >> 1) + (A) + C", 2, 8, 5),
    NOP12__(NOP, ZPAGE, false, 0x64, "[no operation]", 2, 3),
    ADC_ZPG(ADC, ZPAGE, true, 0x65, "A <- (A) + M + C", 2, 3),
    ROR_ZPG(ROR, ZPAGE, true, 0x66, "C<-A0 & A<- (A7=C + A>>1)", 2, 5),
    RRA_ZPG(RRA, ZPAGE, false, 0x67, "M <- (M >> 1) + (A) + C", 2, 5, 5),
    PLA_IMP(PLA, IMPLIED, true, 0x68, "A <- (Stack)", 1, 4),
    ADC_IMM(ADC, IMMEDIATE, true, 0x69, "A <- (A) + M + C", 2, 2),
    ROR_ACC(ROR, ACCUMULATOR, true, 0x6A, "C<-A0 & A<- (A7=C + A>>1)", 1, 2),
    ARR_IMM(ARR, IMMEDIATE, false, 0x6B, "A <- [(A^M) >> 1]", 1, 2, 5),
    JMP_INDIRECT(JMP, INDIRECT, true, 0x6C, "PC <- Address", 3, 5),
    ADC_ABS(ADC, ABSOLUTE, true, 0x6D, "A <- (A) + M + C", 3, 4),
    ROR_ABS(ROR, ABSOLUTE, true, 0x6E, "C<-A0 & A<- (A7=C + A>>1)", 3, 6),
    RRA_ABS(RRA, ABSOLUTE, false, 0x6F, "M <- (M >> 1) + (A) + C", 3, 6, 5),
    BVS_REL(BVS, RELATIVE, true, 0x70, "if V=1, PC = PC + offset", 2, 2, 2),
    ADC_INY(ADC, INDY, true, 0x71, "A <- (A) + M + C", 2, 5, 1),
    JAM8___(JAM, IMPLIED, false, 0x72, "[locks up machine]", 1, 0),
    RRA_INY(RRA, INDY, false, 0x73, "M <- (M >> 1) + (A) + C", 2, 8, 5),
    NOP13__(NOP, ZPAGEX, false, 0x74, "[no operation]", 2, 4),
    ADC_ZPX(ADC, ZPAGEX, true, 0x75, "A <- (A) + M + C", 2, 4),
    ROR_ZPX(ROR, ZPAGEX, true, 0x76, "C<-A0 & A<- (A7=C + A>>1)", 2, 6),
    RRA_ZPX(RRA, ZPAGEX, false, 0x77, "M <- (M >> 1) + (A) + C", 2, 6, 5),
    SEI_IMP(SEI, IMPLIED, true, 0x78, "I <- 1", 1, 2),
    ADC_ABY(ADC, ABSOLUTEY, true, 0x79, "A <- (A) + M + C", 3, 4, 1),
    NOP14__(NOP, IMPLIED, false, 0x7A, "[no operation]", 1, 2),
    RRA_ABY(RRA, ABSOLUTEY, false, 0x7B, "M <- (M >> 1) + (A) + C", 3, 7, 5),
    NOP15__(NOP, ABSOLUTEX, false, 0x7C, "[no operation]", 3, 4, 1),
    ADC_ABX(ADC, ABSOLUTEX, true, 0x7D, "A <- (A) + M + C", 3, 4, 1),
    ROR_ABX(ROR, ABSOLUTEX, true, 0x7E, "C<-A0 & A<- (A7=C + A>>1)", 3, 7),
    RRA_ABX(RRA, ABSOLUTEX, false, 0x7F, "M <- (M >> 1) + (A) + C", 3, 7, 5),
    NOP16__(NOP, IMMEDIATE, false, 0x80, "[no operation]", 2, 2),
    STA_INX(STA, INDX, true, 0x81, "M <- (A)", 2, 6),
    NOP17__(NOP, IMMEDIATE, false, 0x82, "[no operation]", 2, 2),
    SAX_INX(SAX, INDX, false, 0x83, "M <- (A)^(X)", 2, 6),
    STY_ZPG(STY, ZPAGE, true, 0x84, "M <- (Y)", 2, 3),
    STA_ZPG(STA, ZPAGE, true, 0x85, "M <- (A)", 2, 3),
    STX_ZPG(STX, ZPAGE, true, 0x86, "M <- (X)", 2, 3),
    SAX_ZPG(SAX, ZPAGE, false, 0x87, "M <- (A)^(X)", 2, 3),
    DEY_IMP(DEY, IMPLIED, true, 0x88, "Y <- (Y) - 1", 1, 2),
    NOP18__(NOP, IMMEDIATE, false, 0x89, "[no operation]", 2, 2),
    TXA_IMP(TXA, IMPLIED, true, 0x8A, "A <- (X)", 1, 2),
    ANE_IMM(ANE, IMMEDIATE, false, 0x8B, "M <-[(A)v$EE]^(X)^(M)", 2, 2, 4),
    STY_ABS(STY, ABSOLUTE, true, 0x8C, "M <- (Y)", 3, 4),
    STA_ABS(STA, ABSOLUTE, true, 0x8D, "M <- (A)", 3, 4),
    STX_ABS(STX, ABSOLUTE, true, 0x8E, "M <- (X)", 3, 4),
    SAX_ABS(SAX, ABSOLUTE, false, 0x8F, "M <- (A)^(X)", 3, 4),
    BCC_REL(BCC, RELATIVE, true, 0x90, "if C=0, PC = PC + offset", 2, 2, 2),
    STA_INY(STA, INDY, true, 0x91, "M <- (A)", 2, 6),
    JAM9___(JAM, IMPLIED, false, 0x92, "[locks up machine]", 1, 0),
    SHA_ABX(SHA, ABSOLUTEX, false, 0x93, "M <- (A)^(X)^(PCH+1)", 3, 6, 3),
    STY_ZPX(STY, ZPAGEX, true, 0x94, "M <- (Y)", 2, 4),
    STA_ZPX(STA, ZPAGEX, true, 0x95, "M <- (A)", 2, 4),
    SAX_ZPY(SAX, ZPAGEY, false, 0x97, "M <- (A)^(X)", 2, 4),
    STX_ZPY(STX, ZPAGEY, true, 0x96, "M <- (X)", 2, 4),
    TYA_IMP(TYA, IMPLIED, true, 0x98, "A <- (Y)", 1, 2),
    STA_ABY(STA, ABSOLUTEY, true, 0x99, "M <- (A)", 3, 5),
    TXS_IMP(TXS, IMPLIED, true, 0x9A, "S <- (X)", 1, 2),
    SHS_ABY(SHS, ABSOLUTEY, false, 0x9B, "X <- (A)^(X), S <- (X) \n M <- (X)^(PCH+1)", 3, 5),
    SHY_ABY(SHY, ABSOLUTEY, false, 0x9C, "M <- (Y)^(PCH+1)", 3, 5, 3),
    STA_ABX(STA, ABSOLUTEX, true, 0x9D, "M <- (A)", 3, 5),
    SHX_ABX(SHX, ABSOLUTEX, false, 0x9E, "M <- (X)^(PCH+1)", 3, 5, 3),
    SHA_ABY(SHA, ABSOLUTEY, false, 0x9F, "M <- (A)^(X)^(PCH+1)", 3, 5, 3),
    LDY_IMM(LDY, IMMEDIATE, true, 0xA0, "Y <- M", 2, 2),
    LDA_INX(LDA, INDX, true, 0xA1, "A <- M", 2, 6),
    LDX_IMM(LDX, IMMEDIATE, true, 0xA2, "X <- M", 2, 2),
    LAX_INX(LAX, INDX, false, 0xA3, "A <- M, X <- M", 2, 6),
    LDY_ZPG(LDY, ZPAGE, true, 0xA4, "Y <- M", 2, 3),
    LDA_ZPG(LDA, ZPAGE, true, 0xA5, "A <- M", 2, 3),
    LDX_ZPG(LDX, ZPAGE, true, 0xA6, "X <- M", 2, 3),
    LAX_ZPG(LAX, ZPAGE, false, 0xA7, "A <- M, X <- M", 2, 3),
    TAY_IMP(TAY, IMPLIED, true, 0xA8, "Y <- (A)", 1, 2),
    LDA_IMM(LDA, IMMEDIATE, true, 0xA9, "A <- M", 2, 2),
    TAX_IMP(TAX, IMPLIED, true, 0xAA, "X <- (A)", 1, 2),
    LXA_IMM(LXA, IMMEDIATE, false, 0xAB, "X04 <- (X04)^M04 \nA04 <- (A04)^M04", 1, 2),
    LDY_ABS(LDY, ABSOLUTE, true, 0xAC, "Y <- M", 3, 4),
    LDA_ABS(LDA, ABSOLUTE, true, 0xAD, "A <- M", 3, 4),
    LDX_ABS(LDX, ABSOLUTE, true, 0xAE, "X <- M", 3, 4),
    LAX_ABS(LAX, ABSOLUTE, false, 0xAF, "A <- M, X <- M", 3, 4),
    BCS_REL(BCS, RELATIVE, true, 0xB0, "if C=1, PC = PC + offset", 2, 2, 2),
    LDA_INY(LDA, INDY, true, 0xB1, "A <- M", 2, 5, 1),
    JAM10__(JAM, IMPLIED, false, 0xB2, "[locks up machine]", 1, 0),
    LAX_INY(LAX, INDY, false, 0xB3, "A <- M, X <- M", 2, 5, 1),
    LDY_ZPX(LDY, ZPAGEX, true, 0xB4, "Y <- M", 2, 4),
    LDA_ZPX(LDA, ZPAGEX, true, 0xB5, "A <- M", 2, 4),
    LDX_ZPY(LDX, ZPAGEY, true, 0xB6, "X <- M", 2, 4),
    LAX_ZPY(LAX, ZPAGEY, false, 0xB7, "A <- M, X <- M", 2, 4),
    CLV_IMP(CLV, IMPLIED, true, 0xB8, "V <- 0", 1, 2),
    LDA_ABY(LDA, ABSOLUTEY, true, 0xB9, "A <- M", 3, 4, 1),
    TSX_IMP(TSX, IMPLIED, true, 0xBA, "X <- (S)", 1, 2),
    LAE_ABY(LAE, ABSOLUTEY, false, 0xBB, "X,S,A <- (S^M)", 3, 4, 1),
    LDY_ABX(LDY, ABSOLUTEX, true, 0xBC, "Y <- M", 3, 4, 1),
    LDA_ABX(LDA, ABSOLUTEX, true, 0xBD, "A <- M", 3, 4, 1),
    LDX_ABY(LDX, ABSOLUTEY, true, 0xBE, "X <- M", 3, 4, 1),
    LAX_ABY(LAX, ABSOLUTEY, false, 0xBF, "A <- M, X <- M", 3, 4, 1),
    CPY_IMM(CPY, IMMEDIATE, true, 0xC0, "(Y - M) -> NZC", 2, 2),
    CMP_INX(CMP, INDX, true, 0xC1, "(A - M) -> NZC", 2, 6),
    NOP19__(NOP, IMMEDIATE, false, 0xC2, "[no operation]", 2, 2),
    DCP_INX(DCP, INDX, false, 0xC3, "M <- (M)-1, (A-M) -> NZC", 2, 8),
    CPY_ZPG(CPY, ZPAGE, true, 0xC4, "(Y - M) -> NZC", 2, 3),
    CMP_ZPG(CMP, ZPAGE, true, 0xC5, "(A - M) -> NZC", 2, 3),
    DEC_ZPG(DEC, ZPAGE, true, 0xC6, "M <- (M) - 1", 2, 5),
    DCP_ZPG(DCP, ZPAGE, false, 0xC7, "M <- (M)-1, (A-M) -> NZC", 2, 5),
    INY_IMP(INY, IMPLIED, true, 0xC8, "Y <- (Y) + 1", 1, 2),
    CMP_IMM(CMP, IMMEDIATE, true, 0xC9, "(A - M) -> NZC", 2, 2),
    DEX_IMP(DEX, IMPLIED, true, 0xCA, "X <- (X) - 1", 1, 2),
    SBX_IMM(SBX, IMMEDIATE, false, 0xCB, "X <- (X)^(A) - M", 2, 2),
    CPY_ABS(CPY, ABSOLUTE, true, 0xCC, "(Y - M) -> NZC", 3, 4),
    CMP_ABS(CMP, ABSOLUTE, true, 0xCD, "(A - M) -> NZC", 3, 4),
    DEC_ABS(DEC, ABSOLUTE, true, 0xCE, "M <- (M) - 1", 3, 6),
    DCP_ABS(DCP, ABSOLUTE, false, 0xCF, "M <- (M)-1, (A-M) -> NZC", 3, 6),
    BNE_REL(BNE, RELATIVE, true, 0xD0, "if Z=0, PC = PC + offset", 2, 2, 2),
    CMP_INY(CMP, INDY, true, 0xD1, "(A - M) -> NZC", 2, 5, 1),
    JAM11__(JAM, IMPLIED, false, 0xD2, "[locks up machine]", 1, 0),
    DCP_INY(DCP, INDY, false, 0xD3, "M <- (M)-1, (A-M) -> NZC", 2, 8),
    NOP20__(NOP, ZPAGEX, false, 0xD4, "[no operation]", 2, 4),
    CMP_ZPX(CMP, ZPAGEX, true, 0xD5, "(A - M) -> NZC", 2, 4),
    DEC_ZPX(DEC, ZPAGEX, true, 0xD6, "M <- (M) - 1", 2, 6),
    DCP_ZPX(DCP, ZPAGEX, false, 0xD7, "M <- (M)-1, (A-M) -> NZC", 2, 6),
    CLD_IMP(CLD, IMPLIED, true, 0xD8, "D <- 0", 1, 2),
    CMP_ABY(CMP, ABSOLUTEY, true, 0xD9, "(A - M) -> NZC", 3, 4, 1),
    NOP21__(NOP, IMPLIED, false, 0xDA, "[no operation]", 1, 2),
    DCP_ABY(DCP, ABSOLUTEY, false, 0xDB, "M <- (M)-1, (A-M) -> NZC", 3, 7),
    NOP22__(NOP, ABSOLUTEX, false, 0xDC, "[no operation]", 3, 4, 1),
    CMP_ABX(CMP, ABSOLUTEX, true, 0xDD, "(A - M) -> NZC", 3, 4, 1),
    DEC_ABX(DEC, ABSOLUTEX, true, 0xDE, "M <- (M) - 1", 3, 7),
    DCP_ABX(DCP, ABSOLUTEX, false, 0xDF, "M <- (M)-1, (A-M) -> NZC", 3, 7),
    CPX_IMM(CPX, IMMEDIATE, true, 0xE0, "(X - M) -> NZC", 2, 2),
    SBC_INX(SBC, INDX, true, 0xE1, "A <- (A) - M - ~C", 2, 6),
    NOP23__(NOP, IMMEDIATE, false, 0xE2, "[no operation]", 2, 2),
    ISB_INX(ISB, INDX, false, 0xE3, "M <- (M) - 1,A <- (A)-M-~C", 3, 8, 1),
    CPX_ZPG(CPX, ZPAGE, true, 0xE4, "(X - M) -> NZC", 2, 3),
    SBC_ZPG(SBC, ZPAGE, true, 0xE5, "A <- (A) - M - ~C", 2, 3),
    INC_ZPG(INC, ZPAGE, true, 0xE6, "M <- (M) + 1", 2, 5),
    ISB_ZPG(ISB, ZPAGE, false, 0xE7, "M <- (M) - 1,A <- (A)-M-~C", 2, 5),
    INX_IMP(INX, IMPLIED, true, 0xE8, "X <- (X) +1", 1, 2),
    SBC_IMM(SBC, IMMEDIATE, true, 0xE9, "A <- (A) - M - ~C", 2, 2),
    NOP24__(NOP, IMPLIED, true, 0xEA, "[no operation]", 1, 2),
    SBC_IMM2(SBC, IMMEDIATE, false, 0xEB, "A <- (A) - M - ~C", 1, 2),
    SBC_ABS(SBC, ABSOLUTE, true, 0xED, "A <- (A) - M - ~C", 3, 4),
    CPX_ABS(CPX, ABSOLUTE, true, 0xEC, "(X - M) -> NZC", 3, 4),
    INC_ABS(INC, ABSOLUTE, true, 0xEE, "M <- (M) + 1", 3, 6),
    ISB_ABS(ISB, ABSOLUTE, false, 0xEF, "M <- (M) - 1,A <- (A)-M-~C", 3, 6),
    BEQ_REL(BEQ, RELATIVE, true, 0xF0, "if Z=1, PC = PC + offset", 2, 2, 2),
    SBC_INY(SBC, INDY, true, 0xF1, "A <- (A) - M - ~C", 2, 5, 1),
    JAM12__(JAM, IMPLIED, false, 0xF2, "[locks up machine]", 1, 0),
    ISB_INY(ISB, INDY, false, 0xF3, "M <- (M) - 1,A <- (A)-M-~C", 2, 8),
    NOP25__(NOP, ZPAGEX, false, 0xF4, "[no operation]", 2, 4),
    SBC_ZPX(SBC, ZPAGEX, true, 0xF5, "A <- (A) - M - ~C", 2, 4),
    INC_ZPX(INC, ZPAGEX, true, 0xF6, "M <- (M) + 1", 2, 6),
    ISB_ZPX(ISB, ZPAGEX, false, 0xF7, "M <- (M) - 1,A <- (A)-M-~C", 2, 6),
    SED_IMP(SED, IMPLIED, true, 0xF8, "D <- 1", 1, 2),
    SBC_ABY(SBC, ABSOLUTEY, true, 0xF9, "A <- (A) - M - ~C", 3, 4, 1),
    NOP26__(NOP, IMPLIED, false, 0xFA, "[no operation]", 1, 2),
    ISB_ABY(ISB, ABSOLUTEY, false, 0xFB, "M <- (M) - 1,A <- (A)-M-~C", 3, 7),
    NOP27__(NOP, ABSOLUTEX, false, 0xFC, "[no operation]", 3, 4, 1),
    SBC_ABX(SBC, ABSOLUTEX, true, 0xFD, "A <- (A) - M - ~C", 3, 4, 1),
    INC_ABX(INC, ABSOLUTEX, true, 0xFE, "M <- (M) + 1", 3, 7),
    ISB_ABX(ISB, ABSOLUTEX, false, 0xFF, "M <- (M) - 1,A <- (A)-M-~C", 3, 7),
    ;

    public String mnemonic;
    public boolean standard;
    public int hex;
    public String description;
    public Addressing addressing;
    public int bytes;
    public int time;
    public int timeb;


    static HashMap<Integer, Opcode> map = new HashMap<>();

    static {
        for (Opcode o : values()) {
            map.put(o.hex, o);
        }
    }

    public static Opcode byMnemonic(int mnemonic) {
        return map.get(mnemonic);
    }

    Opcode(Mnemonic type, Addressing addressing, boolean standard, int hex, String description, int bytes, int time, int timeb) {
        this.mnemonic = type.mnemonic;
        this.standard = standard;
        this.hex = hex;
        this.description = description;
        this.addressing = addressing;
        this.bytes = bytes;
        this.time = time;
        this.timeb = timeb;
    }

    Opcode(Mnemonic type, Addressing addressing, boolean standard, int hex, String description, int bytes, int time) {
        this.mnemonic = type.mnemonic;
        this.standard = standard;
        this.hex = hex;
        this.description = description;
        this.addressing = addressing;
        this.bytes = bytes;
        this.time = time;
        this.timeb = 0;
    }

    public static Opcode get(String mnemonic) {
        return byMnemonic(Integer.parseInt(mnemonic, 16));
    }

    @Override
    public String toString() {
        return this.name();
    }
}
