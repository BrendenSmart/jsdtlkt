package debug;

import com.bsmart.jsdtk.binds.glfw.GLFW;
import com.bsmart.jsdtk.util.Pointer;
import com.bsmart.jsdtk.util.StringPointer;
import com.bsmart.jsdtk.util.IntPointer;

import static com.bsmart.jsdtk.util.Pointer.*;
import static com.bsmart.jsdtk.binds.glfw.GLFW.*;

public class Main {

    static void main() {
        IntPointer major = mallocInt();
        IntPointer minor = mallocInt();
        IntPointer rev = mallocInt();

        glfwInit();
        glfwGetVersion(major, minor, rev);

        System.out.println(major.get());
        System.out.println(minor.get());


        long window = glfwCreateWindow(500, 500, Pointer.mallocString("GLFW Window!"), 0, 0);
        
        while(glfwWindowShouldClose(window) == GLFW_FALSE) {
            glfwWaitEvents();
            glfwSwapBuffers(window);
        }

    }

}