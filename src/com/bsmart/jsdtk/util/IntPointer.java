package com.bsmart.jsdtk.util;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout.OfInt;

public class IntPointer extends Pointer {

    protected IntPointer(int value) {
        ptr = arena.allocateFrom(OfInt.JAVA_INT, 0, value);
    }

    protected IntPointer() {
        ptr = arena.allocate(OfInt.JAVA_INT);
    }

    protected IntPointer(long address, boolean isAddress) {
        if(isAddress)
            ptr = MemorySegment.ofAddress(address);
    }

    public void set(int value) {
        ptr.set(OfInt.JAVA_INT, 0, value);
    }

    public int get() {
        return ptr.get(OfInt.JAVA_INT, 0);
    }

}
