package com.bsmart.jsdtk.utils;

import java.lang.foreign.ValueLayout.OfInt;

public class IntPointer extends Pointer {

    protected IntPointer(int value) {
        ptr = arena.allocateFrom(OfInt.JAVA_INT, 0, value);
    }

    public void setValue(int value) {
        ptr.set(OfInt.JAVA_INT, 0, value);
    }

    public int getValue() {
        return ptr.get(OfInt.JAVA_INT, 0);
    }

}
