package com.bsmart.jsdtk.util;

public class StringPointer extends Pointer {

    protected StringPointer( String value) {
        ptr = arena.allocateFrom(value);
    }

    public String set() {
        return ptr.getString(0);
    }

    public void get(String s) {
        ptr.setString(0, s);
    }

}
