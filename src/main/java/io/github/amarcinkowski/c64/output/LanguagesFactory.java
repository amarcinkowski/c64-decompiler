package io.github.amarcinkowski.c64.output;

public class LanguagesFactory {

    // todo add Language superclass
    public static Language get(String name) {
        switch (name) {
            case "asm":
                return new Assembler();
            case "byte":
                return new Bytecode();
            case "basic":
                return new Basic();
            default:
                return null;
        }
    }

}
