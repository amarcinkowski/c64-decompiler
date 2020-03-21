package io.github.amarcinkowski.c64.utils;

import java.util.*;

public class Enums {

    @SuppressWarnings("unchecked")
    public static String toString(HashMap<? extends Enum, Integer> map) {
        StringBuilder sb = new StringBuilder();
        Set keys =  map.keySet();
        TreeSet<Enum> t = new TreeSet<>();
        t.addAll(keys);
        for (Enum e : t) {
            sb.append(e);
            sb.append(":");
            sb.append(map.get(e));
            sb.append(" ");
        }
        return sb.toString();
    }

}
