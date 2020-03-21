package io.github.amarcinkowski.c64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.github.amarcinkowski.c64.utils.Arrays.range;
import static io.github.amarcinkowski.c64.utils.Numbers.hex;
import static io.github.amarcinkowski.c64.utils.Arrays.read2B;

public class PrgLoader {

    private final static Logger logger = LoggerFactory.getLogger(PrgLoader.class);

    private final static int CODE_START_IN_FILE = 2;
    private final static int BASIC_START_IN_FILE = 4;
    public int destinationAddress;
    public int asmStart;
    public int asmStartInFile;
    public byte[] basic;
    public byte[] asm;
    public byte[] codeBlock;


    public void readHeaders(byte[] code) {
        destinationAddress = read2B(code, 0);
        asmStart = read2B(code, CODE_START_IN_FILE);
        asmStartInFile = asmStart - destinationAddress;

        logger.debug("destination adr " + destinationAddress + " (" + hex(destinationAddress) + ")");
        logger.debug("asm start       " + asmStart + " (" + hex(asmStart) + ")");
    }

    public void load(byte[] fileContent) {
        readHeaders(fileContent);
        codeBlock = range(fileContent, CODE_START_IN_FILE, fileContent.length);
        basic = range(fileContent, BASIC_START_IN_FILE, asmStartInFile + CODE_START_IN_FILE);
        asm = range(fileContent, CODE_START_IN_FILE + asmStartInFile, fileContent.length);
    }
}
