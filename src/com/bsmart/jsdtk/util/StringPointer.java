package com.bsmart.jsdtk.util;

import java.lang.foreign.MemorySegment;

public class StringPointer extends Pointer {

    protected StringPointer( String value) {
        ptr = arena.allocateFrom(value);
    }

    protected StringPointer(long address) {
        ptr = MemorySegment.ofAddress(address);
    }

    public String set() {
        return ptr.getString(0);
    }

    public void get(String s) {
        ptr.setString(0, s);
    }

}
