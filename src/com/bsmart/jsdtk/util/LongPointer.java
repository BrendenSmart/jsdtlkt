package com.bsmart.jsdtk.util;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class LongPointer extends Pointer {

    protected LongPointer(long value) {
        ptr = arena.allocateFrom(ValueLayout.JAVA_LONG, value);
    }

    protected LongPointer() {
        ptr = arena.allocate(ValueLayout.JAVA_LONG);
    }

    protected LongPointer(long address, boolean isAddress) {
        if(isAddress)
        ptr = MemorySegment.ofAddress(address);
    }

    public void set(long value) {
        ptr.set(ValueLayout.JAVA_LONG, 0, value);
    }

    public long get() {
        return ptr.get(ValueLayout.JAVA_LONG, 0);
    }

}
