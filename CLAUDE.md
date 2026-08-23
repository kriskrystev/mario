# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project overview

Mario is a Java game built from scratch on top of [LWJGL](https://www.lwjgl.org/) (Lightweight Java Game Library), following the "Java Game Engine Development" style tutorials. The project currently sets up a GLFW window and an OpenGL rendering context, scene management, a reusable shader abstraction, and a 2D camera as the foundation for a 2D engine.

## Commands

Build:
```bash
./gradlew build
```
On Windows: `gradlew.bat build`

Run: there is no `application` plugin configured yet, so `./gradlew run` does not work. Run `Main.main()` directly from an IDE (e.g. IntelliJ IDEA).

Test: JUnit 6 (Jupiter) is wired up via `test { useJUnitPlatform() }`, but no tests exist yet.
```bash
./gradlew test
```

## Architecture

- `Main.java` is the entry point; it fetches the singleton `Window` and calls `run()`.
- `jade/Window.java` is a singleton (`Window.get()`) that owns the GLFW/OpenGL lifecycle: `init()` creates and configures the GLFW window and OpenGL context and calls `Window.changeScene(0)` to start on `LevelEditorScene`, `loop()` is the main render loop (computes `dt` via `util.Time.getTime()` and delegates to `currentScene.update(dt)`), `run()` also tears down GLFW callbacks/resources on exit. Future engine code should follow this `jade` package convention.
- `jade/MouseListener.java` and `jade/KeyListener.java` are singletons (same `get()` pattern as `Window`) populated by GLFW callbacks registered in `Window.init()`. See README's "Input handling" section for their public API.
- `jade/Scene.java` is an abstract base class (`init()` hook + abstract `update(float dt)`) holding a `protected Camera camera`. `jade/LevelEditorScene.java` and `jade/LevelScene.java` are concrete scenes; `Window.changeScene(int)` swaps the active scene (0 → `LevelEditorScene`, 1 → `LevelScene`) and calls its `init()`.
- `jade/Camera.java` holds a 2D `position` and computes an orthographic `projectionMatrix` and a `lookAt`-based `viewMatrix` (JOML), uploaded to shaders each frame as `uProjection`/`uView`.
- `renderer/Shader.java` loads a combined vertex+fragment source file (split on `#type vertex` / `#type fragment` markers, see `assets/shaders/default.glsl`), compiles/links the GL program, and exposes `use()`/`detach()` plus `uploadMat4f`/`uploadMat3f`/`uploadVec4f`/`uploadVec3f`/`uploadVec2f`/`uploadFloat`/`uploadInt` uniform helpers (each implicitly calls `use()`).
- `util/Time.java` provides `Time.getTime()`, seconds elapsed since class load, used to compute per-frame `dt`.

## Build configuration notes

- `build.gradle` hardcodes `lwjglNatives = "natives-windows"`. To build/run on macOS or Linux, this must be changed to the matching classifier (`natives-macos`, `natives-linux`).
- Dependencies: LWJGL 3.4.2 (core, assimp, glfw, nfd, openal, opengl, stb) and JOML 1.10.9 for math.
