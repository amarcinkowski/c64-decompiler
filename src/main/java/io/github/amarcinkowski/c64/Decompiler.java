package io.github.amarcinkowski.c64;

import io.github.amarcinkowski.c64.asm.Parser;
import io.github.amarcinkowski.c64.output.Language;
import io.github.amarcinkowski.c64.output.LanguagesFactory;
import io.github.amarcinkowski.c64.utils.Files;

import java.io.IOException;

import static io.github.amarcinkowski.c64.utils.Files.readFile;

public class Decompiler {

        final static String FILE = "/home/am/git/c64asm/colors2.prg";
//    final static String FILE = "src/test/resources/colors2.prg";
//    final static String FILE = "/home/am/Pulpit/giana/ggs.prg";
//    great giana s.+.prg
    final static byte[] fileContent = readFile(FILE);

    public static void main(String[] args) throws IOException {
        Parser p = new Parser();
        p.parse(fileContent);

        Language asm = LanguagesFactory.get("asm");
        Language bytecode = LanguagesFactory.get("byte");
//        Language basic = LanguagesFactory.get("basic");

        bytecode.parse(p);
        asm.parse(p);
//        basic.parse(p);

        System.out.println(bytecode.toString());
        System.out.println(asm.toString());
        Files.toFile(asm.toString());
//        System.out.println(basic.toString());
    }

}
