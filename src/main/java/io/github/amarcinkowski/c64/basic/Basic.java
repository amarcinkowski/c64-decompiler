package io.github.amarcinkowski.c64.basic;

import io.github.amarcinkowski.c64.asm.Instruction;
import io.github.amarcinkowski.c64.utils.Arrays;
import io.github.amarcinkowski.c64.utils.Numbers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.github.amarcinkowski.c64.utils.Numbers.dec;
import static io.github.amarcinkowski.c64.utils.Numbers.hex;

// http://fileformats.archiveteam.org/wiki/Commodore_BASIC_tokenized_file
// tokenized basic file
public class Basic {

    private final static Logger logger = LoggerFactory.getLogger(Basic.class);

/*    String getCommand(Instruction c) {
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

    }*/

    public void parse(byte[] basic) {
        for(int i = 0; i < basic.length; i++) {
            int lineNumber = Arrays.read2B(basic, i);
//            System.out.println(" line " + lineNumber);
            i+=2;
            int command = basic[i] & 0xff;
            System.out.println(">>  " + basic[i] + " " + command);
            BasicToken t = BasicToken.getByCode(command);
//            System.out.println(hex(basic[i]) + ":" + t);
            String s = "";
            try {
                while (basic[i] != 0) {
                    i++;
                    if (i == basic.length) {
                    break;
                    }
                    if ( basic[i] == 0) {
                        s += "\n";
                    } else {
                        s += (char) basic[i];
                    }
//                    System.out.println("s= " + s + " ((" + basic[i] + ")) " + (char)basic[i] );
                }
//                System.out.println(" param " + s);
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.info(lineNumber + " " + t + " " + s);
        }
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