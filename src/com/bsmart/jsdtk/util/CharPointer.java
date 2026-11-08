package com.bsmart.jsdtk.util;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class CharPointer extends Pointer {

    protected CharPointer(char value) {
        ptr = arena.allocateFrom(ValueLayout.JAVA_CHAR, value);
    }

    protected CharPointer() {
        ptr = arena.allocate(ValueLayout.JAVA_CHAR);
    }

    protected CharPointer(long address, boolean isAddress) {
        if(isAddress)
            ptr = MemorySegment.ofAddress(address);
    }

    public void set(char value) {
        ptr.set(ValueLayout.JAVA_CHAR, 0, value);
    }

    public char get() {
        return ptr.get(ValueLayout.JAVA_CHAR, 0);
    }

}
