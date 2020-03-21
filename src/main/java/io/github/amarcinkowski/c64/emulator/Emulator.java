package io.github.amarcinkowski.c64.emulator;

import io.github.amarcinkowski.c64.utils.Enums;

import java.util.HashMap;
import java.util.Map;

import static io.github.amarcinkowski.c64.utils.Files.readFile;

/**
 * http://archive.6502.org/datasheets/mos_6510_mpu.pdf
 */
public class Emulator {

    final HashMap<Register6502, Integer> registers = new HashMap<>();
    final HashMap<ProcessorStatusFlags, Integer> status = new HashMap<>();
    final byte[] mem;

    public Emulator() {
        for (Register6502 r : Register6502.values()) {
            registers.put(r, r.defaultValue);
        }
        for (ProcessorStatusFlags f : ProcessorStatusFlags.values()) {
            status.put(f, f.defaultValue);
        }
        mem = readFile("src/main/resources/c64.mem");
    }

    @Override
    public String toString() {
        return Enums.toString(status) + "\t" + Enums.toString(registers);
    }
}
