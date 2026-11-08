package com.bsmart.jsdtk.util;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public final class DoublePointer extends Pointer {

    protected DoublePointer(double value) {
        ptr = arena.allocateFrom(ValueLayout.JAVA_DOUBLE, value);
    }

    protected DoublePointer() {
        ptr = arena.allocate(ValueLayout.JAVA_DOUBLE);
    }

    protected DoublePointer(long address, boolean ofAddress) {
        if(ofAddress)
            ptr = MemorySegment.ofAddress(address);
    }

    public double get() {
        return ptr.get(ValueLayout.JAVA_DOUBLE, 0);
    }

    public void set(double value) {
        ptr.set(ValueLayout.JAVA_DOUBLE, 0, value);
    }

}
