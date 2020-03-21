package io.github.amarcinkowski.c64.emulator;

/**
 * https://www.c64-wiki.com/wiki/Stack
 */
public class Stack {
    int stackPointer = 0xFF;
//    That is, the first entry in the stack is $01FF and the last entry is $0100.
    static final int STACK_END = 0x0100;
    static final int STACK_START = 0x01FF;

    public void push(byte push) {
        // NYI
    }

    public byte pop() {
        // NYI
        return 0;
    }


}
