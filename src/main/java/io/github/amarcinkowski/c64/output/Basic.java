package io.github.amarcinkowski.c64.output;

import io.github.amarcinkowski.c64.asm.Instruction;
import io.github.amarcinkowski.c64.asm.Parser;

import java.util.List;
import java.util.stream.Collectors;

import static io.github.amarcinkowski.c64.utils.Numbers.dec;

// http://fileformats.archiveteam.org/wiki/Commodore_BASIC_tokenized_file
// tokenized basic file
public class Basic extends Language {

    String getCommand(Instruction c) {
        switch (c.opcode) {
            case LDA_IMM:
                return "A=" + dec(c.data);
                // A = PEEK(53281)   A <- M
                //POKE 53280, PEEK(53281)    M <- M
//                    integer_variable = PEEK(address)
//                    POKE address, value
            case STA_ABS:
                return "POKE " + dec(c.data) + ", A";
            case JMP_ABS:
                return "GOTO " + "10";
            default:
                return "?";
        }

    }

    @Override
    public void parse(Parser p) {
        List<Instruction> instructions = p.instructions;
        int line = 1;
        for (Instruction c : instructions) {
            output.add((line++*10) +" "+ getCommand(c));
        }
    }

    @Override
    public String toString() {
        return output.stream().collect(Collectors.joining("\n"));
    }
}


/* read keyboard

10 a = peek(197): if a=64 then 10
20 print "the pushed key has got the value: "; a
40 if a=46 then end
30 if a<>0 then 10

read two-byte pointer in the low-byte/high-byte form to integer B
B% = PEEK(43) + PEEK(44) * 256

 */