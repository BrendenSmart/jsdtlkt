package com.bsmart.jsdtk.utils;


public class StringPointer extends Pointer {

    protected StringPointer(String value) {
        ptr = arena.allocateFrom(value);
    }

    public String getString() {
        return ptr.getString(0);
    }

    public void setString(String s) {
        ptr.setString(0, s);
    }

}
