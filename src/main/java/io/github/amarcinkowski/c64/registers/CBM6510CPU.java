package io.github.amarcinkowski.c64.registers;

import java.util.HashMap;
import java.util.Map;

/**
 * http://archive.6502.org/datasheets/mos_6510_mpu.pdf
 */
public class CBM6510CPU {

    HashMap<Register6502, Integer> status = new HashMap<>();

    public CBM6510CPU() {
        for (Register6502 r : Register6502.values()) {
            status.put(r, r.defaultValue);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Register6502,Integer> e :status.entrySet()) {
            sb.append(e.getKey());
            sb.append(":");
            sb.append(e.getValue());
            sb.append(" ");
        }
        return sb.toString();
    }
}
