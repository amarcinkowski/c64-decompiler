package io.github.amarcinkowski.c64.asm;

public enum Addressing {

    ABSOLUTE("", ""), // A
    ABSOLUTEX("", ",x"), // AX
    ABSOLUTEY("", ",y"), // AY
    ACCUMULATOR("?", "?"), // ACC
    IMMEDIATE("#", ""), // IMM
    IMPLIED("?", "?"), // IMP
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
}
