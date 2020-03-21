package io.github.amarcinkowski.c64;

import io.github.amarcinkowski.c64.asm.Assembler;
import io.github.amarcinkowski.c64.asm.AssemblerDecorator;
import io.github.amarcinkowski.c64.asm.template.Template;
import io.github.amarcinkowski.c64.basic.Basic;
import io.github.amarcinkowski.c64.bytecode.Bytecode;
import io.github.amarcinkowski.c64.emulator.Emulator;
import io.github.amarcinkowski.c64.utils.BytecodeUtils;
import io.github.amarcinkowski.c64.utils.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Collectors;

import static io.github.amarcinkowski.c64.utils.Files.readFile;

public class Decompiler {

    final static Logger logger = LoggerFactory.getLogger(Decompiler.class);
//        final static String FILE = "src/test/resources/colors2.prg";

    static String FILE = "/media/am/eb1cf6e9-eafb-4546-9edb-c8e9195d9643/gamebase c64/apps/";

    static {
        FILE += "c64/1_WWF0/wwf wrestling";
    }
/*
    c64/1_1STDIVIS/1st division m.
    c64/1_ALIENSY0/alien syndrome+
    c64/1_ALTCITY4/ar-city char.ed.
    c64/1_AMBASSAD/ambassador
    c64/1_AMERICHA/american chall.
    c64/1_ANIMALS!/animals!
    c64/1_ANOTHER0/another world+
    c64/1_ANOTHER1/another w. note
    c64/1_ANOTHER2/another wor. dox
    c64/1_APACHEST/apache strike
    c64/1_AP/ap

    c64/1_APOCAL-E/apocalypse now+
    c64/1_ARABIANT/arabian treasur.
    c64/1_ARSCET/arscet
    c64/1_AUFSIOUX/aufst. der sioux
    c64/1_AUSTRIA2/austria dash 02
    c64/1_AVVENTUR/avventura 1
    c64/1_MABELSMA/mabel's mansion
    c64/1_MAGICLAN/magic land
    c64/1_MAINFRAM/main frame
    c64/1_MANAGE-D/manager
    c64/1_MANIACD0/maniac mansion
    c64/1_MANUFAKT/manufaktur
    c64/1_MARKOFTV/mark of vampire
    c64/1_MARS0/mars
    c64/1_MASTRIVA/master trivia
    c64/1_MATCHWIT/match-wits
    c64/1_MATHBLA1/math blaster
    c64/1_MATSU/ma tsu
    c64/1_MAZECSET/maze constr. set
    c64/1_MAZEMONS/maze monsters
    c64/1_MEANSTR4/dox loader
    c64/1_MEDIEVL1/medieval l. docs
    c64/1_MEGABOU4/mega boulder 4
    c64/1_MEGAMA1K/megamania64 1k
    c64/1_METALGE2/metal gear dox
    c64/1_MICKEY0/mickey
    c64/1_MICROMU0/micro-mud
    c64/1_MINDWHE1/mindwheel s2
    c64/1_MINDWHE2/mindwheel s3
    c64/1_MINES!/mines!
    c64/1_MINESWP3/mine sweeper
    c64/1_MINIATG1/miniature golf
    c64/1_MINIZORK/mini zork i
    c64/1_MISSIONA/mission
    c64/1_MISSX2/mission x-2
    c64/1_MLB1A/mlb
    c64/1_MLB1-BOX/box
    c64/1_MLB1-GMO/gmo
    c64/1_MODEMWA1/modem war codes
    c64/1_MONDU0/mondu's f.palace
    c64/1_MOONMIST/moonmist
    c64/1_MOONRAID/moon raider
    c64/1_MORSELEH/morselehrgang
    c64/1_MOTORMAS/motor massacre+
    c64/1_MOVIEB-E/movie business
    c64/1_MTVREMC0/mtv remote ctrl
    c64/1_MUELLOMA/muellomat
    c64/1_MYSTICCA/mystic castle
    c64/1_MYTH1/myth dox
    c64/1_MYTHCRT1/-myth cartridge-
    c64/1_MYTH/game
    c64/1_STKEENE0/steve keene
    c64/1_WWF0/wwf wrestling
*/

    final static byte[] fileContent = readFile(FILE);

    public static void main(String[] args) throws Exception {

        PrgLoader prg = new PrgLoader();
        prg.load(fileContent);

        Basic basic = new Basic();
        basic.parse(prg.basic);

        Bytecode bytecode = new Bytecode();
        bytecode.parse(prg.asm, prg.destinationAddress + prg.asmStartInFile);

        Assembler asm = new Assembler();
        asm.parse(bytecode.instructions);

        logger.trace("raw " + BytecodeUtils.format(bytecode));
        logger.trace("decorated bytecode" + BytecodeUtils.hexFormat(bytecode.toString(), prg.asmStart) + "\n");

        String formatted = BytecodeUtils.format(bytecode);
        for (Class<? extends Template> tc : Template.getSubtypes()) {
            Template t = tc.newInstance();
            t.findTemplateInCode(formatted);
        }

        Emulator e = new Emulator();
        logger.info(e.toString());

        // stream.... to hex string ... move to utils
        String asmStr = bytecode.instructions.stream().map(Object::toString).map(String::trim).collect(Collectors.joining("."));
        logger.debug(BytecodeUtils.hexFormat(asmStr,0x80b).substring(0,500) + "  (...)");

        Files.toFile(asm.toString());

//        byte[] codeblock = prg.codeBlock;
//        byte[] range;
//        System.out.println("mem size " + mem.length);
//        System.out.println("block size " + codeblock.length);
//        mem = replace(mem, codeblock, 0x0801);
//        System.out.println("replace");
//        System.out.println("mem size " + mem.length);
//        range = Arrays.range(mem, 0x80b, );
//        logger.info(java.util.Arrays.toString(range));
//        byte[] copied = Arrays.range(mem, 0xb07 + 1, 0xb07 + 0xbd);
//        mem = replace(mem, copied, 0xfc + 1);

//        /*byte[]*/ range = Arrays.range(mem,0x101, 0x130);
//        Assembler asm2 = new Assembler();
//        asm2.parse(range, 0x101);
        logger.info(new AssemblerDecorator(asm).toString());

//        logger.debug("> " + bytecode.instructions.stream().map(Object::toString).collect(Collectors.joining("\n")));

//        System.out.println("\n\nx64 -remotemonitor -autoload \"" + FILE + "\" &");
//        System.out.println("telnet 127.0.0.1 6510\n\n");

    }





}
