package com.bsmart.jsdtk.binds.glfw;

import java.lang.foreign.MemorySegment;

public interface GLFWdropfun {

    void callback(long window, int path_count, MemorySegment[] paths);

}
