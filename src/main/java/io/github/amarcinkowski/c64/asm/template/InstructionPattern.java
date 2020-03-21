package io.github.amarcinkowski.c64.asm.template;

import io.github.amarcinkowski.c64.asm.Opcode;
import io.github.amarcinkowski.c64.utils.BytecodeUtils;
import io.github.amarcinkowski.c64.utils.Numbers;

import java.util.regex.Pattern;


public class InstructionPattern {

    public final static String ANY_VALUE = "[0-9a-f ]";
    public final static String ENDING_ESCAPED = Pattern.quote(BytecodeUtils.COMMAND_ENDING);

    public byte[] data;

    public boolean extractData = false;

    public boolean anyData = true;

    public boolean optional = false;

    Opcode opcode;

    private String getAnyValuePattern(String pattern, int length) {
        return String.format("%s{%d}", pattern, length);
    }

    private String getDataPattern() {
        if (anyData) {
            int numberOfDataBytes = opcode.length - 1;
            int numberOfSpaces = numberOfDataBytes - 1;
            return getAnyValuePattern(ANY_VALUE, numberOfDataBytes * 2 + numberOfSpaces);
        } else {
            return Numbers.hex(data).trim();
        }
    }

    private String decorateWithGroup(String str) {
        return String.format("(%s)", str);
    }

    public String getPattern() {
        StringBuilder builder = new StringBuilder();
        builder.append(Numbers.hex(opcode.hex));
        if (opcode.length > 1) {
            builder.append(BytecodeUtils.BETWEEN_OPCODE_AND_DATA);
            String dataPattern = getDataPattern();
            dataPattern = extractData ? decorateWithGroup(dataPattern) : dataPattern;
            dataPattern = optional ? decorateWithGroup(dataPattern) + "?" : dataPattern;
            builder.append(dataPattern);
        }
        builder.append(ENDING_ESCAPED);
        return builder.toString();
    }

    @Override
    public String toString() {
        return getPattern();
    }

}
