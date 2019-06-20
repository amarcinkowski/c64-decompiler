package io.github.amarcinkowski.c64.memory;

import java.util.Comparator;

public class SmallestRangeSorter implements Comparator<MemoryAddress> {
    @Override
    public int compare(MemoryAddress ma1, MemoryAddress ma2) {
        return ma1.size - ma2.size;
    }

}