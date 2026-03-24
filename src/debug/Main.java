package debug;

import com.bsmart.jsdtk.graphics.Window;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;

import static com.bsmart.jsdtk.binds.glfw.GLFW.*;

public class Main {

    static void main() {
        Arena global = Arena.global();
        System.out.println(glfwInit() == GLFW_TRUE);


        long window = glfwCreateWindow(500, 500, global.allocateFrom("title"), 0, 0);
        glfwDefaultWindowHints();
        glfwSetWindowShouldClose(window, GLFW_TRUE);

        while (true) {
            glfwPollEvents();
        }


    }

}