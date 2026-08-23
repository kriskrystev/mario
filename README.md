# Mario

A Java game built from scratch on top of [LWJGL](https://www.lwjgl.org/) (Lightweight Java Game Library), following the "Java Game Engine Development" style tutorials. The project currently sets up a GLFW window and an OpenGL rendering context, mouse and keyboard input handling, scene management, a reusable shader abstraction, and a 2D camera, as the foundation for a 2D engine.

## Tech stack

- Java (Gradle project)
- [LWJGL 3.4.2](https://www.lwjgl.org/) — GLFW, OpenGL, Assimp, STB, OpenAL, NFD bindings
- [JOML 1.10.9](https://github.com/JOML-CI/JOML) — math library for OpenGL
- JUnit 6 for tests

> **Note:** `build.gradle` is currently pinned to `natives-windows`, so the project builds and runs on Windows out of the box. To run on macOS or Linux, change the `lwjglNatives` property in `build.gradle` to the matching classifier (e.g. `natives-macos`, `natives-linux`).

## Prerequisites

- JDK 17+ (or whatever version your local `JAVA_HOME` points to)
- No local Gradle install needed — the project uses the Gradle Wrapper

## Building

```bash
./gradlew build
```

On Windows (PowerShell/cmd):

```
gradlew.bat build
```

## Running

The `application` Gradle plugin isn't wired up yet, so run `Main.main()` directly from your IDE (e.g. IntelliJ IDEA).

## Project structure

```
src/main/java/
├── Main.java              # Entry point
├── jade/
│   ├── Window.java         # GLFW window setup and main render loop
│   ├── MouseListener.java  # Mouse position, drag, button, and scroll state
│   ├── KeyListener.java    # Keyboard key state
│   ├── Scene.java          # Abstract base class for scenes
│   ├── LevelEditorScene.java # Renders test geometry via the shader/camera pipeline
│   ├── LevelScene.java     # Placeholder scene
│   └── Camera.java         # 2D orthographic camera (position, projection/view matrices)
├── renderer/
│   └── Shader.java         # Loads, compiles, links, and binds GLSL shader programs
└── util/
    └── Time.java           # Time.getTime() — seconds elapsed since class load
```

## Input handling

Mouse and keyboard input are tracked via GLFW callbacks registered on the window in `Window.init()`, and exposed through two singletons:

- `MouseListener` — cursor position (`getX()`/`getY()`), movement delta (`getDx()`/`getDy()`), scroll offset (`getScrollX()`/`getScrollY()`), drag state (`isDragging()`), and button state (`mouseButtonDown(button)`)
- `KeyListener` — key state via `isKeyPressed(keyCode)`, backed by GLFW key codes

Both singletons are updated automatically by their registered GLFW callbacks and can be queried from anywhere via `MouseListener.get()` / `KeyListener.get()`.

## Scene management

`Scene` is an abstract base class with an `init()` hook and an abstract `update(float dt)`, plus a `protected Camera camera` available to subclasses. `Window.changeScene(int)` swaps the active scene and calls its `init()`:

- `0` → `LevelEditorScene`
- `1` → `LevelScene`

`Window.loop()` computes `dt` each frame via `Time.getTime()` and calls `currentScene.update(dt)`.

## Rendering

- `renderer/Shader` loads a single `.glsl` file containing both a vertex and fragment shader, split on `#type vertex` / `#type fragment` markers (see `assets/shaders/default.glsl`). Call `compile()` once after construction, then `use()`/`detach()` to bind/unbind the program each frame. Uniform values are set via `uploadMat4f`, `uploadMat3f`, `uploadVec4f`, `uploadVec3f`, `uploadVec2f`, `uploadFloat`, and `uploadInt`.
- `jade/Camera` holds a 2D `position` and exposes `getProjectionMatrix()` (orthographic) and `getViewMatrix()` (`lookAt`-based), typically uploaded to a shader as `uProjection`/`uView` uniforms each frame.
