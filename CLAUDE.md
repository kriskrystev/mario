# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project overview

Mario is a Java game built from scratch on top of [LWJGL](https://www.lwjgl.org/) (Lightweight Java Game Library), following the "Java Game Engine Development" style tutorials. The project currently sets up a GLFW window and an OpenGL rendering context as the foundation for a 2D engine.

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
- `jade/Window.java` is a singleton (`Window.get()`) that owns the GLFW/OpenGL lifecycle: `init()` creates and configures the GLFW window and OpenGL context, `loop()` is the main render loop (currently just clears the screen and polls events). Future engine code should follow this `jade` package convention.

## Build configuration notes

- `build.gradle` hardcodes `lwjglNatives = "natives-windows"`. To build/run on macOS or Linux, this must be changed to the matching classifier (`natives-macos`, `natives-linux`).
- Dependencies: LWJGL 3.4.2 (core, assimp, glfw, nfd, openal, opengl, stb) and JOML 1.10.9 for math.
