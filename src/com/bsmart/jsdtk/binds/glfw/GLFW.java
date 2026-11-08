package com.bsmart.jsdtk.binds.glfw;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;

import com.bsmart.jsdtk.util.StringPointer;
import com.bsmart.jsdtk.util.DoublePointer;
import com.bsmart.jsdtk.util.IntPointer;
import com.bsmart.jsdtk.util.FloatPointer;
import com.bsmart.jsdtk.util.Pointer;
import com.bsmart.jsdtk.util.LongPointer;

import static java.lang.foreign.ValueLayout.*;
import static java.lang.foreign.FunctionDescriptor.*;

public final class GLFW {

    private GLFW() {}

    private static Linker linker;

    private static Arena arena;

    private static Arena global;

    private static SymbolLookup glfwlib;


    static {
        loadLibrary();
        initIDs();
    }

    @SuppressWarnings("SpellCheckingInspection")
    private static void loadLibrary() {
        linker = Linker.nativeLinker();
        arena = Arena.ofConfined();
        global = Arena.global();

        URL url = GLFW.class.getResource("/glfw/glfw3.dll");
        try {
            File tempDir = Files.createTempDirectory("tempglfw").toFile();
            tempDir.deleteOnExit();
            File nativeLibTmpFile = new File(tempDir, "glfw3.dll");
            nativeLibTmpFile.deleteOnExit();
            try (InputStream in = url.openStream()) {
                Files.copy(in, nativeLibTmpFile.toPath());
            }
             glfwlib = SymbolLookup.libraryLookup(nativeLibTmpFile.toPath(), arena);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @SuppressWarnings("all")
    public static final int GLFW_NO_API = 0, GLFWOPENGL_API = 0x00030001, GLFW_OPENGL_ES_API = 0x00030002, GLFW_NO_ROBUSTNESS = 0, GLFW_NO_RESET_NOTIFICATION = 0x00031001,
    GLFW_LOSE_CONTEXT_ON_RESET = 0x00031002, GLFW_OPENGL_ANY_PROFILE = 0, GLFW_OPENGL_CORE_PROFILE = 0x00032001, GLFW_OPENGL_COMPAT_PROFILE = 0x00032002,
    GLFW_CURSOR = 0x00033001, GLFW_STICKY_KEYS = 0x00033002, GLFW_STICKY_MOUSE_BUTTONS = 0x00033003, GLFW_LOCK_KEY_MODS = 0x00033004, GLFW_RAW_MOUSE_MOTION = 0x00033005,
    GLFW_CURSOR_NORMAL = 0x00034001, GLFW_CURSOR_HIDDEN = 0x00034002, GLFW_CURSOR_DISABLED = 0x00034003, GLFW_CURSOR_CAPTURED = 0x00034004, GLFW_ANY_RELEASE_BEHAVIOR = 0,
    GLFW_RELEASE_BEHAVIOR_FLUSH = 0x00035001, GLFW_RELEASE_BEHAVIOR_NONE = 0x00035002, GLFW_NATIVE_CONTEXT_API = 0x00036001, GLFW_EGL_CONTEXT_API = 0x00036002,
    GLFW_OSMESA_CONTEXT_API = 0x00036003, GLFW_ANGLE_PLATFORM_TYPE_NONE = 0x00037001, GLFW_ANGLE_PLATFORM_TYPE_OPENGL = 0x00037002, GLFW_ANGLE_PLATFORM_TYPE_OPENGLES = 0x00037003,
    GLFW_ANGLE_PLATFORM_TYPE_D3D9 = 0x00037004, GLFW_ANGLE_PLATFORM_TYPE_D3D11 = 0x00037005, GLFW_ANGLE_PLATFORM_TYPE_VULKAN = 0x00037007, GLFW_ANGLE_PLATFORM_TYPE_METAL = 0x00037008,
    GLFW_WAYLAND_PREFER_LIBDECOR = 0x00038001, GLFW_WAYLAND_DISABLE_LIBDECOR = 0x00038002, GLFW_ANY_POSITION = 0x80000000, GLFW_CONNECTED = 0x00040001, GLFW_DISCONNECTED = 0x00040002,
    GLFW_DONT_CARE = -1, GLFW_TRUE = 1, GLFW_FALSE = 0, GLFW_HAT_CENTERED = 0, GLFW_HAT_UP = 1, GLFW_HAT_RIGHT = 2, GLFW_HAT_DOWN = 4, GLFW_HAT_LEFT = 8, GLFW_HAT_RIGHT_UP =
    (GLFW_HAT_RIGHT | GLFW_HAT_UP), GLFW_HAT_RIGHT_DOWN = (GLFW_HAT_RIGHT | GLFW_HAT_DOWN), GLFW_HAT_LEFT_UP = (GLFW_HAT_LEFT | GLFW_HAT_UP), GLFW_HAT_LEFT_DOWN = (GLFW_HAT_LEFT | GLFW_HAT_DOWN),
    GLFW_KEY_UNKNOWN = -1, GLFW_KEY_SPACE = 32, GLFW_KEY_APOSTROPHE = 39, GLFW_KEY_COMMA = 44, GLFW_KEY_MINUS = 45, GLFW_KEY_PERIOD = 46, GLFW_KEY_SLASH = 47,
    GLFW_KEY_0 = 48, GLFW_KEY_1 = 49, GLFW_KEY_2 = 50, GLFW_KEY_3 = 51, GLFW_KEY_4 = 52, GLFW_KEY_5 = 53, GLFW_KEY_6 = 54, GLFW_KEY_7 = 55, GLFW_KEY_8 = 56, GLFW_KEY_9 = 57,
    GLFW_KEY_SEMICOLON = 59, GLFW_KEY_EQUAL = 61, GLFW_KEY_A = 65, GLFW_KEY_B = 66, GLFW_KEY_C = 67, GLFW_KEY_D = 68, GLFW_KEY_E = 69, GLFW_KEY_F = 70, GLFW_KEY_G = 71,
    GLFW_KEY_H = 72, GLFW_KEY_I = 73, GLFW_KEY_J = 74, GLFW_KEY_K = 75, GLFW_KEY_L = 76, GLFW_KEY_M = 77, GLFW_KEY_N = 78, GLFW_KEY_O = 79, GLFW_KEY_P = 80,
    GLFW_KEY_Q = 81, GLFW_KEY_R = 82, GLFW_KEY_S = 83, GLFW_KEY_T = 84, GLFW_KEY_U = 85, GLFW_KEY_V = 86, GLFW_KEY_W = 87, GLFW_KEY_X = 88, GLFW_KEY_Y = 89,
    GLFW_KEY_Z = 90, GLFW_KEY_LEFT_BRACKET = 91, GLFW_KEY_BACKSLASH = 92, GLFW_KEY_RIGHT_BRACKET = 93, GLFW_KEY_GRAVE_ACCENT = 96, GLFW_KEY_WORLD_1 = 161,
    GLFW_KEY_WORLD_2 = 162, GLFW_KEY_ESCAPE = 256, GLFW_KEY_ENTER = 257, GLFW_KEY_TAB = 258, GLFW_KEY_BACKSPACE = 259, GLFW_KEY_INSERT = 260, GLFW_KEY_DELETE = 261,
    GLFW_KEY_RIGHT = 262, GLFW_KEY_LEFT = 263, GLFW_KEY_DOWN = 264, GLFW_KEY_UP = 265, GLFW_KEY_PAGE_UP = 266, GLFW_KEY_PAGE_DOWN = 267, GLFW_KEY_HOME = 268,
    GLFW_KEY_END = 269, GLFW_KEY_CAPS_LOCK = 280, GLFW_KEY_SCROLL_LOCK = 281, GLFW_KEY_NUM_LOCK = 282, GLFW_KEY_PRINT_SCREEN = 283, GLFW_KEY_PAUSE = 284, GLFW_KEY_F1 = 290,
    GLFW_KEY_F2 = 291, GLFW_KEY_F3 = 292, GLFW_KEY_F4 = 293, GLFW_KEY_F5 = 294, GLFW_KEY_F6 = 295, GLFW_KEY_F7 = 296, GLFW_KEY_F8 = 297, GLFW_KEY_F9 = 298, GLFW_KEY_F10 = 299,
    GLFW_KEY_F11 = 300, GLFW_KEY_F12 = 301, GLFW_KEY_F13 = 302, GLFW_KEY_F14 = 303, GLFW_KEY_F15 = 304, GLFW_KEY_F16 = 305, GLFW_KEY_F17 = 306, GLFW_KEY_F18 = 307,
    GLFW_KEY_F19 = 308, GLFW_KEY_F20 = 309, GLFW_KEY_F21 = 310, GLFW_KEY_F22 = 311, GLFW_KEY_F23 = 312, GLFW_KEY_F24 = 313, GLFW_KEY_F25 = 314, GLFW_KEY_KP_0 = 320,
    GLFW_KEY_KP_1 = 321, GLFW_KEY_KP_2 = 322, GLFW_KEY_KP_3 = 323, GLFW_KEY_KP_4 = 324, GLFW_KEY_KP_5 = 325, GLFW_KEY_KP_6 = 326, GLFW_KEY_KP_7 = 327, GLFW_KEY_KP_8 = 328,
    GLFW_KEY_KP_9 = 329, GLFW_KEY_KP_DECIMAL = 330, GLFW_KEY_KP_DIVIDE = 331, GLFW_KEY_KP_MULTIPLY = 332, GLFW_KEY_KP_SUBTRACT = 333, GLFW_KEY_KP_ADD = 334, GLFW_KEY_KP_ENTER = 335,
    GLFW_KEY_KP_EQUAL = 336, GLFW_KEY_LEFT_SHIFT = 340, GLFW_KEY_LEFT_CONTROL = 341, GLFW_KEY_LEFT_ALT = 342, GLFW_KEY_LEFT_SUPER = 343, GLFW_KEY_RIGHT_SHIFT = 344,
    GLFW_KEY_RIGHT_CONTROL = 345, GLFW_KEY_RIGHT_ALT = 346, GLFW_KEY_RIGHT_SUPER = 347, GLFW_KEY_MENU = 348, GLFW_KEY_LAST = GLFW_KEY_MENU, GLFW_MOD_SHIFT = 0x0001,
    GLFW_MOD_CONTROL = 0x0002, GLFW_MOD_ALT = 0x0004, GLFW_MOD_SUPER = 0x0008, GLFW_MOD_CAPS_LOCK = 0x0010, GLFW_MOD_NUM_LOCK = 0x0020, GLFW_MOUSE_BUTTON_1 = 0, GLFW_MOUSE_BUTTON_2 = 1,
    GLFW_MOUSE_BUTTON_3 = 2, GLFW_MOUSE_BUTTON_4 = 3, GLFW_MOUSE_BUTTON_5 = 4, GLFW_MOUSE_BUTTON_6 = 5, GLFW_MOUSE_BUTTON_7 = 6, GLFW_MOUSE_BUTTON_8 = 7, GLFW_MOUSE_BUTTON_LAST = GLFW_MOUSE_BUTTON_8,
    GLFW_MOUSE_BUTTON_LEFT = GLFW_MOUSE_BUTTON_1, GLFW_MOUSE_BUTTON_RIGHT = GLFW_MOUSE_BUTTON_2, GLFW_MOUSE_BUTTON_MIDDLE = GLFW_MOUSE_BUTTON_3, GLFW_JOYSTICK_1 = 0,
    GLFW_JOYSTICK_2 = 1, GLFW_JOYSTICK_3 = 2, GLFW_JOYSTICK_4 = 3, GLFW_JOYSTICK_5 = 4, GLFW_JOYSTICK_6 = 5, GLFW_JOYSTICK_7 = 6, GLFW_JOYSTICK_8 = 7, GLFW_JOYSTICK_9 = 8,
    GLFW_JOYSTICK_10 = 9, GLFW_JOYSTICK_11 = 10, GLFW_JOYSTICK_12 = 11, GLFW_JOYSTICK_13 = 12, GLFW_JOYSTICK_14 = 13, GLFW_JOYSTICK_15 = 14, GLFW_JOYSTICK_16 = 15,
    GLFW_JOYSTICK_LAST = GLFW_JOYSTICK_16, GLFW_GAMEPAD_BUTTON_A = 0, GLFW_GAMEPAD_BUTTON_B = 1, GLFW_GAMEPAD_BUTTON_X = 2, GLFW_GAMEPAD_BUTTON_Y = 3, GLFW_GAMEPAD_BUTTON_LEFT_BUMPER = 4,
    GLFW_GAMEPAD_BUTTON_RIGHT_BUMPER = 5, GLFW_GAMEPAD_BUTTON_BACK = 6, GLFW_GAMEPAD_BUTTON_START = 7, GLFW_GAMEPAD_BUTTON_GUIDE = 8, GLFW_GAMEPAD_BUTTON_LEFT_THUMB = 9,
    GLFW_GAMEPAD_BUTTON_RIGHT_THUMB = 10, GLFW_GAMEPAD_BUTTON_DPAD_UP = 11, GLFW_GAMEPAD_BUTTON_DPAD_RIGHT = 12, GLFW_GAMEPAD_BUTTON_DPAD_DOWN = 13, GLFW_GAMEPAD_BUTTON_DPAD_LEFT = 14,
    GLFW_GAMEPAD_BUTTON_LAST = GLFW_GAMEPAD_BUTTON_DPAD_LEFT, GLFW_GAMEPAD_BUTTON_CROSS = GLFW_GAMEPAD_BUTTON_A, GLFW_GAMEPAD_BUTTON_CIRCLE = GLFW_GAMEPAD_BUTTON_B,
    GLFW_GAMEPAD_BUTTON_SQUARE = GLFW_GAMEPAD_BUTTON_X, GLFW_GAMEPAD_BUTTON_TRIANGLE = GLFW_GAMEPAD_BUTTON_Y, GLFW_GAMEPAD_AXIS_LEFT_X = 0, GLFW_GAMEPAD_AXIS_LEFT_Y = 1,
    GLFW_GAMEPAD_AXIS_RIGHT_X = 2, GLFW_GAMEPAD_AXIS_RIGHT_Y = 3, GLFW_GAMEPAD_AXIS_LEFT_TRIGGER = 4, GLFW_GAMEPAD_AXIS_RIGHT_TRIGGER = 5, GLFW_GAMEPAD_AXIS_LAST = GLFW_GAMEPAD_AXIS_RIGHT_TRIGGER,
    GLFW_NO_ERROR = 0, GLFW_NOT_INITIALIZED = 0x00010001, GLFW_NO_CURRENT_CONTEXT = 0x00010002, GLFW_INVALID_ENUM = 0x00010003, GLFW_INVALID_VALUE = 0x00010004,
    GLFW_OUT_OF_MEMORY = 0x00010005, GLFW_API_UNAVAILABLE = 0x00010006, GLFW_VERSION_UNAVAILABLE = 0x00010007, GLFW_PLATFORM_ERROR = 0x00010008, GLFW_FORMAT_UNAVAILABLE = 0x00010009,
    GLFW_NO_WINDOW_CONTEXT = 0x0001000A, GLFW_CURSOR_UNAVAILABLE = 0x0001000B, GLFW_FEATURE_UNAVAILABLE = 0x0001000C, GLFW_FEATURE_UNIMPLEMENTED = 0x0001000D,
    GLFW_PLATFORM_UNAVAILABLE = 0x0001000E, GLFW_FOCUSED = 0x00020001, GLFW_ICONIFIED = 0x00020002, GLFW_RESIZABLE = 0x00020003, GLFW_VISIBLE = 0x00020004,
    GLFW_DECORATED = 0x00020005, GLFW_AUTO_ICONIFY = 0x00020006, GLFW_FLOATING = 0x00020007, GLFW_MAXIMIZED = 0x00020008, GLFW_CENTER_CURSOR = 0x00020009,
    GLFW_TRANSPARENT_FRAMEBUFFER = 0x0002000A, GLFW_HOVERED = 0x0002000B, GLFW_FOCUS_ON_SHOW = 0x0002000C, GLFW_MOUSE_PASSTHROUGH = 0x0002000D, GLFW_POSITION_X = 0x0002000E,
    GLFW_POSITION_Y = 0x0002000F, GLFW_RED_BITS = 0x00021001, GLFW_GREEN_BITS = 0x00021002, GLFW_BLUE_BITS = 0x00021003, GLFW_ALPHA_BITS = 0x00021004,
    GLFW_DEPTH_BITS = 0x00021005, GLFW_STENCIL_BITS = 0x00021006, GLFW_ACCUM_RED_BITS = 0x00021007, GLFW_ACCUM_GREEN_BITS = 0x00021008, GLFW_ACCUM_BLUE_BITS = 0x00021009,
    GLFW_ACCUM_ALPHA_BITS = 0x0002100A, GLFW_AUX_BUFFERS = 0x0002100B, GLFW_STEREO = 0x0002100C, GLFW_SAMPLES = 0x0002100D, GLFW_SRGB_CAPABLE = 0x0002100E,
    GLFW_REFRESH_RATE = 0x0002100F, GLFW_DOUBLEBUFFER = 0x00021010, GLFW_CLIENT_API = 0x00022001, GLFW_CONTEXT_VERSION_MAJOR = 0x00022002, GLFW_CONTEXT_VERSION_MINOR = 0x00022003,
    GLFW_CONTEXT_REVISION = 0x00022004, GLFW_CONTEXT_ROBUSTNESS = 0x00022005, GLFW_OPENGL_FORWARD_COMPAT = 0x00022006, GLFW_CONTEXT_DEBUG = 0x00022007,
    GLFW_OPENGL_DEBUG_CONTEXT = GLFW_CONTEXT_DEBUG, GLFW_OPENGL_PROFILE = 0x00022008, GLFW_CONTEXT_RELEASE_BEHAVIOR = 0x00022009, GLFW_CONTEXT_NO_ERROR = 0x0002200A,
    GLFW_CONTEXT_CREATION_API = 0x0002200B, GLFW_SCALE_TO_MONITOR = 0x0002200C, GLFW_SCALE_FRAMEBUFFER = 0x0002200D, GLFW_COCOA_RETINA_FRAMEBUFFER = 0x00023001,
    GLFW_COCOA_FRAME_NAME = 0x00023002, GLFW_COCOA_GRAPHICS_SWITCHING = 0x00023003, GLFW_X11_CLASS_NAME = 0x00024001, GLFW_X11_INSTANCE_NAME = 0x00024002,
    GLFW_WIN32_KEYBOARD_MENU = 0x00025001, GLFW_WIN32_SHOWDEFAULT = 0x00025002, GLFW_WAYLAND_APP_ID = 0x00026001, GLFW_ARROW_CURSOR = 0x00036001, GLFW_IBEAM_CURSOR = 0x00036002,
    GLFW_CROSSHAIR_CURSOR = 0x00036003, GLFW_POINTING_HAND_CURSOR = 0x00036004, GLFW_RESIZE_EW_CURSOR = 0x00036005, GLFW_RESIZE_NS_CURSOR = 0x00036006, GLFW_RESIZE_NWSE_CURSOR = 0x00036007,
    GLFW_RESIZE_NESW_CURSOR = 0x00036008, GLFW_RESIZE_ALL_CURSOR = 0x00036009, GLFW_NOT_ALLOWED_CURSOR = 0x0003600A, GLFW_HRESIZE_CURSOR = GLFW_RESIZE_EW_CURSOR,
    GLFW_VRESIZE_CURSOR = GLFW_RESIZE_NS_CURSOR, GLFW_HAND_CURSOR = GLFW_POINTING_HAND_CURSOR, GLFW_JOYSTICK_HAT_BUTTONS = 0x00050001, GLFW_ANGLE_PLATFORM_TYPE = 0x00050002,
    GLFW_PLATFORM = 0x00050003, GLFW_COCOA_CHDIR_RESOURCES = 0x00051001, GLFW_COCOA_MENUBAR = 0x00051002, GLFW_X11_XCB_VULKAN_SURFACE = 0x00052001, GLFW_WAYLAND_LIBDECOR = 0x00053001,
    GLFW_ANY_PLATFORM = 0x00060000, GLFW_PLATFORM_WIN32 = 0x00060001, GLFW_PLATFORM_COCOA = 0x00060002, GLFW_PLATFORM_WAYLAND = 0x00060003, GLFW_PLATFORM_X11 = 0x00060004,
    GLFW_PLATFORM_NULL = 0x00060005, GLFW_RELEASE = 0, GLFW_PRESS = 1, GLFW_REPEAT = 2, GLFW_VERSION_MAJOR = 3, GLFW_VERSION_MINOR = 4, GLFW_VERSION_REVISION = 0;

    private static MethodHandle glfwInit, glfwTerminate, glfwInitHint, glfwInitAllocator, glfwInitVulkanLoader, glfwGetVersion, glfwGetVersionString,
    glfwGetError, glfwSetErrorCallback, glfwGetPlatform, glfwPlatformSupported, glfwGetMonitors, glfwGetPrimaryMonitor, glfwGetMonitorPos, glfwGetMonitorWorkArea,
    glfwGetMonitorPhysicalSize, glfwGetMonitorContentScale, glfwGetMonitorName, glfwSetMonitorUserPointer, glfwGetMonitorUserPointer, glfwSetMonitorCallback,
    glfwGetVideoModes, glfwGetVideoMode, glfwSetGamma, glfwGetGammaRamp, glfwSetGammaRamp, glfwDefaultWindowHints, glfwWindowHint, glfwWindowHintString,
    glfwCreateWindow, glfwDestroyWindow, glfwWindowShouldClose, glfwSetWindowShouldClose, glfwGetWindowTitle, glfwSetWindowTitle, glfwSetWindowIcon,
    glfwGetWindowPos, glfwSetWindowPos, glfwGetWindowSize, glfwSetWindowSizeLimits, glfwSetWindowAspectRatio, glfwSetWindowSize, glfwGetFramebufferSize,
    glfwGetWindowFrameSize, glfwGetWindowContentScale, glfwGetWindowOpacity, glfwSetWindowOpacity, glfwIconifyWindow, glfwRestoreWindow, glfwMaximizeWindow,
    glfwShowWindow, glfwHideWindow, glfwFocusWindow, glfwRequestWindowAttention, glfwGetWindowMonitor, glfwSetWindowMonitor, glfwGetWindowAttrib, glfwSetWindowAttrib,
    glfwSetWindowUserPointer, glfwGetWindowUserPointer, glfwSetWindowPosCallback, glfwSetWindowSizeCallback, glfwSetWindowCloseCallback, glfwSetWindowRefreshCallback,
    glfwSetWindowFocusCallback, glfwSetWindowIconifyCallback, glfwSetWindowMaximizeCallback, glfwSetFramebufferSizeCallback, glfwSetWindowContentScaleCallback,
    glfwPollEvents, glfwWaitEvents, glfwWaitEventsTimeout, glfwPostEmptyEvent, glfwGetInputMode, glfwSetInputMode, glfwRawMouseMotionSupported, glfwGetKeyName,
    glfwGetKeyScancode, glfwGetKey, glfwGetMouseButton, glfwGetCursorPos, glfwSetCursorPos, glfwCreateCursor, glfwCreateStandardCursor, glfwDestroyCursor,
    glfwSetCursor, glfwSetKeyCallback, glfwSetCharCallback, glfwSetCharModsCallback, glfwSetMouseButtonCallback, glfwSetCursorPosCallback, glfwSetCursorEnterCallback,
    glfwSetScrollCallback, glfwSetDropCallback, glfwJoystickPresent, glfwGetJoystickAxes, glfwGetJoyStickButtons, glfwGetJoystickHats, glfwGetJoystickName,
    glfwGetJoystickGUID, glfwSetJoystickUserPointer, glfwGetJoystickUserPointer, glfwJoystickIsGamepad, glfwSetJoystickCallback, glfwUpdateGamepadMappings,
    glfwGetGamepadName, glfwGetGamepadState, glfwSetClipboardString, glfwGetClipboardString, glfwGetTime, glfwSetTime, glfwGetTimerValue, glfwGetTimerFrequency,
    glfwMakeContextCurrent, glfwGetCurrentContext, glfwSwapBuffers, glfwSwapInterval, glfwExtensionSupported, glfwGetProcAddress, glfwVulkanSupported,
    glfwGetRequiredInstanceExtensions, glfwGetInstanceProcAddress, glfwGetPhysicalDevicePresentationSupport, glfwCreateWindowSurface;

    @SuppressWarnings("SpellCheckingInspection")
    private static void initIDs() {
        glfwInit = linker.downcallHandle(glfwlib.findOrThrow("glfwInit"), of(JAVA_INT));
        glfwTerminate = linker.downcallHandle(glfwlib.findOrThrow("glfwTerminate"), ofVoid());
        glfwInitHint = linker.downcallHandle(glfwlib.findOrThrow("glfwInitHint"), ofVoid(JAVA_INT, JAVA_INT));
        glfwInitAllocator = linker.downcallHandle(glfwlib.findOrThrow("glfwInitAllocator"), ofVoid(JAVA_LONG));
        glfwInitVulkanLoader = linker.downcallHandle(glfwlib.findOrThrow("glfwInitVulkanLoader"), ofVoid(JAVA_LONG));
        glfwGetVersion = linker.downcallHandle(glfwlib.findOrThrow("glfwGetVersion"), ofVoid(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwGetVersionString = linker.downcallHandle(glfwlib.findOrThrow("glfwGetVersionString"), of(JAVA_LONG));
        glfwGetError = linker.downcallHandle(glfwlib.findOrThrow("glfwGetError"), of(JAVA_INT, JAVA_LONG));
        glfwSetErrorCallback = linker.downcallHandle(glfwlib.findOrThrow("glfwSetErrorCallback"), of(JAVA_LONG, JAVA_LONG));
        glfwGetPlatform = linker.downcallHandle(glfwlib.findOrThrow("glfwGetPlatform"), of(JAVA_INT));
        glfwPlatformSupported = linker.downcallHandle(glfwlib.findOrThrow("glfwPlatformSupported"), of(JAVA_INT, JAVA_INT));
        glfwGetMonitors = linker.downcallHandle(glfwlib.findOrThrow("glfwGetMonitors"), of(JAVA_LONG, JAVA_LONG));
        glfwGetPrimaryMonitor = linker.downcallHandle(glfwlib.findOrThrow("glfwGetPrimaryMonitor"), of(JAVA_LONG));
        glfwGetMonitorPos = linker.downcallHandle(glfwlib.findOrThrow("glfwGetMonitorPos"), ofVoid(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwGetMonitorWorkArea = linker.downcallHandle(glfwlib.findOrThrow("glfwGetMonitorWorkarea"), ofVoid(JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwGetMonitorPhysicalSize = linker.downcallHandle(glfwlib.findOrThrow("glfwGetMonitorPhysicalSize"), ofVoid(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwGetMonitorContentScale = linker.downcallHandle(glfwlib.findOrThrow("glfwGetMonitorContentScale"), ofVoid(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwGetMonitorName = linker.downcallHandle(glfwlib.findOrThrow("glfwGetMonitorName"), of(JAVA_LONG, JAVA_LONG));
        glfwSetMonitorUserPointer = linker.downcallHandle(glfwlib.findOrThrow("glfwSetMonitorUserPointer"), FunctionDescriptor.ofVoid(JAVA_LONG, JAVA_LONG));
        glfwGetMonitorUserPointer = linker.downcallHandle(glfwlib.findOrThrow("glfwGetMonitorUserPointer"), FunctionDescriptor.of(JAVA_LONG, JAVA_LONG));
        glfwSetMonitorCallback = linker.downcallHandle(glfwlib.findOrThrow("glfwSetMonitorCallback"), FunctionDescriptor.of(JAVA_LONG, JAVA_LONG));
        glfwGetVideoModes = linker.downcallHandle(glfwlib.findOrThrow("glfwGetVideoModes"), FunctionDescriptor.of(JAVA_LONG, JAVA_LONG));
        glfwGetVideoMode = linker.downcallHandle(glfwlib.findOrThrow("glfwGetVideoMode"), FunctionDescriptor.of(JAVA_LONG, JAVA_LONG));
        glfwSetGamma = linker.downcallHandle(glfwlib.findOrThrow("glfwSetGamma"), FunctionDescriptor.ofVoid(JAVA_LONG, JAVA_FLOAT));
        glfwGetGammaRamp = linker.downcallHandle(glfwlib.findOrThrow("glfwGetGammaRamp"), FunctionDescriptor.of(JAVA_LONG, JAVA_LONG));
        glfwSetGammaRamp = linker.downcallHandle(glfwlib.findOrThrow("glfwSetGammaRamp"), FunctionDescriptor.ofVoid(JAVA_LONG, JAVA_LONG));
        glfwDefaultWindowHints = linker.downcallHandle(glfwlib.findOrThrow("glfwDefaultWindowHints"), FunctionDescriptor.ofVoid());
        glfwWindowHint = linker.downcallHandle(glfwlib.findOrThrow("glfwWindowHint"), FunctionDescriptor.ofVoid(JAVA_INT, JAVA_INT));
        glfwWindowHintString = linker.downcallHandle(glfwlib.findOrThrow("glfwWindowHintString"), FunctionDescriptor.ofVoid(JAVA_INT, JAVA_LONG));
        glfwCreateWindow = linker.downcallHandle(glfwlib.findOrThrow("glfwCreateWindow"), FunctionDescriptor.of(JAVA_LONG, JAVA_INT, JAVA_INT, JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwDestroyWindow = linker.downcallHandle(glfwlib.findOrThrow("glfwDestroyWindow"), FunctionDescriptor.ofVoid(JAVA_LONG));
        glfwWindowShouldClose = linker.downcallHandle(glfwlib.findOrThrow("glfwWindowShouldClose"), FunctionDescriptor.of(JAVA_INT, JAVA_LONG));
        glfwSetWindowShouldClose = linker.downcallHandle(glfwlib.findOrThrow("glfwSetWindowShouldClose"), FunctionDescriptor.ofVoid(JAVA_LONG, JAVA_INT));
        glfwGetWindowTitle = linker.downcallHandle(glfwlib.findOrThrow("glfwGetWindowTitle"), FunctionDescriptor.of(JAVA_LONG, JAVA_LONG));
        glfwSetWindowTitle = linker.downcallHandle(glfwlib.findOrThrow("glfwSetWindowTitle"), FunctionDescriptor.ofVoid(JAVA_LONG, JAVA_LONG));
        glfwSetWindowIcon = linker.downcallHandle(glfwlib.findOrThrow("glfwSetWindowIcon"), FunctionDescriptor.ofVoid(JAVA_LONG, JAVA_INT, JAVA_LONG));
        glfwGetWindowPos = linker.downcallHandle(glfwlib.findOrThrow("glfwGetWindowPos"), FunctionDescriptor.of(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwSetWindowPos = linker.downcallHandle(glfwlib.findOrThrow("glfwSetWindowPos"), FunctionDescriptor.ofVoid(JAVA_LONG, JAVA_INT, JAVA_INT));
        glfwGetWindowSize = linker.downcallHandle(glfwlib.findOrThrow("glfwGetWindowSize"), FunctionDescriptor.of(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwSetWindowSizeLimits = linker.downcallHandle(glfwlib.findOrThrow("glfwSetWindowSizeLimits"), FunctionDescriptor.ofVoid(JAVA_LONG, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT));
        glfwSetWindowAspectRatio = linker.downcallHandle(glfwlib.findOrThrow("glfwSetWindowAspectRatio"), FunctionDescriptor.ofVoid(JAVA_LONG, JAVA_INT, JAVA_INT));
        glfwSetWindowSize = linker.downcallHandle(glfwlib.findOrThrow("glfwSetWindowSize"), FunctionDescriptor.ofVoid(JAVA_LONG, JAVA_INT, JAVA_INT));
        glfwGetFramebufferSize = linker.downcallHandle(glfwlib.findOrThrow("glfwGetFramebufferSize"), FunctionDescriptor.ofVoid(JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwGetWindowFrameSize = linker.downcallHandle(glfwlib.findOrThrow("glfwGetWindowFrameSize"), FunctionDescriptor.ofVoid(JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwGetWindowContentScale = linker.downcallHandle(glfwlib.findOrThrow("glfwGetWindowContentScale"), FunctionDescriptor.ofVoid(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwGetWindowOpacity = linker.downcallHandle(glfwlib.findOrThrow("glfwGetWindowOpacity"), FunctionDescriptor.of(JAVA_FLOAT, JAVA_LONG));
        glfwSetWindowOpacity = linker.downcallHandle(glfwlib.findOrThrow("glfwSetWindowOpacity"), FunctionDescriptor.ofVoid(JAVA_LONG, JAVA_FLOAT));
        glfwIconifyWindow = linker.downcallHandle(glfwlib.findOrThrow("glfwIconifyWindow"), FunctionDescriptor.ofVoid(JAVA_LONG));
        glfwRestoreWindow = linker.downcallHandle(glfwlib.findOrThrow("glfwRestoreWindow"), FunctionDescriptor.ofVoid(JAVA_LONG));
        glfwMaximizeWindow = linker.downcallHandle(glfwlib.findOrThrow("glfwMaximizeWindow"), FunctionDescriptor.ofVoid(JAVA_LONG));
        glfwShowWindow = linker.downcallHandle(glfwlib.findOrThrow("glfwShowWindow"), FunctionDescriptor.ofVoid(JAVA_LONG));
        glfwHideWindow = linker.downcallHandle(glfwlib.findOrThrow("glfwHideWindow"), FunctionDescriptor.ofVoid(JAVA_LONG));
        glfwFocusWindow = linker.downcallHandle(glfwlib.findOrThrow("glfwFocusWindow"), FunctionDescriptor.ofVoid(JAVA_LONG));
        glfwRequestWindowAttention = linker.downcallHandle(glfwlib.findOrThrow("glfwRequestWindowAttention"), FunctionDescriptor.ofVoid(JAVA_LONG));
        glfwGetWindowMonitor = linker.downcallHandle(glfwlib.findOrThrow("glfwGetWindowMonitor"), FunctionDescriptor.of(JAVA_LONG, JAVA_LONG));
        glfwSetWindowMonitor = linker.downcallHandle(glfwlib.findOrThrow("glfwSetWindowMonitor"), FunctionDescriptor.ofVoid(JAVA_LONG, JAVA_LONG, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT));
        glfwGetWindowAttrib = linker.downcallHandle(glfwlib.findOrThrow("glfwGetWindowAttrib"), of(JAVA_INT, JAVA_LONG, JAVA_INT));
        glfwSetWindowAttrib = linker.downcallHandle(glfwlib.findOrThrow("glfwSetWindowAttrib"), ofVoid(JAVA_LONG, JAVA_INT, JAVA_INT));
        glfwSetWindowUserPointer = linker.downcallHandle(glfwlib.findOrThrow("glfwSetWindowUserPointer"), ofVoid(JAVA_LONG));
        glfwGetWindowUserPointer = linker.downcallHandle(glfwlib.findOrThrow("glfwGetWindowUserPointer"), of(JAVA_LONG, JAVA_LONG));
        glfwSetWindowPosCallback = linker.downcallHandle(glfwlib.findOrThrow("glfwSetWindowPosCallback"), of(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwSetWindowSizeCallback = linker.downcallHandle(glfwlib.findOrThrow("glfwSetWindowSizeCallback"), of(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwSetWindowCloseCallback = linker.downcallHandle(glfwlib.findOrThrow("glfwSetWindowCloseCallback"), of(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwSetWindowRefreshCallback = linker.downcallHandle(glfwlib.findOrThrow("glfwSetWindowRefreshCallback"), of(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwSetWindowFocusCallback = linker.downcallHandle(glfwlib.findOrThrow("glfwSetWindowFocusCallback"), of(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwSetWindowIconifyCallback = linker.downcallHandle(glfwlib.findOrThrow("glfwSetWindowIconifyCallback"), of(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwSetWindowMaximizeCallback = linker.downcallHandle(glfwlib.findOrThrow("glfwSetWindowMaximizeCallback"), of(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwSetFramebufferSizeCallback = linker.downcallHandle(glfwlib.findOrThrow("glfwSetFramebufferSizeCallback"), of(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwSetWindowContentScaleCallback = linker.downcallHandle(glfwlib.findOrThrow("glfwSetWindowContentScaleCallback"), of(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwPollEvents = linker.downcallHandle(glfwlib.findOrThrow("glfwPollEvents"), ofVoid());
        glfwWaitEvents = linker.downcallHandle(glfwlib.findOrThrow("glfwWaitEvents"), ofVoid());
        glfwWaitEventsTimeout = linker.downcallHandle(glfwlib.findOrThrow("glfwWaitEventsTimeout"), ofVoid(JAVA_DOUBLE));
        glfwPostEmptyEvent = linker.downcallHandle(glfwlib.findOrThrow("glfwPostEmptyEvent"), ofVoid());
        glfwGetInputMode = linker.downcallHandle(glfwlib.findOrThrow("glfwGetInputMode"), of(JAVA_INT, JAVA_LONG, JAVA_INT));
        glfwSetInputMode = linker.downcallHandle(glfwlib.findOrThrow("glfwSetInputMode"), ofVoid(JAVA_LONG, JAVA_INT, JAVA_INT));
        glfwRawMouseMotionSupported = linker.downcallHandle(glfwlib.findOrThrow("glfwRawMouseMotionSupported"), of(JAVA_INT));
        glfwGetKeyName = linker.downcallHandle(glfwlib.findOrThrow("glfwGetKeyName"), of(JAVA_LONG, JAVA_INT, JAVA_INT));
        glfwGetKeyScancode = linker.downcallHandle(glfwlib.findOrThrow("glfwGetKeyScancode"), of(JAVA_INT, JAVA_INT));
        glfwGetKey = linker.downcallHandle(glfwlib.findOrThrow("glfwGetKey"), of(JAVA_INT, JAVA_LONG, JAVA_INT));
        glfwGetMouseButton = linker.downcallHandle(glfwlib.findOrThrow("glfwGetMouseButton"), of(JAVA_INT, JAVA_LONG, JAVA_INT));
        glfwGetCursorPos = linker.downcallHandle(glfwlib.findOrThrow("glfwGetCursorPos"), ofVoid(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwSetCursorPos = linker.downcallHandle(glfwlib.findOrThrow("glfwSetCursorPos"), ofVoid(JAVA_LONG, JAVA_DOUBLE, JAVA_DOUBLE));
        glfwCreateCursor = linker.downcallHandle(glfwlib.findOrThrow("glfwCreateCursor"), of(JAVA_LONG, JAVA_LONG, JAVA_INT, JAVA_INT));
        glfwCreateStandardCursor = linker.downcallHandle(glfwlib.findOrThrow("glfwCreateStandardCursor"), of(JAVA_LONG, JAVA_INT));
        glfwDestroyCursor = linker.downcallHandle(glfwlib.findOrThrow("glfwDestroyCursor"), ofVoid(JAVA_LONG));
        glfwSetCursor = linker.downcallHandle(glfwlib.findOrThrow("glfwSetCursor"), ofVoid(JAVA_LONG, JAVA_LONG));
        glfwSetKeyCallback = linker.downcallHandle(glfwlib.findOrThrow("glfwSetKeyCallback"), of(JAVA_LONG, JAVA_LONG, JAVA_LONG));
         glfwSetCharCallback = linker.downcallHandle(glfwlib.findOrThrow("glfwSetCharCallback"), of(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwSetCharModsCallback = linker.downcallHandle(glfwlib.findOrThrow("glfwSetCharModsCallback"), of(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwSetMouseButtonCallback = linker.downcallHandle(glfwlib.findOrThrow("glfwSetMouseButtonCallback"), of(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwSetCursorPosCallback = linker.downcallHandle(glfwlib.findOrThrow("glfwSetCursorPosCallback"), of(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwSetCursorEnterCallback = linker.downcallHandle(glfwlib.findOrThrow("glfwSetCursorEnterCallback"), of(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwSetScrollCallback = linker.downcallHandle(glfwlib.findOrThrow("glfwSetScrollCallback"), of(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwSetDropCallback = linker.downcallHandle(glfwlib.findOrThrow("glfwSetDropCallback"), of(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwJoystickPresent = linker.downcallHandle(glfwlib.findOrThrow("glfwJoystickPresent"), of(JAVA_INT, JAVA_INT));
        glfwGetJoystickAxes = linker.downcallHandle(glfwlib.findOrThrow("glfwGetJoystickAxes"), of(JAVA_LONG, JAVA_INT, JAVA_LONG));
        glfwGetJoyStickButtons = linker.downcallHandle(glfwlib.findOrThrow("glfwGetJoystickButtons"), of(JAVA_LONG, JAVA_INT, JAVA_LONG));
        glfwGetJoystickHats = linker.downcallHandle(glfwlib.findOrThrow("glfwGetJoystickHats"), of(JAVA_LONG, JAVA_INT, JAVA_LONG));
        glfwGetJoystickName = linker.downcallHandle(glfwlib.findOrThrow("glfwGetJoystickName"), of(JAVA_LONG, JAVA_INT));
        glfwJoystickIsGamepad = linker.downcallHandle(glfwlib.findOrThrow("glfwJoystickIsGamepad"), of(JAVA_INT, JAVA_INT));
        glfwSetJoystickCallback = linker.downcallHandle(glfwlib.findOrThrow("glfwSetJoystickCallback"), of(JAVA_LONG, JAVA_LONG));
        glfwGetJoystickUserPointer = linker.downcallHandle(glfwlib.findOrThrow("glfwGetJoystickUserPointer"), of(JAVA_LONG, JAVA_INT));
        glfwUpdateGamepadMappings = linker.downcallHandle(glfwlib.findOrThrow("glfwUpdateGamepadMappings"), of(JAVA_INT, JAVA_LONG));
        glfwGetGamepadName = linker.downcallHandle(glfwlib.findOrThrow("glfwGetGamepadName"), of(JAVA_LONG, JAVA_INT));
        glfwGetGamepadState = linker.downcallHandle(glfwlib.findOrThrow("glfwGetGamepadState"), of(JAVA_INT, JAVA_INT, JAVA_LONG));
        glfwSetClipboardString = linker.downcallHandle(glfwlib.findOrThrow("glfwSetClipboardString"), ofVoid(JAVA_LONG, JAVA_LONG));
        glfwGetClipboardString = linker.downcallHandle(glfwlib.findOrThrow("glfwGetClipboardString"), of(JAVA_LONG, JAVA_LONG));
        glfwGetTime = linker.downcallHandle(glfwlib.findOrThrow("glfwGetTime"), of(JAVA_DOUBLE));
        glfwSetTime = linker.downcallHandle(glfwlib.findOrThrow("glfwSetTime"), ofVoid(JAVA_DOUBLE));
        glfwGetTimerValue = linker.downcallHandle(glfwlib.findOrThrow("glfwGetTimerValue"), of(JAVA_LONG));
        glfwGetTimerFrequency = linker.downcallHandle(glfwlib.findOrThrow("glfwGetTimerFrequency"), of(JAVA_LONG));
        glfwMakeContextCurrent = linker.downcallHandle(glfwlib.findOrThrow("glfwMakeContextCurrent"), ofVoid(JAVA_LONG));
        glfwGetCurrentContext = linker.downcallHandle(glfwlib.findOrThrow("glfwGetCurrentContext"), of(JAVA_LONG, JAVA_LONG));
        glfwSwapBuffers = linker.downcallHandle(glfwlib.findOrThrow("glfwSwapBuffers"), ofVoid(JAVA_LONG));
        glfwSwapInterval = linker.downcallHandle(glfwlib.findOrThrow("glfwSwapInterval"), ofVoid(JAVA_INT));
        glfwExtensionSupported = linker.downcallHandle(glfwlib.findOrThrow("glfwExtensionSupported"), of(JAVA_INT, JAVA_LONG));
        glfwGetProcAddress = linker.downcallHandle(glfwlib.findOrThrow("glfwGetProcAddress"), of(JAVA_LONG, JAVA_LONG));
        glfwVulkanSupported = linker.downcallHandle(glfwlib.findOrThrow("glfwVulkanSupported"), of(JAVA_INT));
        glfwGetRequiredInstanceExtensions = linker.downcallHandle(glfwlib.findOrThrow("glfwGetRequiredInstanceExtensions"), of(JAVA_LONG, JAVA_LONG));
        glfwGetInstanceProcAddress = linker.downcallHandle(glfwlib.findOrThrow("glfwGetInstanceProcAddress"), of(JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwGetPhysicalDevicePresentationSupport = linker.downcallHandle(glfwlib.findOrThrow("glfwGetPhysicalDevicePresentationSupport"), of(JAVA_INT, JAVA_LONG, JAVA_LONG, JAVA_LONG));
        glfwCreateWindowSurface = linker.downcallHandle(glfwlib.findOrThrow("glfwCreateWindowSurface"), of(JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG, JAVA_LONG));
    }

    public static int glfwInit() {
        try {
           return (int) glfwInit.invokeExact();
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwTerminate() {
        try {
            glfwTerminate.invokeExact();
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwInitHint(int hint, int value) {
        try {
            glfwInitHint.invokeExact(hint, value);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwInitAllocator(long allocator) {
        try {
            glfwInitAllocator.invokeExact(allocator);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwInitVulkanLoader(long loader) {
        try {
            glfwInitVulkanLoader.invokeExact(loader);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwGetVersion(IntPointer major, IntPointer minor, IntPointer rev) {
        try {

            glfwGetVersion.invokeExact(major.getPhysicalAddress(), minor.getPhysicalAddress(), rev.getPhysicalAddress());
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static StringPointer glfwGetVersionString() {
        try {
            return Pointer.ofAddressString((long)glfwGetVersionString.invokeExact());
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static int glfwGetError(StringPointer desc) {
        try {
            return (int) glfwGetError.invokeExact(desc);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetErrorCallback(GLFWerrorfun callback) {
        try {
            Method m = callback.getClass().getMethod("callback", int.class, long.class);
            MethodHandle callbackHandle = MethodHandles.lookup().unreflect(m);
            MemorySegment callbackAddress = linker.upcallStub(callbackHandle, ofVoid(JAVA_INT, JAVA_LONG), global);
            glfwSetErrorCallback.invokeExact(callbackAddress);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static int glfwGetPlatform() {
        try {
            return (int) glfwGetPlatform.invokeExact();
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static int glfwPlatformSupported(int platform) {
        try {
            return (int) glfwPlatformSupported.invokeExact(platform);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static LongPointer glfwGetMonitors(IntPointer count) {
        try {
            return Pointer.ofAddressLong((long) (glfwGetMonitors.invokeExact(count.getPhysicalAddress())));
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static long glfwGetPrimaryMonitor() {
        try {
            return (long) glfwGetPrimaryMonitor.invokeExact();
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwGetMonitorPos(long monitor, IntPointer xpos, IntPointer ypos) {
        try {
            glfwGetMonitorPos.invokeExact(monitor, xpos.getPhysicalAddress(), ypos.getPhysicalAddress());
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwGetMonitorWorkarea(long monitor, IntPointer xpos, IntPointer ypos, IntPointer width, IntPointer height) {
        try {
            glfwGetMonitorWorkArea.invokeExact(monitor, xpos.getPhysicalAddress(), ypos.getPhysicalAddress(), width.getPhysicalAddress(), height.getPhysicalAddress());
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwGetMonitorPhysicalSize(long monitor, IntPointer widthMM, IntPointer heightMM) {
        try {
            glfwGetMonitorPhysicalSize.invokeExact(monitor, widthMM.getPhysicalAddress(), heightMM.getPhysicalAddress());
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwGetMonitorContentScale(long monitor, FloatPointer xscale, FloatPointer yscale) {
        try {
            glfwGetMonitorContentScale.invokeExact(monitor, xscale.getPhysicalAddress(), yscale.getPhysicalAddress());
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static StringPointer glfwGetMonitorName(long monitor) {
        try {
            return StringPointer.ofAddressString((long) glfwGetMonitorName.invokeExact(monitor));
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetMonitorUserPointer(long monitor, long pointer) {
        try {
            glfwSetMonitorUserPointer.invokeExact(monitor, pointer);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static long glfwGetMonitorUserPointer(long monitor) {
        try {
            return ((long) glfwGetMonitorUserPointer.invokeExact(monitor));
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetMonitorCallback(GLFWmonitorfun callback) {
        try {
            Method m = callback.getClass().getMethod("callback", long.class, int.class);
            MethodHandle callbackHandle = MethodHandles.lookup().unreflect(m);
            MemorySegment callbackAddress = linker.upcallStub(callbackHandle, ofVoid(JAVA_LONG, JAVA_INT), global);
            glfwSetMonitorCallback.invokeExact(callbackAddress);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static long glfwGetVideoModes(long monitor, IntPointer count) {
        try {
            return (long) glfwGetVideoModes.invokeExact(monitor, count);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static long glfwGetVideoMode(long monitor) {
        try {
            return (long) glfwGetVideoMode.invokeExact(monitor);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetGamma(long monitor, float gamma) {
        try {
            glfwSetGamma.invokeExact(monitor, gamma);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static long glfwGetGammaRamp(long monitor) {
        try {
            return (long) glfwGetGammaRamp.invokeExact(monitor);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetGammaRamp(long monitor, long ramp) {
        try {
            glfwSetGammaRamp.invokeExact(monitor, ramp);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwDefaultWindowHints() {
        try {
            glfwDefaultWindowHints.invokeExact();
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwWindowHint(int hint, int value) {
        try {
            glfwWindowHint.invokeExact(hint, value);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwWindowHintString(int hint, StringPointer value) {
        try {
            glfwWindowHintString.invokeExact(hint, value.getPhysicalAddress());
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static long glfwCreateWindow(int width, int height, StringPointer title, long monitor, long share) {
        try {
            return (long) glfwCreateWindow.invokeExact(width, height, title.getPhysicalAddress(), monitor, share);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwDestroyWindow(long window) {
        try {
            glfwDestroyWindow.invokeExact(window);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static int glfwWindowShouldClose(long window) {
        try {
            return (int) glfwWindowShouldClose.invokeExact(window);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetWindowShouldClose(long window, int value) {
        try {
            glfwSetWindowShouldClose.invokeExact(window, value);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static StringPointer glfwGetWindowTitle(long window) {
        try {
            return Pointer.ofAddressString((long) glfwGetWindowTitle.invokeExact(window));
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetWindowTitle(long window, StringPointer title) {
        try {
            glfwSetWindowTitle.invokeExact(window, title.getPhysicalAddress());
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetWindowIcon(long window, int count, long images) {
        try {
            glfwSetWindowIcon.invokeExact(window, count, images);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwGetWindowPos(long window, IntPointer xpos, IntPointer ypos) {
        try {
            glfwGetWindowPos.invokeExact(window, xpos.getPhysicalAddress(), ypos.getPhysicalAddress());
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetWindowPos(long window, int xpos, int ypos) {
        try {
            glfwSetWindowPos.invokeExact(window, xpos, ypos);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwGetWindowSize(long window, IntPointer width, IntPointer height) {
        try {
            glfwGetWindowSize.invokeExact(window, width.getPhysicalAddress(), height.getPhysicalAddress());
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetWindowSizeLimits(long window, int minwidth, int minheight, int maxwidth, int maxheight) {
        try {
            glfwSetWindowSizeLimits.invokeExact(window, minwidth, minheight, maxwidth, maxheight);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetWindowAspectRatio(long window, int numer, int denom) {
        try {
            glfwSetWindowAspectRatio.invokeExact(window, numer, denom);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetWindowSize(long window, int width, int height) {
        try {
            glfwSetWindowSize.invokeExact(window, width, height);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwGetFramebufferSize(long window, IntPointer width, IntPointer height) {
        try {
            glfwGetFramebufferSize.invokeExact(window, width.getPhysicalAddress(), height.getPhysicalAddress());
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwGetWindowFrameSize(long window, IntPointer left, IntPointer top, IntPointer right, IntPointer bottom) {
        try {
            glfwGetWindowFrameSize.invokeExact(window, left.getPhysicalAddress(), top.getPhysicalAddress(), right.getPhysicalAddress(), bottom.getPhysicalAddress());
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwGetWindowContentScale(long window, FloatPointer xscale, FloatPointer yscale) {
        try {
            glfwGetWindowContentScale.invokeExact(window, xscale.getPhysicalAddress(), yscale.getPhysicalAddress());
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static float glfwGetWindowOpacity(long window) {
        try {
            return (float) glfwGetWindowOpacity.invokeExact(window);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetWindowOpacity(long window, float opacity) {
        try {
            glfwSetWindowOpacity.invokeExact(window, opacity);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwIconifyWindow(long window) {
        try {
            glfwIconifyWindow.invokeExact(window);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwRestoreWindow(long window) {
        try {
            glfwRestoreWindow.invokeExact(window);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwMaximizeWindow(long window) {
        try {
            glfwMaximizeWindow.invokeExact(window);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwShowWindow(long window) {
        try {
            glfwShowWindow.invokeExact(window);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwHideWindow(long window) {
        try {
            glfwHideWindow.invokeExact(window);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwFocusWindow(long window) {
        try {
            glfwFocusWindow.invokeExact(window);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwRequestWindowAttention(long window) {
        try {
            glfwRequestWindowAttention.invokeExact(window);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static long glfwGetWindowMonitor(long window) {
        try {
            return (long) glfwGetWindowMonitor.invokeExact(window);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetWindowMonitor(long window, long monitor, int xpos, int ypos, int width, int height, int refreshRate) {
        try {
            glfwSetWindowMonitor.invokeExact(window, monitor, xpos, ypos, width, height, refreshRate);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static int glfwGetWindowAttrib(long window, int attrib) {
        try {
            return (int) glfwGetWindowAttrib.invokeExact(window, attrib);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetWindowAttrib(long window, int attrib, int value) {
        try {
            glfwSetWindowAttrib.invokeExact(window, attrib, value);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetWindowUserPointer(long window, long pointer) {
        try {
            glfwSetWindowUserPointer.invokeExact(window, pointer);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static long glfwGetWindowUserPointer(long window) {
        try {
            return (long) glfwGetWindowUserPointer.invokeExact(window);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetWindowPosCallback(long window, GLFWwindowposfun callback) {
        try {
            Method m = callback.getClass().getMethod("callback", long.class, int.class, int.class);
            MethodHandle callbackHandle = MethodHandles.lookup().unreflect(m);
            MemorySegment callbackAddress = linker.upcallStub(callbackHandle, ofVoid(JAVA_LONG, JAVA_INT, JAVA_INT), global);
            glfwSetWindowPosCallback.invokeExact(window, callbackAddress);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetWindowSizeCallback(long window, GLFWwindowsizefun callback) {
        try {
            Method m = callback.getClass().getMethod("callback", long.class, int.class, int.class);
            MethodHandle callbackHandle = MethodHandles.lookup().unreflect(m);
            MemorySegment callbackAddress = linker.upcallStub(callbackHandle, ofVoid(JAVA_LONG, JAVA_INT, JAVA_INT), global);
            glfwSetWindowSizeCallback.invokeExact(window, callbackAddress);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetWindowCloseCallback(long window, GLFWwindowclosefun callback) {
        try {
            Method m = callback.getClass().getMethod("callback", long.class);
            MethodHandle callbackHandle = MethodHandles.lookup().unreflect(m);
            MemorySegment callbackAddress = linker.upcallStub(callbackHandle, ofVoid(JAVA_LONG), global);
            glfwSetWindowCloseCallback.invokeExact(window, callbackAddress);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetWindowRefreshCallback(long window, GLFWwindowrefreshfun callback) {
        try {
            Method m = callback.getClass().getMethod("callback", long.class);
            MethodHandle callbackHandle = MethodHandles.lookup().unreflect(m);
            MemorySegment callbackAddress = linker.upcallStub(callbackHandle, ofVoid(JAVA_LONG), global);
            glfwSetWindowRefreshCallback.invokeExact(window, callbackAddress);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetWindowFocusCallback(long window, GLFWwindowfocusfun callback) {
        try {
            Method m = callback.getClass().getMethod("callback", long.class, int.class);
            MethodHandle callbackHandle = MethodHandles.lookup().unreflect(m);
            MemorySegment callbackAddress = linker.upcallStub(callbackHandle, ofVoid(JAVA_LONG, JAVA_INT), global);
            glfwSetWindowFocusCallback.invokeExact(window, callbackAddress);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetWindowIconifyCallback(long window, GLFWwindowiconifyfun callback) {
        try {
            Method m = callback.getClass().getMethod("callback", long.class, int.class);
            MethodHandle callbackHandle = MethodHandles.lookup().unreflect(m);
            MemorySegment callbackAddress = linker.upcallStub(callbackHandle, ofVoid(JAVA_LONG, JAVA_INT), global);
            glfwSetWindowIconifyCallback.invokeExact(window, callbackAddress);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetWindowMaximizeCallback(long window, GLFWwindowmaximizefun callback) {
        try {
            Method m = callback.getClass().getMethod("callback", long.class, int.class);
            MethodHandle callbackHandle = MethodHandles.lookup().unreflect(m);
            MemorySegment callbackAddress = linker.upcallStub(callbackHandle, ofVoid(JAVA_LONG, JAVA_INT), global);
            glfwSetWindowMaximizeCallback.invokeExact(window, callbackAddress);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetFramebufferSizeCallback(long window, GLFWframebuffersizefun callback) {
        try {
            Method m = callback.getClass().getMethod("callback", long.class, int.class, int.class);
            MethodHandle callbackHandle = MethodHandles.lookup().unreflect(m);
            MemorySegment callbackAddress = linker.upcallStub(callbackHandle, ofVoid(JAVA_LONG, JAVA_INT, JAVA_INT), global);
            glfwSetFramebufferSizeCallback.invokeExact(window, callbackAddress);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetWindowContentScaleCallback(long window, GLFWwindowcontentscalefun callback) {
        try {
            Method m = callback.getClass().getMethod("callback", long.class, float.class, float.class);
            MethodHandle callbackHandle = MethodHandles.lookup().unreflect(m);
            MemorySegment callbackAddress = linker.upcallStub(callbackHandle, ofVoid(JAVA_LONG, JAVA_FLOAT, JAVA_FLOAT), global);
            glfwSetWindowContentScaleCallback.invokeExact(window, callbackAddress);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwPollEvents() {
        try {
            glfwPollEvents.invokeExact();
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwWaitEvents() {
        try {
            glfwWaitEvents.invokeExact();
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwWaitEventsTimeout(double timeout) {
        try {
            glfwWaitEventsTimeout.invokeExact(timeout);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwPostEmptyEvent() {
        try {
            glfwPostEmptyEvent.invokeExact();
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static int glfwGetInputMode(long window, int mode) {
        try {
            return (int) glfwGetInputMode.invokeExact(window, mode);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetInputMode(long window, int mode, int value) {
        try {
            glfwSetInputMode.invokeExact(window, mode, value);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static int glfwRawMouseMotionSupported() {
        try {
            return (int) glfwRawMouseMotionSupported.invokeExact();
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static MemorySegment glfwGetKeyName(int key, int scancode) {
        try {
            return (MemorySegment) glfwGetKeyName.invokeExact(key, scancode);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static int glfwGetKeyScancode(int key) {
        try {
            return (int) glfwGetKeyScancode.invokeExact(key);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static int glfwGetKey(long window, int key) {
        try {
            return (int) glfwGetKey.invokeExact(window, key);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static int glfwGetMouseButton(long window, int button) {
        try {
            return (int) glfwGetMouseButton.invokeExact(window, button);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwGetCursorPos(long window, DoublePointer xpos, DoublePointer ypos) {
        try {
            glfwGetCursorPos.invokeExact(window, xpos.getPhysicalAddress(), ypos.getPhysicalAddress());
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetCursorPos(long window, double xpos, double ypos) {
        try {
            glfwSetCursorPos.invokeExact(window, xpos, ypos);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    private static long glfwCreateCursor(long image, int xhot, int yhot) {
        try {
            return (long) glfwCreateCursor.invokeExact(image, xhot, yhot);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static long glfwCreateStandardCursor(int shape) {
        try {
            return (long) glfwCreateStandardCursor.invokeExact(shape);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwDestroyCursor(long cursor) {
        try {
            glfwDestroyCursor.invokeExact(cursor);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetCursor(long window, long cursor) {
        try {
            glfwSetCursor.invokeExact(window, cursor);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetKeyCallback(long window, GLFWkeyfun callback) {
        try {
            Method m = callback.getClass().getMethod("callback", long.class, int.class, int.class, int.class, int.class);
            MethodHandle callbackHandle = MethodHandles.lookup().unreflect(m);
            MemorySegment callbackAddress = linker.upcallStub(callbackHandle, ofVoid(JAVA_LONG, JAVA_INT, JAVA_INT, JAVA_INT, JAVA_INT), global);
            glfwSetKeyCallback.invokeExact(window, callbackAddress);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetCharCallback(long window, GLFWcharfun callback) {
        try {
            Method m = callback.getClass().getMethod("callback", long.class, int.class);
            MethodHandle callbackHandle = MethodHandles.lookup().unreflect(m);
            MemorySegment callbackAddress = linker.upcallStub(callbackHandle, ofVoid(JAVA_LONG, JAVA_INT), global);
            glfwSetCharCallback.invokeExact(window, callbackAddress);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetCharModsCallback(long window, GLFWcharmodsfun callback) {
        try {
            Method m = callback.getClass().getMethod("callback", long.class, int.class, int.class);
            MethodHandle callbackHandle = MethodHandles.lookup().unreflect(m);
            MemorySegment callbackAddress = linker.upcallStub(callbackHandle, ofVoid(JAVA_LONG, JAVA_INT, JAVA_INT), global);
            glfwSetCharModsCallback.invokeExact(window, callbackAddress);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetMouseButtonCallback(long window, GLFWmousebuttonfun callback) {
        try {
            Method m = callback.getClass().getMethod("callback", long.class, int.class, int.class, int.class);
            MethodHandle callbackHandle = MethodHandles.lookup().unreflect(m);
            MemorySegment callbackAddress = linker.upcallStub(callbackHandle, ofVoid(JAVA_LONG, JAVA_INT, JAVA_INT, JAVA_INT), global);
            glfwSetMouseButtonCallback.invokeExact(window, callbackAddress);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetCursorPosCallback(long window, GLFWcursorposfun callback) {
        try {
            Method m = callback.getClass().getMethod("callback", long.class, double.class, double.class);
            MethodHandle callbackHandle = MethodHandles.lookup().unreflect(m);
            MemorySegment callbackAddress = linker.upcallStub(callbackHandle, ofVoid(JAVA_LONG, JAVA_DOUBLE, JAVA_DOUBLE), global);
            glfwSetCursorPosCallback.invokeExact(window, callbackAddress);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetScrollCallback(long window, GLFWscrollfun callback) {
        try {
            Method m = callback.getClass().getMethod("callback", long.class, double.class, double.class);
            MethodHandle callbackHandle = MethodHandles.lookup().unreflect(m);
            MemorySegment callbackAddress = linker.upcallStub(callbackHandle, ofVoid(JAVA_LONG, JAVA_DOUBLE, JAVA_DOUBLE), global);
            glfwSetScrollCallback.invokeExact(window, callbackAddress);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetDropCallback(long window, GLFWdropfun callback) {
        try {
            Method m = callback.getClass().getMethod("callback", long.class, int.class, MemorySegment[].class);
            MethodHandle callbackHandle = MethodHandles.lookup().unreflect(m);
            MemorySegment callbackAddress = linker.upcallStub(callbackHandle, ofVoid(JAVA_LONG, JAVA_INT, ADDRESS), global);
            glfwSetDropCallback.invokeExact(window, callbackAddress);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static int glfwJoystickPresent(int jid) {
        try {
            return (int) glfwJoystickPresent.invokeExact(jid);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static FloatPointer glfwGetJoyStickAxes(int jid, IntPointer count) {
        try {
            return Pointer.ofAddressFloat((long) glfwGetJoystickAxes.invokeExact(jid, count));
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static StringPointer glfwGetJoystickHats(int jid, IntPointer count) {
        try {
            return StringPointer.ofAddressString((long) glfwGetJoystickHats.invokeExact(jid, count.getPhysicalAddress()));
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static StringPointer glfwGetJoystickName(int jid) {
        try {
            return StringPointer.ofAddressString((long) glfwGetJoystickName.invokeExact(jid));
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static StringPointer glfwGetJoystickGUID(int jid) {
        try {
            return StringPointer.ofAddressString((long) glfwGetJoystickGUID.invokeExact(jid));
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetJoystickUserPointer(int jid, long pointer) {
        try {
            glfwSetJoystickUserPointer.invokeExact(jid, pointer);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static long glfwGetJoystickUserPointer(int jid) {
        try {
            return (long) glfwGetJoystickUserPointer.invokeExact(jid);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static int glfwJoystickIsGamepad(int jid) {
        try {
            return (int) glfwJoystickIsGamepad.invokeExact(jid);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetJoystickCallback(GLFWjoystickfun callback) {
        try {
            Method m = callback.getClass().getMethod("callback", int.class, int.class);
            MethodHandle handle = MethodHandles.lookup().unreflect(m);
            MemorySegment address = linker.upcallStub(handle, ofVoid(JAVA_INT, JAVA_INT), global);
            glfwSetJoystickCallback.invokeExact(address);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static int glfwUpdateGamepadMappings(StringPointer string) {
        try {
            return (int) glfwUpdateGamepadMappings.invokeExact(string.getPhysicalAddress());
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static StringPointer glfwGetGamepadName(int jid) {
        try {
            return Pointer.ofAddressString((long) glfwGetGamepadName.invokeExact(jid));
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static int glfwGetGamepadState(int jid, long state) {
        try {
            return (int) glfwGetGamepadState.invokeExact(jid, state);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetClipboardString(long window, StringPointer string) {
        try {
            glfwSetClipboardString.invokeExact(window, string.getPhysicalAddress());
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static StringPointer glfwGetClipboardString(long window) {
        try {
            return Pointer.ofAddressString((long) glfwGetClipboardString.invokeExact(window));
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static double glfwGetTime() {
        try {
            return (double) glfwGetTime.invokeExact();
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSetTime(double time) {
        try {
            glfwSetTime.invokeExact(time);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static long glfwGetTimerValue() {
        try {
            return (long) glfwGetTimerValue.invokeExact();
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static long glfwGetTimerFrequency() {
        try {
            return (long) glfwGetTimerFrequency.invokeExact();
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwMakeContextCurrent(long window) {
        try {
            glfwMakeContextCurrent.invokeExact(window);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static long glfwGetCurrentContext() {
        try {
            return (long) glfwGetCurrentContext.invokeExact();
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSwapBuffers(long window) {
        try {
            glfwSwapBuffers.invokeExact(window);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static void glfwSwapInterval(int interval) {
        try {
            glfwSwapInterval.invokeExact(interval);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static int glfwExtensionSupported(StringPointer extension) {
        try {
            return (int) glfwExtensionSupported.invokeExact(extension.getPhysicalAddress());
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static long glfwGetProcAddress(StringPointer procname) {
        try {
            return (long) glfwGetProcAddress.invokeExact(procname.getPhysicalAddress());
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static int glfwVulkanSupported() {
        try {
            return (int) glfwVulkanSupported.invokeExact();
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static StringPointer glfwGetRequiredInstanceExtensions(IntPointer count) {
        try {
            return StringPointer.ofAddressString((long) glfwGetRequiredInstanceExtensions.invokeExact(count));
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static long glfwGetInstanceProcAddress(long vkinstance, StringPointer procname) {
        try {
            return (long) glfwGetInstanceProcAddress.invokeExact();
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static int glfwGetPhysicalDevicePresentationSupport(long instance, long device, int queuefamily) {
        try {
            return (int) glfwGetPhysicalDevicePresentationSupport.invokeExact(instance, device, queuefamily);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static long glfwCreateWindowSurface(long instance, long window, long allocator, long surface) {
        try {
            return (long) glfwCreateWindowSurface.invokeExact(instance, window, allocator, surface);
        } catch (Throwable e) {
            throw new GLFWException(e);
        }
    }

    public static String getLibraryDirectory() {
        return GLFW.class.getResource("/glfw/glfw3.dll").getPath();
    }
}
