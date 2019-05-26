package io.github.amarcinkowski.c64;

import java.util.HashMap;

import static io.github.amarcinkowski.c64.Addressing.*;

@SuppressWarnings("unused")
public enum Opcode {

    /**
     * C=haking 1
     * http://www.ffd2.com/fridge/chacking/c=hacking1.txt
     * <p>
     * Std Mnemonic Hex   Description            Addressing Mode  Bytes/Time
     * BRK      $00       Stack <- PC, PC <- ($fffe) (Immediate)      1/7
     * ORA      $01       A <- (A) V M               (Ind,X)          6/2
     * JAM      $02       [locks up machine]         (Implied)        1/-
     * SLO      $03       M <- (M >> 1) + A + C      (Ind,X)          2/8
     * NOP      $04       [no operation]             (Z-Page)         2/3
     * ORA      $05       A <- (A) V M               (Z-Page)         2/3
     * ASL      $06       C <- A7, A <- (A) << 1     (Z-Page)         2/5
     * SLO      $07       M <- (M >> 1) + A + C      (Z-Page)         2/5
     * PHP      $08       Stack <- (P)               (Implied)        1/3
     * ORA      $09       A <- (A) V M               (Immediate)      2/2
     * ASL      $0A       C <- A7, A <- (A) << 1     (Accumalator)    1/2
     * ANC      $0B       A <- A^M, C=~A7            (Immediate)      1/2
     * NOP      $0C       [no operation]             (Absolute)       3/4
     * ORA      $0D       A <- (A) V M               (Absolute)       3/4
     * ASL      $0E       C <- A7, A <- (A) << 1     (Absolute)       3/6
     * SLO      $0F       M <- (M >> 1) + A + C      (Absolute)       3/6
     * BPL      $10       if N=0, PC = PC + offset   (Relative)       2/2'2
     * ORA      $11       A <- (A) V M               ((Ind),Y)        2/5'1
     * JAM      $12       [locks up machine]         (Implied)        1/-
     * SLO      $13       M <- (M >. 1) + A + C      ((Ind),Y)        2/8'5
     * NOP      $14       [no operation]             (Z-Page,X)       2/4
     * ORA      $15       A <- (A) V M               (Z-Page,X)       2/4
     * ASL      $16       C <- A7, A <- (A) << 1     (Z-Page,X)       2/6
     * SLO      $17       M <- (M >> 1) + A + C      (Z-Page,X)       2/6
     * CLC      $18       C <- 0                     (Implied)        1/2
     * ORA      $19       A <- (A) V M               (Absolute,Y)     3/4'1
     * NOP      $1A       [no operation]             (Implied)        1/2
     * SLO      $1B       M <- (M >> 1) + A + C      (Absolute,Y)     3/7
     * NOP      $1C       [no operation]             (Absolute,X)     2/4'1
     * ORA      $1D       A <- (A) V M               (Absolute,X)     3/4'1
     * ASL      $1E       C <- A7, A <- (A) << 1     (Absolute,X)     3/7
     * SLO      $1F       M <- (M >> 1) + A + C      (Absolute,X)     3/7
     * JSR      $20       Stack <- PC, PC <- Address (Absolute)       3/6
     * AND      $21       A <- (A)^M                 (Ind,X)          2/6
     * JAM      $22       [locks up machine]         (Implied)        1/-
     * RLA      $23       M <- (M << 1)^(A)          (Ind,X)          2/8
     * BIT      $24       Z <- ~(A^M) N<-M7 V<-M6    (Z-Page)         2/3
     * AND      $25       A <- (A)^M                 (Z-Page)         2/3
     * ROL      $26       C <- A7 & A <- A << 1 + C  (Z-Page)         2/5
     * RLA      $27       M <- (M << 1)^(A)          (Z-Page)         2/5'5
     * PLP      $28       A <- (Stack)               (Implied)        1/4
     * AND      $29       A <- (A)^M                 (Immediate)      2/2
     * ROL      $2A       C <- A7 & A <- A << 1 + C  (Accumalator)    1/2
     * ANC      $2B       A <- A^M, C <- ~A7         (Immediate9      1/2
     * BIT      $2C       Z <- ~(A^M) N<-M7 V<-M6    (Absolute)       3/4
     * AND      $2D       A <- (A)^M                 (Absolute)       3/4
     * ROL      $2E       C <- A7 & A <- A << 1 + C  (Absolute)       3/6
     * RLA      $2F       M <- (M << 1)^(A)          (Absolute)       3/6'5
     * BMI      $30       if N=1, PC = PC + offset   (Relative)       2/2'2
     * AND      $31       A <- (A)^M                 ((Ind),Y)        2/5'1
     * JAM      $32       [locks up machine]         (Implied)        1/-
     * RLA      $33       M <- (M << 1)^(A)          ((Ind),Y)        2/8'5
     * NOP      $34       [no operation]             (Z-Page,X)       2/4
     * AND      $35       A <- (A)^M                 (Z-Page,X)       2/4
     * ROL      $36       C <- A7 & A <- A << 1 + C  (Z-Page,X)       2/6
     * RLA      $37       M <- (M << 1)^(A)          (Z-Page,X)       2/6'5
     * SEC      $38       C <- 1                     (Implied)        1/2
     * AND      $39       A <- (A)^M                 (Absolute,Y)     3/4'1
     * NOP      $3A       [no operation]             (Implied)        1/2
     * RLA      $3B       M <- (M << 1)^(A)          (Absolute,Y)     3/7'5
     * NOP      $3C       [no operation]             (Absolute,X)     3/4'1
     * AND      $3D       A <- (A)^M                 (Absolute,X)     3/4'1
     * ROL      $3E       C <- A7 & A <- A << 1 + C  (Absolute,X)     3/7
     * RLA      $3F       M <- (M << 1)^(A)          (Absolute,X)     3/7'5
     * RTI      $40       P <- (Stack), PC <-(Stack) (Implied)        1/6
     * EOR      $41       A <- (A) ⊻ M               (Ind,X)          2/6
     * JAM      $42       [locks up machine]         (Implied)        1/-
     * SRE      $43       M <- (M >> 1) ⊻ A          (Ind,X)          2/8
     * NOP      $44       [no operation]             (Z-Page)         2/3
     * EOR      $45       A <- (A) ⊻ M               (Z-Page)         2/3
     * LSR      $46       C <- A0, A <- (A) >> 1     (Absolute,X)     3/7
     * SRE      $47       M <- (M >> 1) ⊻ A          (Z-Page)         2/5
     * PHA      $48       Stack <- (A)               (Implied)        1/3
     * EOR      $49       A <- (A) ⊻ M               (Immediate)      2/2
     * LSR      $4A       C <- A0, A <- (A) >> 1     (Accumalator)    1/2
     * ASR      $4B       A <- [(A^M) >> 1]          (Immediate)      1/2
     * JMP      $4C       PC <- Address              (Absolute)       3/3
     * EOR      $4D       A <- (A) ⊻ M               (Absolute)       3/4
     * LSR      $4E       C <- A0, A <- (A) >> 1     (Absolute)       3/6
     * SRE      $4F       M <- (M >> 1) ⊻ A          (Absolute)       3/6
     * BVC      $50       if V=0, PC = PC + offset   (Relative)       2/2'2
     * EOR      $51       A <- (A) ⊻ M               ((Ind),Y)        2/5'1
     * JAM      $52       [locks up machine]         (Implied)        1/-
     * SRE      $53       M <- (M >> 1) ⊻ A          ((Ind),Y)        2/8
     * NOP      $54       [no operation]             (Z-Page,X)       2/4
     * EOR      $55       A <- (A) ⊻ M               (Z-Page,X)       2/4
     * LSR      $56       C <- A0, A <- (A) >> 1     (Z-Page,X)       2/6
     * SRE      $57       M <- (M >> 1) ⊻ A          (Z-Page,X)       2/6
     * CLI      $58       I <- 0                     (Implied)        1/2
     * EOR      $59       A <- (A) ⊻ M               (Absolute,Y)     3/4'1
     * NOP      $5A       [no operation]             (Implied)        1/2
     * SRE      $5B       M <- (M >> 1) ⊻ A          (Absolute,Y)     3/7
     * NOP      $5C       [no operation]             (Absolute,X)     3/4'1
     * EOR      $5D       A <- (A) ⊻ M               (Absolute,X)     3/4'1
     * SRE      $5F       M <- (M >> 1) ⊻ A          (Absolute,X)     3/7
     * RTS      $60       PC <- (Stack)              (Implied)        1/6
     * ADC      $61       A <- (A) + M + C           (Ind,X)          2/6
     * JAM      $62       [locks up machine]         (Implied)        1/-
     * RRA      $63       M <- (M >> 1) + (A) + C    (Ind,X)          2/8'5
     * NOP      $64       [no operation]             (Z-Page)         2/3
     * ADC      $65       A <- (A) + M + C           (Z-Page)         2/3
     * ROR      $66       C<-A0 & A<- (A7=C + A>>1)  (Z-Page)         2/5
     * RRA      $67       M <- (M >> 1) + (A) + C    (Z-Page)         2/5'5
     * PLA      $68       A <- (Stack)               (Implied)        1/4
     * ADC      $69       A <- (A) + M + C           (Immediate)      2/2
     * ROR      $6A       C<-A0 & A<- (A7=C + A>>1)  (Accumalator)    1/2
     * ARR      $6B       A <- [(A^M) >> 1]          (Immediate)      1/2'5
     * JMP      $6C       PC <- Address              (Indirect)       3/5
     * ADC      $6D       A <- (A) + M + C           (Absolute)       3/4
     * ROR      $6E       C<-A0 & A<- (A7=C + A>>1)  (Absolute)       3/6
     * RRA      $6F       M <- (M >> 1) + (A) + C    (Absolute)       3/6'5
     * BVS      $70       if V=1, PC = PC + offset   (Relative)       2/2'2
     * ADC      $71       A <- (A) + M + C           ((Ind),Y)        2/5'1
     * JAM      $72       [locks up machine]         (Implied)        1/-
     * RRA      $73       M <- (M >> 1) + (A) + C    ((Ind),Y)        2/8'5
     * NOP      $74       [no operation]             (Z-Page,X)       2/4
     * ADC      $75       A <- (A) + M + C           (Z-Page,X)       2/4
     * ROR      $76       C<-A0 & A<- (A7=C + A>>1)  (Z-Page,X)       2/6
     * RRA      $77       M <- (M >> 1) + (A) + C    (Z-Page,X)       2/6'5
     * SEI      $78       I <- 1                     (Implied)        1/2
     * ADC      $79       A <- (A) + M + C           (Absolute,Y)     3/4'1
     * NOP      $7A       [no operation]             (Implied)        1/2
     * RRA      $7B       M <- (M >> 1) + (A) + C    (Absolute,Y)     3/7'5
     * NOP      $7C       [no operation]             (Absolute,X)     3/4'1
     * ADC      $7D       A <- (A) + M + C           (Absolute,X)     3/4'1
     * ROR      $7E       C<-A0 & A<- (A7=C + A>>1)  (Absolute,X)     3/7
     * RRA      $7F       M <- (M >> 1) + (A) + C    (Absolute,X)     3/7'5
     * NOP      $80       [no operation]             (Immediate)      2/2
     * STA      $81       M <- (A)                   (Ind,X)          2/6
     * NOP      $82       [no operation]             (Immediate)      2/2
     * SAX      $83       M <- (A)^(X)               (Ind,X)          2/6
     * STY      $84       M <- (Y)                   (Z-Page)         2/3
     * STA      $85       M <- (A)                   (Z-Page)         2/3
     * STX      $86       M <- (X)                   (Z-Page)         2/3
     * SAX      $87       M <- (A)^(X)               (Z-Page)         2/3
     * DEY      $88       Y <- (Y) - 1               (Implied)        1/2
     * NOP      $89       [no operation]             (Immediate)      2/2
     * TXA      $8A       A <- (X)                   (Implied)        1/2
     * ANE      $8B       M <-[(A)\/$EE]^(X)/\(M)    (Immediate)      2/2^4
     * STY      $8C       M <- (Y)                   (Absolute)       3/4
     * STA      $8D       M <- (A)                   (Absolute)       3/4
     * STX      $8E       M <- (X)                   (Absolute)       3/4
     * SAX      $8F       M <- (A)^(X)               (Absolute)       3/4
     * BCC      $90       if C=0, PC = PC + offset   (Relative)       2/2'2
     * STA      $91       M <- (A)                   ((Ind),Y)        2/6
     * JAM      $92       [locks up machine]         (Implied)        1/-
     * SHA      $93       M <- (A)^(X)^(PCH+1)       (Absolute,X)     3/6'3
     * STY      $94       M <- (Y)                   (Z-Page,X)       2/4
     * STA      $95       M <- (A)                   (Z-Page,X)       2/4
     * SAX      $97       M <- (A)^(X)               (Z-Page,Y)       2/4
     * STX      $96       M <- (X)                   (Z-Page,Y)       2/4
     * TYA      $98       A <- (Y)                   (Implied)        1/2
     * STA      $99       M <- (A)                   (Absolute,Y)     3/5
     * TXS      $9A       S <- (X)                   (Implied)        1/2
     * SHS      $9B       X <- (A)^(X), S <- (X)     (Absolute,Y)     3/5
     * M <- (X)^(PCH+1)
     * SHY      $9C       M <- (Y)^(PCH+1)           (Absolute,Y)     3/5'3
     * STA      $9D       M <- (A)                   (Absolute,X)     3/5
     * SHX      $9E       M <- (X)^(PCH+1)           (Absolute,X)     3/5'3
     * SHA      $9F       M <- (A)^(X)^(PCH+1)       (Absolute,Y)     3/5'3
     * LDY      $A0       Y <- M                     (Immediate)      2/2
     * LDA      $A1       A <- M                     (Ind,X)          2/6
     * LDX      $A2       X <- M                     (Immediate)      2/2
     * LAX      $A3       A <- M, X <- M             (Ind,X)          2/6
     * LDY      $A4       Y <- M                     (Z-Page)         2/3
     * LDA      $A5       A <- M                     (Z-Page)         2/3
     * LDX      $A6       X <- M                     (Z-Page)         2/3
     * LAX      $A7       A <- M, X <- M             (Z-Page)         2/3
     * TAY      $A8       Y <- (A)                   (Implied)        1/2
     * LDA      $A9       A <- M                     (Immediate)      2/2
     * TAX      $AA       X <- (A)                   (Implied)        1/2
     * LXA      $AB       X04 <- (X04)^M04           (Immediate)      1/2
     * A04 <- (A04)^M04
     * LDY      $AC       Y <- M                     (Absolute)       3/4
     * LDA      $AD       A <- M                     (Absolute)       3/4
     * LDX      $AE       X <- M                     (Absolute)       3/4
     * LAX      $AF       A <- M, X <- M             (Absolute)       3/4
     * BCS      $B0       if C=1, PC = PC + offset   (Relative)       2/2'2
     * LDA      $B1       A <- M                     ((Ind),Y)        2/5'1
     * JAM      $B2       [locks up machine]         (Implied)        1/-
     * LAX      $B3       A <- M, X <- M             ((Ind),Y)        2/5'1
     * LDY      $B4       Y <- M                     (Z-Page,X)       2/4
     * LDA      $B5       A <- M                     (Z-Page,X)       2/4
     * LDX      $B6       X <- M                     (Z-Page,Y)       2/4
     * LAX      $B7       A <- M, X <- M             (Z-Page,Y)       2/4
     * CLV      $B8       V <- 0                     (Implied)        1/2
     * LDA      $B9       A <- M                     (Absolute,Y)     3/4'1
     * TSX      $BA       X <- (S)                   (Implied)        1/2
     * LAE      $BB       X,S,A <- (S^M)             (Absolute,Y)     3/4'1
     * LDY      $BC       Y <- M                     (Absolute,X)     3/4'1
     * LDA      $BD       A <- M                     (Absolute,X)     3/4'1
     * LDX      $BE       X <- M                     (Absolute,Y)     3/4'1
     * LAX      $BF       A <- M, X <- M             (Absolute,Y)     3/4'1
     * CPY      $C0       (Y - M) -> NZC             (Immediate)      2/2
     * CMP      $C1       (A - M) -> NZC             (Ind,X)          2/6
     * NOP      $C2       [no operation]             (Immediate)      2/2
     * DCP      $C3       M <- (M)-1, (A-M) -> NZC   (Ind,X)          2/8
     * CPY      $C4       (Y - M) -> NZC             (Z-Page)         2/3
     * CMP      $C5       (A - M) -> NZC             (Z-Page)         2/3
     * DEC      $C6       M <- (M) - 1               (Z-Page)         2/5
     * DCP      $C7       M <- (M)-1, (A-M) -> NZC   (Z-Page)         2/5
     * INY      $C8       Y <- (Y) + 1               (Implied)        1/2
     * CMP      $C9       (A - M) -> NZC             (Immediate)      2/2
     * DEX      $CA       X <- (X) - 1               (Implied)        1/2
     * SBX      $CB       X <- (X)/\(A) - M          (Immediate)      2/2
     * CPY      $CC       (Y - M) -> NZC             (Absolute)       3/4
     * CMP      $CD       (A - M) -> NZC             (Absolute)       3/4
     * DEC      $CE       M <- (M) - 1               (Absolute)       3/6
     * DCP      $CF       M <- (M)-1, (A-M) -> NZC   (Absolute)       3/6
     * BNE      $D0       if Z=0, PC = PC + offset   (Relative)       2/2'2
     * CMP      $D1       (A - M) -> NZC             ((Ind),Y)        2/5'1
     * JAM      $D2       [locks up machine]         (Implied)        1/-
     * DCP      $D3       M <- (M)-1, (A-M) -> NZC   ((Ind),Y)        2/8
     * NOP      $D4       [no operation]             (Z-Page,X)       2/4
     * CMP      $D5       (A - M) -> NZC             (Z-Page,X)       2/4
     * DEC      $D6       M <- (M) - 1               (Z-Page,X)       2/6
     * DCP      $D7       M <- (M)-1, (A-M) -> NZC   (Z-Page,X)       2/6
     * CLD      $D8       D <- 0                     (Implied)        1/2
     * CMP      $D9       (A - M) -> NZC             (Absolute,Y)     3/4'1
     * NOP      $DA       [no operation]             (Implied)        1/2
     * DCP      $DB       M <- (M)-1, (A-M) -> NZC   (Absolute,Y)     3/7
     * NOP      $DC       [no operation]             (Absolute,X)     3/4'1
     * CMP      $DD       (A - M) -> NZC             (Absolute,X)     3/4'1
     * DEC      $DE       M <- (M) - 1               (Absolute,X)     3/7
     * DCP      $DF       M <- (M)-1, (A-M) -> NZC   (Absolute,X)     3/7
     * CPX      $E0       (X - M) -> NZC             (Immediate)      2/2
     * SBC      $E1       A <- (A) - M - ~C          (Ind,X)          2/6
     * NOP      $E2       [no operation]             (Immediate)      2/2
     * ISB      $E3       M <- (M) - 1,A <- (A)-M-~C (Ind,X)          3/8'1
     * CPX      $E4       (X - M) -> NZC             (Z-Page)         2/3
     * SBC      $E5       A <- (A) - M - ~C          (Z-Page)         2/3
     * INC      $E6       M <- (M) + 1               (Z-Page)         2/5
     * ISB      $E7       M <- (M) - 1,A <- (A)-M-~C (Z-Page)         2/5
     * INX      $E8       X <- (X) +1                (Implied)        1/2
     * SBC      $E9       A <- (A) - M - ~C          (Immediate)      2/2
     * NOP      $EA       [no operation]             (Implied)        1/2
     * SBC      $EB       A <- (A) - M - ~C          (Immediate)      1/2
     * SBC      $ED       A <- (A) - M - ~C          (Absolute)       3/4
     * CPX      $EC       (X - M) -> NZC             (Absolute)       3/4
     * INC      $EE       M <- (M) + 1               (Absolute)       3/6
     * ISB      $EF       M <- (M) - 1,A <- (A)-M-~C (Absolute)       3/6
     * BEQ      $F0       if Z=1, PC = PC + offset   (Relative)       2/2'2
     * SBC      $F1       A <- (A) - M - ~C          ((Ind),Y)        2/5'1
     * JAM      $F2       [locks up machine]         (Implied)        1/-
     * ISB      $F3       M <- (M) - 1,A <- (A)-M-~C ((Ind),Y)        2/8
     * NOP      $F4       [no operation]             (Z-Page,X)       2/4
     * SBC      $F5       A <- (A) - M - ~C          (Z-Page,X)       2/4
     * INC      $F6       M <- (M) + 1               (Z-Page,X)       2/6
     * ISB      $F7       M <- (M) - 1,A <- (A)-M-~C (Z-Page,X)       2/6
     * SED      $F8       D <- 1                     (Implied)        1/2
     * SBC      $F9       A <- (A) - M - ~C          (Absolute,Y)     3/4'1
     * NOP      $FA       [no operation]             (Implied)        1/2
     * ISB      $FB       M <- (M) - 1,A <- (A)-M-~C (Absolute,Y)     3/7
     * NOP      $FC       [no operation]             (Absolute,X)     3/4'1
     * SBC      $FD       A <- (A) - M - ~C          (Absolute,X)     3/4'1
     * INC      $FE       M <- (M) + 1               (Absolute,X)     3/7
     * ISB      $FF       M <- (M) - 1,A <- (A)-M-~C (Absolute,X)     3/7
     * <p>
     * (Ind,X) -- INDX
     * ((Ind),Y) -- INDY
     * (Indirect) -- INDIRECT _IND
     * (Implied) -- IMPLIED _IMP
     * (Z-Page) -- ZPAGE _Z
     * (Z-Page,X) -- ZPAGEX _ZX
     * (Z-Page,Y) -- ZPAGEY _ZY
     * (Immediate) -- IMMEDIATE _IMM
     * (Absolute) -- ABSOLUTE _A
     * (Absolute,Y) -- ABSOLUTEY _AY
     * (Relative) -- RELATIVE _R
     * (Accumalator) -- ACCUMULATOR _ACC
     */

    //    Std Mnemonic Hex Value Description  Addressing Mode  Bytes/Time
    BRK_IMM("brk",true, 0x00, "Stack <- PC, PC <- ($fffe)", IMMEDIATE, 1, 7),
    ORA_INDX("ora",true, 0x01, "A <- (A) V M", INDX, 6, 2),
    JAM1("jam",false, 0x02, "[locks up machine]", IMPLIED, 1, 0),
    SLO_INDX("slo",false, 0x03, "M <- (M >> 1) + A + C", INDX, 2, 8),
    NOP_Z("nop",false, 0x04, "[no operation]", ZPAGE, 2, 3),
    ORA_Z("ora",true, 0x05, "A <- (A) V M", ZPAGE, 2, 3),
    ASL_Z("asl",true, 0x06, "C <- A7, A <- (A) << 1", ZPAGE, 2, 5),
    SLO_Z("slo",false, 0x07, "M <- (M >> 1) + A + C", ZPAGE, 2, 5),
    PHP_IMP("php",true, 0x08, "Stack <- (P)", IMPLIED, 1, 3),
    ORA_IMM("ora",true, 0x09, "A <- (A) V M", IMMEDIATE, 2, 2),
    ASL_ACC("asl",true, 0x0A, "C <- A7, A <- (A) << 1", ACCUMULATOR, 1, 2),
    ANC_IMM("anc",false, 0x0B, "A <- A^M, C=~A7", IMMEDIATE, 1, 2),
    NOP_A("nop",false, 0x0C, "[no operation]", ABSOLUTE, 3, 4),
    ORA_A("ora",true, 0x0D, "A <- (A) V M", ABSOLUTE, 3, 4),
    ASL_A("asl",true, 0x0E, "C <- A7, A <- (A) << 1", ABSOLUTE, 3, 6),
    SLO_A("slo",false, 0x0F, "M <- (M >> 1) + A + C", ABSOLUTE, 3, 6),
    BPL_R("bpl",true, 0x10, "if N=0, PC = PC + offset", RELATIVE, 2, 2, 2),
    ORA_INDY("ora",true, 0x11, "A <- (A) V M", INDY, 2, 5, 1),
    JAM2("jam",false, 0x12, "[locks up machine]", IMPLIED, 1, 0),
    SLO_INDY("slo",false, 0x13, "M <- (M >. 1) + A + C", INDY, 2, 8, 5),
    NOP_ZX("nop",false, 0x14, "[no operation]", ZPAGEX, 2, 4),
    ORA_ZX("ora",true, 0x15, "A <- (A) V M", ZPAGEX, 2, 4),
    ASL_ZX("asl",true, 0x16, "C <- A7, A <- (A) << 1", ZPAGEX, 2, 6),
    SLO_ZX("slo",false, 0x17, "M <- (M >> 1) + A + C", ZPAGEX, 2, 6),
    CLC_IMP("clc",true, 0x18, "C <- 0", IMPLIED, 1, 2),
    ORA_AY("ora",true, 0x19, "A <- (A) V M", ABSOLUTEY, 3, 4, 1),
    NOP_IMP("nop",false, 0x1A, "[no operation]", IMPLIED, 1, 2),
    SLO_AY("slo",false, 0x1B, "M <- (M >> 1) + A + C", ABSOLUTEY, 3, 7),
    NOP_AX("nop",false, 0x1C, "[no operation]", ABSOLUTEX, 2, 4, 1),
    ORA_AX("ora",true, 0x1D, "A <- (A) V M", ABSOLUTEX, 3, 4, 1),
    ASL_AX("asl",true, 0x1E, "C <- A7, A <- (A) << 1", ABSOLUTEX, 3, 7),
    SLO_AX("slo",false, 0x1F, "M <- (M >> 1) + A + C", ABSOLUTEX, 3, 7),
    JSR_A("jsr",true, 0x20, "Stack <- PC, PC <- Address", ABSOLUTE, 3, 6),
    AND_INDX("and",true, 0x21, "A <- (A)^M", INDX, 2, 6),
    JAM3("jam",false, 0x22, "[locks up machine]", IMPLIED, 1, 0),
    RLA_INDX("rla",false, 0x23, "M <- (M << 1)^(A)", INDX, 2, 8),
    BIT_Z("bit",true, 0x24, "Z <- ~(A^M) N<-M7 V<-M6", ZPAGE, 2, 3),
    AND_Z("and",true, 0x25, "A <- (A)^M", ZPAGE, 2, 3),
    ROL_Z("rol",true, 0x26, "C <- A7 & A <- A << 1 + C", ZPAGE, 2, 5),
    RLA_Z("rla",false, 0x27, "M <- (M << 1)^(A)", ZPAGE, 2, 5, 5),
    PLP_IMP("plp",true, 0x28, "A <- (Stack)", IMPLIED, 1, 4),
    AND_IMM("and",true, 0x29, "A <- (A)^M", IMMEDIATE, 2, 2),
    ROL_ACC("rol",true, 0x2A, "C <- A7 & A <- A << 1 + C", ACCUMULATOR, 1, 2),
    ANC_IMM2("anc",false, 0x2B, "A <- A^M, C <- ~A7", IMMEDIATE, 1, 2),
    BIT_A("bit",true, 0x2C, "Z <- ~(A^M) N<-M7 V<-M6", ABSOLUTE, 3, 4),
    AND_A("and",true, 0x2D, "A <- (A)^M", ABSOLUTE, 3, 4),
    ROL_A("rol",true, 0x2E, "C <- A7 & A <- A << 1 + C", ABSOLUTE, 3, 6),
    RLA_A("rla",false, 0x2F, "M <- (M << 1)^(A)", ABSOLUTE, 3, 6, 5),
    BMI_R("bmi",true, 0x30, "if N=1, PC = PC + offset", RELATIVE, 2, 2, 2),
    AND_INDY("and",true, 0x31, "A <- (A)^M", INDY, 2, 5, 1),
    JAM4("jam",false, 0x32, "[locks up machine]", IMPLIED, 1, 0),
    RLA_INDY("rla",false, 0x33, "M <- (M << 1)^(A)", INDY, 2, 8, 5),
    NOP_ZX2("nop",false, 0x34, "[no operation]", ZPAGEX, 2, 4),
    AND_ZX("and",true, 0x35, "A <- (A)^M", ZPAGEX, 2, 4),
    ROL_ZX("rol",true, 0x36, "C <- A7 & A <- A << 1 + C", ZPAGEX, 2, 6),
    RLA_ZX("rla",false, 0x37, "M <- (M << 1)^(A)", ZPAGEX, 2, 6, 5),
    SEC_IMP("sec",true, 0x38, "C <- 1", IMPLIED, 1, 2),
    AND_AY("and",true, 0x39, "A <- (A)^M", ABSOLUTEY, 3, 4, 1),
    NOP_IMP2("nop",false, 0x3A, "[no operation]", IMPLIED, 1, 2),
    RLA_AY("rla",false, 0x3B, "M <- (M << 1)^(A)", ABSOLUTEY, 3, 7, 5),
    NOP_AX2("nop",false, 0x3C, "[no operation]", ABSOLUTEX, 3, 4, 1),
    AND_AX("and",true, 0x3D, "A <- (A)^M", ABSOLUTEX, 3, 4, 1),
    ROL_AX("rol",true, 0x3E, "C <- A7 & A <- A << 1 + C", ABSOLUTEX, 3, 7),
    RLA_AX("rla",false, 0x3F, "M <- (M << 1)^(A)", ABSOLUTEX, 3, 7, 5),
    RTI_IMP("rti",true, 0x40, "P <- (Stack), PC <-(Stack)", IMPLIED, 1, 6),
    EOR_INDX("eor",true, 0x41, "A <- (A) ⊻ M", INDX, 2, 6),
    JAM5("jam",false, 0x42, "[locks up machine]", IMPLIED, 1, 0),
    SRE_INDX("sre",false, 0x43, "M <- (M >> 1) ⊻ A", INDX, 2, 8),
    NOP_Z2("nop",false, 0x44, "[no operation]", ZPAGE, 2, 3),
    EOR_Z("eor",true, 0x45, "A <- (A) ⊻ M", ZPAGE, 2, 3),
    LSR_AX("lsr",true, 0x46, "C <- A0, A <- (A) >> 1", ABSOLUTEX, 3, 7),
    SRE_Z("sre",false, 0x47, "M <- (M >> 1) ⊻ A", ZPAGE, 2, 5),
    PHA_IMP("pha",true, 0x48, "Stack <- (A)", IMPLIED, 1, 3),
    EOR_IMM("eor",true, 0x49, "A <- (A) ⊻ M", IMMEDIATE, 2, 2),
    LSR_ACC("lsr",true, 0x4A, "C <- A0, A <- (A) >> 1", ACCUMULATOR, 1, 2),
    ASR_IMM("asr",false, 0x4B, "A <- [(A^M) >> 1]", IMMEDIATE, 1, 2),
    JMP_A("jmp",true, 0x4C, "PC <- Address", ABSOLUTE, 3, 3),
    EOR_A("eor",true, 0x4D, "A <- (A) ⊻ M", ABSOLUTE, 3, 4),
    LSR_A("lsr",true, 0x4E, "C <- A0, A <- (A) >> 1", ABSOLUTE, 3, 6),
    SRE_A("sre",false, 0x4F, "M <- (M >> 1) ⊻ A", ABSOLUTE, 3, 6),
    BVC_R("bvc",true, 0x50, "if V=0, PC = PC + offset", RELATIVE, 2, 2, 2),
    EOR_INDY("eor",true, 0x51, "A <- (A) ⊻ M", INDY, 2, 5, 1),
    JAM6("jam",false, 0x52, "[locks up machine]", IMPLIED, 1, 0),
    SRE_INDY("sre",false, 0x53, "M <- (M >> 1) ⊻ A", INDY, 2, 8),
    NOP_ZX3("nop",false, 0x54, "[no operation]", ZPAGEX, 2, 4),
    EOR_ZX("eor",true, 0x55, "A <- (A) ⊻ M", ZPAGEX, 2, 4),
    LSR_ZX("lsr",true, 0x56, "C <- A0, A <- (A) >> 1", ZPAGEX, 2, 6),
    SRE_ZX("sre",false, 0x57, "M <- (M >> 1) ⊻ A", ZPAGEX, 2, 6),
    CLI_IMP("cli",true, 0x58, "I <- 0", IMPLIED, 1, 2),
    EOR_AY("eor",true, 0x59, "A <- (A) ⊻ M", ABSOLUTEY, 3, 4, 1),
    NOP_IMP3("nop",false, 0x5A, "[no operation]", IMPLIED, 1, 2),
    SRE_AY("sre",false, 0x5B, "M <- (M >> 1) ⊻ A", ABSOLUTEY, 3, 7),
    NOP_AX3("nop",false, 0x5C, "[no operation]", ABSOLUTEX, 3, 4, 1),
    EOR_AX("eor",true, 0x5D, "A <- (A) ⊻ M", ABSOLUTEX, 3, 4, 1),
    SRE_AX("sre",false, 0x5F, "M <- (M >> 1) ⊻ A", ABSOLUTEX, 3, 7),
    RTS_IMP("rts",true, 0x60, "PC <- (Stack)", IMPLIED, 1, 6),
    ADC_INDX("adc",true, 0x61, "A <- (A) + M + C", INDX, 2, 6),
    JAM7("jam",false, 0x62, "[locks up machine]", IMPLIED, 1, 0),
    RRA_INDX("rra",false, 0x63, "M <- (M >> 1) + (A) + C", INDX, 2, 8, 5),
    NOP_Z3("nop",false, 0x64, "[no operation]", ZPAGE, 2, 3),
    ADC_Z("adc",true, 0x65, "A <- (A) + M + C", ZPAGE, 2, 3),
    ROR_Z("ror",true, 0x66, "C<-A0 & A<- (A7=C + A>>1)", ZPAGE, 2, 5),
    RRA_Z("rra",false, 0x67, "M <- (M >> 1) + (A) + C", ZPAGE, 2, 5, 5),
    PLA_IMP("pla",true, 0x68, "A <- (Stack)", IMPLIED, 1, 4),
    ADC_IMM("adc",true, 0x69, "A <- (A) + M + C", IMMEDIATE, 2, 2),
    ROR_ACC("ror",true, 0x6A, "C<-A0 & A<- (A7=C + A>>1)", ACCUMULATOR, 1, 2),
    ARR_IMM("arr",false, 0x6B, "A <- [(A^M) >> 1]", IMMEDIATE, 1, 2, 5),
    JMP_INDIRECT("jmp",true, 0x6C, "PC <- Address", INDIRECT, 3, 5),
    ADC_A("adc",true, 0x6D, "A <- (A) + M + C", ABSOLUTE, 3, 4),
    ROR_A("ror",true, 0x6E, "C<-A0 & A<- (A7=C + A>>1)", ABSOLUTE, 3, 6),
    RRA_A("rra",false, 0x6F, "M <- (M >> 1) + (A) + C", ABSOLUTE, 3, 6, 5),
    BVS_R("bvs",true, 0x70, "if V=1, PC = PC + offset", RELATIVE, 2, 2, 2),
    ADC_INDY("adc",true, 0x71, "A <- (A) + M + C", INDY, 2, 5, 1),
    JAM8("jam",false, 0x72, "[locks up machine]", IMPLIED, 1, 0),
    RRA_INDY("rra",false, 0x73, "M <- (M >> 1) + (A) + C", INDY, 2, 8, 5),
    NOP_ZX4("nop",false, 0x74, "[no operation]", ZPAGEX, 2, 4),
    ADC_ZX("adc",true, 0x75, "A <- (A) + M + C", ZPAGEX, 2, 4),
    ROR_ZX("ror",true, 0x76, "C<-A0 & A<- (A7=C + A>>1)", ZPAGEX, 2, 6),
    RRA_ZX("rra",false, 0x77, "M <- (M >> 1) + (A) + C", ZPAGEX, 2, 6, 5),
    SEI_IMP("sei",true, 0x78, "I <- 1", IMPLIED, 1, 2),
    ADC_AY("adc",true, 0x79, "A <- (A) + M + C", ABSOLUTEY, 3, 4, 1),
    NOP_IMP4("nop",false, 0x7A, "[no operation]", IMPLIED, 1, 2),
    RRA_AY("rra",false, 0x7B, "M <- (M >> 1) + (A) + C", ABSOLUTEY, 3, 7, 5),
    NOP_AX4("nop",false, 0x7C, "[no operation]", ABSOLUTEX, 3, 4, 1),
    ADC_AX("adc",true, 0x7D, "A <- (A) + M + C", ABSOLUTEX, 3, 4, 1),
    ROR_AX("ror",true, 0x7E, "C<-A0 & A<- (A7=C + A>>1)", ABSOLUTEX, 3, 7),
    RRA_AX("rra",false, 0x7F, "M <- (M >> 1) + (A) + C", ABSOLUTEX, 3, 7, 5),
    NOP_IMM("nop",false, 0x80, "[no operation]", IMMEDIATE, 2, 2),
    STA_INDX("sta",true, 0x81, "M <- (A)", INDX, 2, 6),
    NOP_IMM1("nop",false, 0x82, "[no operation]", IMMEDIATE, 2, 2),
    SAX_INDX("sax",false, 0x83, "M <- (A)^(X)", INDX, 2, 6),
    STY_Z("sty",true, 0x84, "M <- (Y)", ZPAGE, 2, 3),
    STA_Z("sta",true, 0x85, "M <- (A)", ZPAGE, 2, 3),
    STX_Z("stx",true, 0x86, "M <- (X)", ZPAGE, 2, 3),
    SAX_Z("sax",false, 0x87, "M <- (A)^(X)", ZPAGE, 2, 3),
    DEY_IMP("dey",true, 0x88, "Y <- (Y) - 1", IMPLIED, 1, 2),
    NOP_IMM2("nop",false, 0x89, "[no operation]", IMMEDIATE, 2, 2),
    TXA_IMP("txa",true, 0x8A, "A <- (X)", IMPLIED, 1, 2),
    ANE_IMM("ane",false, 0x8B, "M <-[(A)v$EE]^(X)^(M)", IMMEDIATE, 2, 2, 4),
    STY_A("sty",true, 0x8C, "M <- (Y)", ABSOLUTE, 3, 4),
    STA_A("sta",true, 0x8D, "M <- (A)", ABSOLUTE, 3, 4),
    STX_A("stx",true, 0x8E, "M <- (X)", ABSOLUTE, 3, 4),
    SAX_A("sax",false, 0x8F, "M <- (A)^(X)", ABSOLUTE, 3, 4),
    BCC_R("bcc",true, 0x90, "if C=0, PC = PC + offset", RELATIVE, 2, 2, 2),
    STA_INDY("sta",true, 0x91, "M <- (A)", INDY, 2, 6),
    JAM9("jam",false, 0x92, "[locks up machine]", IMPLIED, 1, 0),
    SHA_AX("sha",false, 0x93, "M <- (A)^(X)^(PCH+1)", ABSOLUTEX, 3, 6, 3),
    STY_ZX("sty",true, 0x94, "M <- (Y)", ZPAGEX, 2, 4),
    STA_ZX("sta",true, 0x95, "M <- (A)", ZPAGEX, 2, 4),
    SAX_ZY("sax",false, 0x97, "M <- (A)^(X)", ZPAGEY, 2, 4),
    STX_ZY("stx",true, 0x96, "M <- (X)", ZPAGEY, 2, 4),
    TYA_IMP("tya",true, 0x98, "A <- (Y)", IMPLIED, 1, 2),
    STA_AY("sta",true, 0x99, "M <- (A)", ABSOLUTEY, 3, 5),
    TXS_IMP("txs",true, 0x9A, "S <- (X)", IMPLIED, 1, 2),
    SHS_AY("shs",false, 0x9B, "X <- (A)^(X), S <- (X) \n M <- (X)^(PCH+1)", ABSOLUTEY, 3, 5),
    SHY_AY("shy",false, 0x9C, "M <- (Y)^(PCH+1)", ABSOLUTEY, 3, 5, 3),
    STA_AX("sta",true, 0x9D, "M <- (A)", ABSOLUTEX, 3, 5),
    SHX_AX("shx",false, 0x9E, "M <- (X)^(PCH+1)", ABSOLUTEX, 3, 5, 3),
    SHA_AY("sha",false, 0x9F, "M <- (A)^(X)^(PCH+1)", ABSOLUTEY, 3, 5, 3),
    LDY_IMM("ldy",true, 0xA0, "Y <- M", IMMEDIATE, 2, 2),
    LDA_INDX("lda",true, 0xA1, "A <- M", INDX, 2, 6),
    LDX_IMM("ldx",true, 0xA2, "X <- M", IMMEDIATE, 2, 2),
    LAX_INDX("lax",false, 0xA3, "A <- M, X <- M", INDX, 2, 6),
    LDY_Z("ldy",true, 0xA4, "Y <- M", ZPAGE, 2, 3),
    LDA_Z("lda",true, 0xA5, "A <- M", ZPAGE, 2, 3),
    LDX_Z("ldx",true, 0xA6, "X <- M", ZPAGE, 2, 3),
    LAX_Z("lax",false, 0xA7, "A <- M, X <- M", ZPAGE, 2, 3),
    TAY_IMP("tay",true, 0xA8, "Y <- (A)", IMPLIED, 1, 2),
    LDA_IMM("lda",true, 0xA9, "A <- M", IMMEDIATE, 2, 2),
    TAX_IMP("tax",true, 0xAA, "X <- (A)", IMPLIED, 1, 2),
    LXA_IMM("lxa",false, 0xAB, "X04 <- (X04)^M04 \nA04 <- (A04)^M04", IMMEDIATE, 1, 2),
    LDY_A("ldy",true, 0xAC, "Y <- M", ABSOLUTE, 3, 4),
    LDA_A("lda",true, 0xAD, "A <- M", ABSOLUTE, 3, 4),
    LDX_A("ldx",true, 0xAE, "X <- M", ABSOLUTE, 3, 4),
    LAX_A("lax",false, 0xAF, "A <- M, X <- M", ABSOLUTE, 3, 4),
    BCS_R("bcs",true, 0xB0, "if C=1, PC = PC + offset", RELATIVE, 2, 2, 2),
    LDA_INDY("lda",true, 0xB1, "A <- M", INDY, 2, 5, 1),
    JAM10("jam",false, 0xB2, "[locks up machine]", IMPLIED, 1, 0),
    LAX_INDY("lax",false, 0xB3, "A <- M, X <- M", INDY, 2, 5, 1),
    LDY_ZX("ldy",true, 0xB4, "Y <- M", ZPAGEX, 2, 4),
    LDA_ZX("lda",true, 0xB5, "A <- M", ZPAGEX, 2, 4),
    LDX_ZY("ldx",true, 0xB6, "X <- M", ZPAGEY, 2, 4),
    LAX_ZY("lax",false, 0xB7, "A <- M, X <- M", ZPAGEY, 2, 4),
    CLV_IMP("clv",true, 0xB8, "V <- 0", IMPLIED, 1, 2),
    LDA_AY("lda",true, 0xB9, "A <- M", ABSOLUTEY, 3, 4, 1),
    TSX_IMP("tsx",true, 0xBA, "X <- (S)", IMPLIED, 1, 2),
    LAE_AY("lae",false, 0xBB, "X,S,A <- (S^M)", ABSOLUTEY, 3, 4, 1),
    LDY_AX("ldy",true, 0xBC, "Y <- M", ABSOLUTEX, 3, 4, 1),
    LDA_AX("lda",true, 0xBD, "A <- M", ABSOLUTEX, 3, 4, 1),
    LDX_AY("ldx",true, 0xBE, "X <- M", ABSOLUTEY, 3, 4, 1),
    LAX_AY("lax",false, 0xBF, "A <- M, X <- M", ABSOLUTEY, 3, 4, 1),
    CPY_IMM("cpy",true, 0xC0, "(Y - M) -> NZC", IMMEDIATE, 2, 2),
    CMP_INDX("cmp",true, 0xC1, "(A - M) -> NZC", INDX, 2, 6),
    NOP_IMM3("nop",false, 0xC2, "[no operation]", IMMEDIATE, 2, 2),
    DCP_INDX("dcp",false, 0xC3, "M <- (M)-1, (A-M) -> NZC", INDX, 2, 8),
    CPY_Z("cpy",true, 0xC4, "(Y - M) -> NZC", ZPAGE, 2, 3),
    CMP_Z("cmp",true, 0xC5, "(A - M) -> NZC", ZPAGE, 2, 3),
    DEC_Z("dec",true, 0xC6, "M <- (M) - 1", ZPAGE, 2, 5),
    DCP_Z("dcp",false, 0xC7, "M <- (M)-1, (A-M) -> NZC", ZPAGE, 2, 5),
    INY_IMP("iny",true, 0xC8, "Y <- (Y) + 1", IMPLIED, 1, 2),
    CMP_IMM("cmp",true, 0xC9, "(A - M) -> NZC", IMMEDIATE, 2, 2),
    DEX_IMP("dex",true, 0xCA, "X <- (X) - 1", IMPLIED, 1, 2),
    SBX_IMM("sbx",false, 0xCB, "X <- (X)^(A) - M", IMMEDIATE, 2, 2),
    CPY_A("cpy",true, 0xCC, "(Y - M) -> NZC", ABSOLUTE, 3, 4),
    CMP_A("cmp",true, 0xCD, "(A - M) -> NZC", ABSOLUTE, 3, 4),
    DEC_A("dec",true, 0xCE, "M <- (M) - 1", ABSOLUTE, 3, 6),
    DCP_A("dcp",false, 0xCF, "M <- (M)-1, (A-M) -> NZC", ABSOLUTE, 3, 6),
    BNE_R("bne",true, 0xD0, "if Z=0, PC = PC + offset", RELATIVE, 2, 2, 2),
    CMP_INDY("cmp",true, 0xD1, "(A - M) -> NZC", INDY, 2, 5, 1),
    JAM11("jam",false, 0xD2, "[locks up machine]", IMPLIED, 1, 0),
    DCP_INDY("dcp",false, 0xD3, "M <- (M)-1, (A-M) -> NZC", INDY, 2, 8),
    NOP_ZX5("nop",false, 0xD4, "[no operation]", ZPAGEX, 2, 4),
    CMP_ZX("cmp",true, 0xD5, "(A - M) -> NZC", ZPAGEX, 2, 4),
    DEC_ZX("dec",true, 0xD6, "M <- (M) - 1", ZPAGEX, 2, 6),
    DCP_ZX("dcp",false, 0xD7, "M <- (M)-1, (A-M) -> NZC", ZPAGEX, 2, 6),
    CLD_IMP("cld",true, 0xD8, "D <- 0", IMPLIED, 1, 2),
    CMP_AY("cmp",true, 0xD9, "(A - M) -> NZC", ABSOLUTEY, 3, 4, 1),
    NOP_IMP1("nop",false, 0xDA, "[no operation]", IMPLIED, 1, 2),
    DCP_AY("dcp",false, 0xDB, "M <- (M)-1, (A-M) -> NZC", ABSOLUTEY, 3, 7),
    NOP_AX1("nop",false, 0xDC, "[no operation]", ABSOLUTEX, 3, 4, 1),
    CMP_AX("cmp",true, 0xDD, "(A - M) -> NZC", ABSOLUTEX, 3, 4, 1),
    DEC_AX("dec",true, 0xDE, "M <- (M) - 1", ABSOLUTEX, 3, 7),
    DCP_AX("dcp",false, 0xDF, "M <- (M)-1, (A-M) -> NZC", ABSOLUTEX, 3, 7),
    CPX_IMM("cpx",true, 0xE0, "(X - M) -> NZC", IMMEDIATE, 2, 2),
    SBC_INDX("sbc",true, 0xE1, "A <- (A) - M - ~C", INDX, 2, 6),
    NOP_IMM4("nop",false, 0xE2, "[no operation]", IMMEDIATE, 2, 2),
    ISB_INDX("isb",false, 0xE3, "M <- (M) - 1,A <- (A)-M-~C", INDX, 3, 8, 1),
    CPX_Z("cpx",true, 0xE4, "(X - M) -> NZC", ZPAGE, 2, 3),
    SBC_Z("sbc",true, 0xE5, "A <- (A) - M - ~C", ZPAGE, 2, 3),
    INC_Z("inc",true, 0xE6, "M <- (M) + 1", ZPAGE, 2, 5),
    ISB_Z("isb",false, 0xE7, "M <- (M) - 1,A <- (A)-M-~C", ZPAGE, 2, 5),
    INX_IMP("inx",true, 0xE8, "X <- (X) +1", IMPLIED, 1, 2),
    SBC_IMM("sbc",true, 0xE9, "A <- (A) - M - ~C", IMMEDIATE, 2, 2),
    NOP_IMP5("nop",true, 0xEA, "[no operation]", IMPLIED, 1, 2),
    SBC_IMM2("sbc",false, 0xEB, "A <- (A) - M - ~C", IMMEDIATE, 1, 2),
    SBC_A("sbc",true, 0xED, "A <- (A) - M - ~C", ABSOLUTE, 3, 4),
    CPX_A("cpx",true, 0xEC, "(X - M) -> NZC", ABSOLUTE, 3, 4),
    INC_A("inc",true, 0xEE, "M <- (M) + 1", ABSOLUTE, 3, 6),
    ISB_A("isb",false, 0xEF, "M <- (M) - 1,A <- (A)-M-~C", ABSOLUTE, 3, 6),
    BEQ_R("beq",true, 0xF0, "if Z=1, PC = PC + offset", RELATIVE, 2, 2, 2),
    SBC_INDY("sbc",true, 0xF1, "A <- (A) - M - ~C", INDY, 2, 5, 1),
    JAM12("jam",false, 0xF2, "[locks up machine]", IMPLIED, 1, 0),
    ISB_INDY("isb",false, 0xF3, "M <- (M) - 1,A <- (A)-M-~C", INDY, 2, 8),
    NOP_ZX6("nop",false, 0xF4, "[no operation]", ZPAGEX, 2, 4),
    SBC_ZX("sbc",true, 0xF5, "A <- (A) - M - ~C", ZPAGEX, 2, 4),
    INC_ZX("inc",true, 0xF6, "M <- (M) + 1", ZPAGEX, 2, 6),
    ISB_ZX("isb",false, 0xF7, "M <- (M) - 1,A <- (A)-M-~C", ZPAGEX, 2, 6),
    SED_IMP("sed",true, 0xF8, "D <- 1", IMPLIED, 1, 2),
    SBC_AY("sbc",true, 0xF9, "A <- (A) - M - ~C", ABSOLUTEY, 3, 4, 1),
    NOP_IMP6("nop",false, 0xFA, "[no operation]", IMPLIED, 1, 2),
    ISB_AY("isb",false, 0xFB, "M <- (M) - 1,A <- (A)-M-~C", ABSOLUTEY, 3, 7),
    NOP_AX5("nop",false, 0xFC, "[no operation]", ABSOLUTEX, 3, 4, 1),
    SBC_AX("sbc",true, 0xFD, "A <- (A) - M - ~C", ABSOLUTEX, 3, 4, 1),
    INC_AX("inc",true, 0xFE, "M <- (M) + 1", ABSOLUTEX, 3, 7),
    ISB_AX("isb",false, 0xFF, "M <- (M) - 1,A <- (A)-M-~C", ABSOLUTEX, 3, 7),
    ;

    String code;
    boolean standard;
    int mnemonic;
    String description;
    Addressing addressing;
    int bytes;
    int time;
    int timeb;


    static HashMap<Integer, Opcode> map = new HashMap<>();

    static {
        for (Opcode o : values()) {
            map.put(o.mnemonic, o);
        }
    }

    public static Opcode byMnemonic(int mnemonic) {
        return map.get(mnemonic);
    }

    Opcode(String code, boolean standard, int mnemonic, String description, Addressing addressing, int bytes, int time, int timeb) {
        this.code = code;
        this.standard = standard;
        this.mnemonic = mnemonic;
        this.description = description;
        this.addressing = addressing;
        this.bytes = bytes;
        this.time = time;
        this.timeb = timeb;
    }

    Opcode(String code, boolean standard, int mnemonic, String description, Addressing addressing, int bytes, int time) {
        this.code = code;
        this.standard = standard;
        this.mnemonic = mnemonic;
        this.description = description;
        this.addressing = addressing;
        this.bytes = bytes;
        this.time = time;
        this.timeb = 0;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
