package io.github.amarcinkowski.c64.basic;

import io.github.amarcinkowski.c64.utils.Numbers;

import java.util.HashMap;

public enum BasicToken {
    //    Hex	Dec	Token meaning
    END("END", 0x80),
    FOR("FOR", 0x81),
    NEXT("NEXT", 0x82),
    DATA("DATA", 0x83),
    INPUT_("INPUT#", 0x84),
    INPUT("INPUT", 0x85),
    DIM("DIM", 0x86),
    READ("READ", 0x87),
    LET("LET", 0x88),
    GOTO("GOTO", 0x89),
    RUN("RUN", 0x8A),
    IF("IF", 0x8B),
    RESTORE("RESTORE", 0x8C),
    GOSUB("GOSUB", 0x8D),
    RETURN("RETURN", 0x8E),
    REM("REM", 0x8F),
    STOP("STOP", 0x90),
    ON("ON", 0x91),
    WAIT("WAIT", 0x92),
    LOAD("LOAD", 0x93),
    SAVE("SAVE", 0x94),
    VERIFY("VERIFY", 0x95),
    DEF("DEF", 0x96),
    POKE("POKE", 0x97),
    PRINT_("PRINT#", 0x98),
    PRINT("PRINT", 0x99),
    CONT("CONT", 0x9A),
    LIST("LIST", 0x9B),
    CLR("CLR", 0x9C),
    CMD("CMD", 0x9D),
    SYS("SYS", 0x9E),
    OPEN("OPEN", 0x9F),
    CLOSE("CLOSE", 0xA0),
    GET("GET", 0xA1),
    NEW("NEW", 0xA2),
    TAB("TAB(", 0xA3),
    TO("TO", 0xA4),
    FN("FN", 0xA5),
    SPC("SPC(", 0xA6),
    THEN("THEN", 0xA7),
    NOT("NOT", 0xA8),
    STEP("STEP", 0xA9),
    PLUS("+", 0xAA),
    MINUS("-", 0xAB),
    MULTIPLY("*", 0xAC),
    DIVIDE("/", 0xAD),
    POWER("^", 0xAE),
    AND("AND", 0xAF),
    OR("OR", 0xB0),
    GT(">", 0xB1),
    EQ("=", 0xB2),
    LT("<", 0xB3),
    SGN("SGN", 0xB4),
    INT("INT", 0xB5),
    ABS("ABS", 0xB6),
    USR("USR", 0xB7),
    FRE("FRE", 0xB8),
    POS("POS", 0xB9),
    SQR("SQR", 0xBA),
    RND("RND", 0xBB),
    LOG("LOG", 0xBC),
    EXP("EXP", 0xBD),
    COS("COS", 0xBE),
    SIN("SIN", 0xBF),
    TAN("TAN", 0xC0),
    ATN("ATN", 0xC1),
    PEEK("PEEK", 0xC2),
    LEN("LEN", 0xC3),
    STR$("STR$", 0xC4),
    VAL("VAL", 0xC5),
    ASC("ASC", 0xC6),
    CHR$("CHR$", 0xC7),
    LEFT$("LEFT$", 0xC8),
    RIGHT$("RIGHT$", 0xC9),
    MID$("MID$", 0xCA),
    GO("GO", 0xCB),
    ;
    String command;
    int code;

    static HashMap<Integer, BasicToken> map = new HashMap<>();
    static {
        for(BasicToken b : BasicToken.values()) {
            map.put(b.code, b);
//            System.out.println(b.code + " " + Numbers.hex(b.code) + " " + b.command);
        }
    }

    public static BasicToken getByCode(int code) {
//        System.out.println("size" + map.size());
//        System.out.println("here" + map.get(0x9e));
        return map.get(code);
    }

    BasicToken(String command, int code) {
        this.code = code;
        this.command = command;
    }
}
