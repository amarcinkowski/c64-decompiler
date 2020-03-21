package io.github.amarcinkowski.c64.asm.template;

import io.github.amarcinkowski.c64.utils.Numbers;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Template {

    public static final Logger logger = LoggerFactory.getLogger(Template.class);

    public ArrayList<InstructionPattern> instructions = new ArrayList<>();

    // short for InstructionPattern builder
    protected static IPBuilder b() {
        return new IPBuilder();
    }

    protected void addInstruction(InstructionPattern ip) {
        instructions.add(ip);
    }

    public String getRegularExpression() {
        StringBuilder sb = new StringBuilder();
        logger.trace("instructions " + instructions.stream().map(s -> s.getPattern()).collect(Collectors.joining()));
        for (InstructionPattern ip : instructions) {
            sb.append(ip.toString());
        }
        return sb.toString();
    }

    public Pattern getPattern() {
        return Pattern.compile(getRegularExpression());
    }

    public String[] getArguments(String str) {
        ArrayList<String> l = new ArrayList<>();
        Pattern p = getPattern();
        Matcher m = p.matcher(str);
        if (m.find()) {
            for (int i = 1; i <= m.groupCount(); i++) {
                logger.trace(" Group " + i + "/" + m.groupCount() + ": " + Numbers.inv(m.group(i)));
                l.add(Numbers.inv(m.group(i)));
            }
        }
        return l.toArray(new String[]{});
    }

    public void findTemplateInCode(String bytecode) {
        Matcher m = getPattern().matcher(bytecode);
        while (m.find()) {
            String[] args = getArguments(m.group());
            logger.debug("Found template " + m.group() + "\t" + format(MemCopyYCounterPlus.DESC, args));
        }

    }

    private static String format(String desc, String[] args) {
        String s = "";
        try {
            logger.trace(desc + " - " + Arrays.toString(args));
            s = String.format(desc, (Object[]) args);
        } catch (Exception e) {
            logger.debug("Cannot load " + desc + " " + Arrays.toString(args));
        }
        return s;
    }

    public static Set<Class<? extends Template>> getSubtypes() {
        Reflections reflections = new Reflections("io.github.amarcinkowski.c64.asm.template");
        Set<Class<? extends Template>> allClasses = reflections.getSubTypesOf(Template.class);
        return allClasses;
    }

    @Override
    public String toString() {
        return getRegularExpression();
    }

}
