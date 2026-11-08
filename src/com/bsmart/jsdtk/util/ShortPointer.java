package com.bsmart.jsdtk.util;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class ShortPointer extends Pointer {

    protected ShortPointer(short value) {
        ptr = arena.allocateFrom(ValueLayout.JAVA_SHORT, value);
    }

    protected ShortPointer() {
        ptr = arena.allocate(ValueLayout.JAVA_SHORT);
    }

    protected ShortPointer(long address, boolean isAddress) {
        if(isAddress)
            ptr = MemorySegment.ofAddress(address);
    }

    public void set(short value) {
        ptr.set(ValueLayout.JAVA_SHORT, 0, value);
    }

    public short get() {
        return ptr.get(ValueLayout.JAVA_SHORT, 0);
    }

}
