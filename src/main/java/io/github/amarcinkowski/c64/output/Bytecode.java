package io.github.amarcinkowski.c64.output;

import io.github.amarcinkowski.c64.asm.Instruction;
import io.github.amarcinkowski.c64.asm.Parser;

import java.util.List;
import java.util.stream.Collectors;

import static io.github.amarcinkowski.c64.utils.Numbers.hex;
import static io.github.amarcinkowski.c64.utils.Strings.addNewLines;
import static io.github.amarcinkowski.c64.utils.Strings.addSpaces;

public class Bytecode extends Language {

    public static final String COMMAND_ENDING = ".";
    public static final String BETWEEN_OPCODE_AND_DATA = "_";


    public Bytecode() {
    }

    public void parse(Parser p) {
        List<Instruction> instructions = p.instructions;
        for(Instruction c : instructions) {
            output.add(c.toString());
        }
    }

    public void parse(byte[] array) {
        for (int i = 0; i < array.length; i++) {
            output.add(hex(array[i]));
            output.add(" ");
            if ((i + 1) % 4 == 0) {
                output.add(" ");
            }
            if ((i + 1) % 16 == 0) {
                output.add("\n");
            }
        }
    }

    @Override
    public String toString() {
        String header = "\n-- output\n00 01 02 03  04 05 06 07  08 09 0a 0b  0c 0d 0e 0f\n\n";
        String data = output.stream().map(String::trim).collect(Collectors.joining(COMMAND_ENDING));
        return header + addNewLines.apply(addSpaces.apply(data));
    }
}
