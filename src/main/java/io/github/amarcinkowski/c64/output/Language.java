package io.github.amarcinkowski.c64.output;

import io.github.amarcinkowski.c64.asm.Parser;

import java.util.ArrayList;

public abstract class Language {

    ArrayList<String> output = new ArrayList<>();

    public abstract void parse(Parser p);

}
