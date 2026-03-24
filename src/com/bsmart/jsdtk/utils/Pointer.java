package com.bsmart.jsdtk.utils;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout.OfInt;

public abstract class Pointer {

    protected static final Arena arena;

    static {
        arena = Arena.global();
    }

    public static IntPointer malloc(int value) {
        return new IntPointer(value);
    }

    public static StringPointer malloc(String value) {
        return new StringPointer(value);
    }

    protected MemorySegment ptr;

    public MemorySegment memorySegment() {
        if(ptr != null) return ptr;
        else throw new NullPointerException("Pointer points to no address");
    }

    public void free() {
        arena.close();
    }

    public long getPhysicalAddress() {
        return ptr.address();
    }

}
