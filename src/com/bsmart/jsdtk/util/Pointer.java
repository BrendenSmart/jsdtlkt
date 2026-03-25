package com.bsmart.jsdtk.util;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

// TODO add array functionality

public abstract class Pointer {

    protected static final Arena arena;

    static {
        arena = Arena.global();
    }

    public static void free(Pointer p) {
        p.ptr.unload();
    }

    public static IntPointer mallocInt() {
        return new IntPointer();
    }

    public static IntPointer mallocInt(int value) {
        return new IntPointer(value);
    }

    public static FloatPointer mallocFloat(float value) {
        return new FloatPointer(value);
    }

    public static FloatPointer mallocFloat() {
        return new FloatPointer();
    }

    public static LongPointer mallocLong(long value) {
        return new LongPointer(value);
    }

    public static LongPointer mallocLong() {
        return new LongPointer();
    }

    public static ShortPointer mallocShort(short value) {
        return new ShortPointer(value);
    }

    public static ShortPointer mallocShort() {
        return new ShortPointer();
    }

    public static CharPointer mallocChar(char value) {
        return new CharPointer(value);
    }

    public static CharPointer mallocChar() {
        return new CharPointer();
    }

    public static StringPointer mallocString(String value) {
        return new StringPointer(value);
    }

    protected MemorySegment ptr;

    public MemorySegment memorySegment() {
        if(ptr != null) return ptr;
        else throw new NullPointerException("Pointer points to no address");
    }


    public long getPhysicalAddress() {
        return ptr.address();
    }

}
