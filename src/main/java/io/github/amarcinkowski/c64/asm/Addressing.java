package io.github.amarcinkowski.c64.asm;

import io.github.amarcinkowski.c64.memory.MemoryMap;

import static io.github.amarcinkowski.c64.utils.Numbers.signed;

public enum Addressing {

    ABSOLUTE("", ""), // A
    ABSOLUTEX("", ",x"), // AX
    ABSOLUTEY("", ",y"), // AY
    ACCUMULATOR("?", "?"), // ACC
    IMMEDIATE("#", ""), // IMM
    IMPLIED("_", "."), // IMP
    INDIRECT("(", ")"), // IND
    INDX("(", ",x)"), // INDX
    INDY("(", "),y"), // INDY
    RELATIVE("", ""), // R
    ZPAGE("", ""), // Z
    ZPAGEX("", ",x"), // ZX
    ZPAGEY("", ",y"), // ZY

    ;
    public String prefix;
    public String postfix;

    Addressing(String prefix, String postfix) {
        this.prefix = prefix;
        this.postfix = postfix;
    }

    public static String getFormattedData(Addressing addressing, MemoryMap ma, String dec) {
        String mem = "";
        switch (addressing) {
            case ABSOLUTEX:
            case ABSOLUTEY:
            case ABSOLUTE:
            case ZPAGE:
                mem = (ma == null ? "?" : ma.toString());
                break;
            case RELATIVE: // used in relative jumps
                mem = signed(dec);
                break;
            default:
                mem = dec;
        }
        return mem;
    }
}
