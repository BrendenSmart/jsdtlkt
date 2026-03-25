package com.bsmart.jsdtk.util;

import java.lang.foreign.ValueLayout;
import java.lang.foreign.ValueLayout.OfFloat;

public class FloatPointer extends Pointer {

    protected FloatPointer(float value) {
        ptr = arena.allocateFrom(OfFloat.JAVA_FLOAT, value);
    }

    protected FloatPointer() {
        ptr = arena.allocate(ValueLayout.JAVA_FLOAT);
    }

    public void set(float value) {
        ptr.set(OfFloat.JAVA_FLOAT, 0, value);
    }

    public float get() {
        return ptr.get(OfFloat.JAVA_FLOAT, 0);
    }

}
