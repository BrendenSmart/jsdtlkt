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

    public static StringPointer ofAddressString(long address) {
        return new StringPointer(address);
    }

    public static IntPointer ofAddressInt(long address) {
        return new IntPointer(address, true);
    }

    public static ShortPointer ofAddressShort(long address) {
        return new ShortPointer(address, true);
    }

    public static CharPointer ofAddressChar(long address) {
        return new CharPointer(address, true);
    }

    public static BytePointer ofAddressByte(long address) {
        return new BytePointer(address, true);
    }

    public static FloatPointer ofAddressFloat(long address) {
        return new FloatPointer(address, true);
    }

    public static LongPointer ofAddressLong(long address) {
        return new LongPointer(address, true);
    }

    public static DoublePointer mallocDouble() {
        return new DoublePointer();
    }

    public static DoublePointer mallocDouble(double value) {
        return new DoublePointer(value);
    }

    public static DoublePointer ofAddressDouble(long address) {
        return new DoublePointer(address);
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
