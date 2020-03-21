package io.github.amarcinkowski.c64.utils;

import io.github.amarcinkowski.c64.bytecode.Bytecode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static io.github.amarcinkowski.c64.utils.Strings.*;

public class BytecodeUtils {

    public static final String COMMAND_ENDING = ".";
    public static final String BETWEEN_OPCODE_AND_DATA = "_";
    public static final String HEADER = "\n--\n\t\t00 01 02 03  04 05 06 07  08 09 0a 0b  0c 0d 0e 0f" +
            "\n\t\t--------------------------------------------------";
    public static final Pattern NEWLINE_PATTERN = Pattern.compile("\n");

    /**
     * adds "." between instructions and _ between opcode and data
     */
    public static String format(Bytecode bytecode) {
        return bytecode.instructions.stream().map(Object::toString).map(String::trim).collect(Collectors.joining(COMMAND_ENDING));
    }

    /**
     * add spaces before data so address can match header's 00 01 .. 0f
     */
    private static String prependSpaces(String data, int adr) {
        int spacesFirstLine = (adr % 16) * 3;
        return times(" ", spacesFirstLine) + data;
    }

    /**
     * new lines every 16 bytes and spaces every 4
     */
    public static String hexFormat(String data) {
        return addNewLines.apply(addSpaces.apply(data));
    }

    /**
     * hex format with header and address
     */
    public static String hexFormat(String data, int adr) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(HEADER);
        buffer.append(addAddressAtLineBeginning("\n" + hexFormat(prependSpaces(data, adr)), adr));
        return buffer.toString();
    }

    /**
     * replace every newline with hex formatted address
     */
    private static String addAddressAtLineBeginning(String data, int address) {
        StringBuffer buffer = new StringBuffer();
        Matcher matcher = NEWLINE_PATTERN.matcher(data);
        while (matcher.find()) {
            matcher.appendReplacement(buffer, "\n" + Numbers.hex2B(address - (address % 16)) + "\t");
            address += 16;
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

}
