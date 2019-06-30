package io.github.amarcinkowski.c64.asm.template;

import io.github.amarcinkowski.c64.asm.Opcode;
import io.github.amarcinkowski.c64.output.Bytecode;
import io.github.amarcinkowski.c64.utils.Numbers;

import java.util.regex.Pattern;

class IPBuilder {

    private InstructionPattern ip = new InstructionPattern();

    public IPBuilder data(byte[] data) {
        ip.data = data;
        ip.anyData = false;
        return this;
    }

    public IPBuilder op(Opcode opcode) {
        ip.opcode = opcode;
        return this;
    }

    public IPBuilder arg() {
        ip.hasArgument = true;
        return this;
    }

    public InstructionPattern build() {
        return ip;
    }

    public InstructionPattern b() {
        return build();
    }
}

public class InstructionPattern {

    public static String ANY_VALUE = "[0-9a-f ]";
    public static String escaped = Pattern.quote(Bytecode.COMMAND_ENDING);

    public byte[] data;

    public boolean hasArgument = false;

    public boolean anyData = true;

    Opcode opcode;

    public String getPatetrn() {
        StringBuilder builder = new StringBuilder();
        builder.append(Numbers.hex(opcode.hex));
        if (opcode.bytes > 1) {
            builder.append(Bytecode.BETWEEN_OPCODE_AND_DATA);
            if (hasArgument) {
                builder.append("(");
            }
            if (anyData) {
                int numberOfDataBytes = opcode.bytes - 1;
                int numberOfSpaces = numberOfDataBytes - 1;
                builder.append(ANY_VALUE);
                builder.append("{");
                builder.append(numberOfDataBytes * 2 + numberOfSpaces);
                builder.append("}");
            } else {
                builder.append(Numbers.hex(data).trim());
            }
            if (hasArgument) {
                builder.append(")");
            }
        }
        builder.append(escaped);
        return builder.toString();
    }

    @Override
    public String toString() {
        return getPatetrn();
    }

}
