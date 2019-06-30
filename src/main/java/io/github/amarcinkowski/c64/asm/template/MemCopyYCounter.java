package io.github.amarcinkowski.c64.asm.template;

import io.github.amarcinkowski.c64.utils.Numbers;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.github.amarcinkowski.c64.asm.Opcode.*;

public class MemCopyYCounter {


    public static IPBuilder b() {
        return new IPBuilder();
    }

    public static InstructionPattern[] opcodes = {b().op(LDA_ABY).arg().b(), b().op(STA_ABY).arg().b(), b().op(INY_IMP).b(), b().op(BNE_REL).data(new byte[]{(byte) 0xf7}).b()};

    public static final String NAME = "MEM_COPY_Y";
    public static final String DESC = "COPY n BYTES FROM %s TO %s";

    public static void main(String[] args) {
        MemCopyYCounter m = new MemCopyYCounter();
        System.out.println(m);
    }

    public static Pattern getPattern() {
        return Pattern.compile(getRegularExpression());
    }

    // FIXME optimize should be done once
    public final static String getRegularExpression() {
        StringBuilder sb = new StringBuilder();
        for (InstructionPattern ip : opcodes) {
            sb.append(ip.toString());
        }
        return sb.toString();
    }

    public static String[] getArguments(String str) {
        ArrayList<String> l = new ArrayList<>();
        Pattern p = getPattern();
        Matcher m = p.matcher(str);
        if (m.find()) {
            for (int i = 1; i <= m.groupCount(); i++) {
                // trace
                System.out.println(" GROUP " + i + "/" + m.groupCount() +": "+ Numbers.inv(m.group(i)));
                l.add(Numbers.inv(m.group(i)));
            }
        }
        return l.toArray(new String[]{});
    }

    @Override
    public String toString() {
        return getRegularExpression();
    }
}
