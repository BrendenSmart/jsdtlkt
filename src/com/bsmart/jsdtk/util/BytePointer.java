package com.bsmart.jsdtk.util;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

public class BytePointer extends Pointer {

    protected BytePointer(byte value) {
        ptr = arena.allocateFrom(ValueLayout.JAVA_BYTE, value);
    }

    protected BytePointer() {
        ptr = arena.allocate(ValueLayout.JAVA_BYTE);
    }

    protected BytePointer(long address, boolean isAddress) {
        if(isAddress)
            ptr = MemorySegment.ofAddress(address);
    }

    public void set(byte value) {
        ptr.set(ValueLayout.JAVA_BYTE, 0, value);
    }

    public byte get() {
        return ptr.get(ValueLayout.JAVA_BYTE, 0);
    }

}
