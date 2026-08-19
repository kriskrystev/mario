---
name: commit
description: Create a well-formatted conventional commit from staged changes, with a Y/N confirmation before committing.
---

# Git Commit Skill

Create well-formatted git commits following conventional commit standards.

## Usage
```
/commit
```

## Behavior
1. Analyze staged changes with `git diff --staged`
2. Generate a conventional commit message, do not mention that it was created by claude. Use the current branch which I am on.
3. Show a confirmation prompt. The prompt should ask if the commit message is ok or not. Y/N choice.
4. Create the commit with proper formatting

## Commit Format
```
<type>: <description>

[optional body]

[optional footer]

```

## Types
- feat: New feature
- fix: Bug fix
- docs: Documentation changes
- style: Code style changes
- refactor: Code refactoring
- test: Adding or modifying tests
- chore: Maintanance tasks

## Example output
```
feat: add jumping to the game
- Handle keypress
- Move character vertically
```

```
fix: correct GLFW window not closing on Alt+F4
- Register window close callback
- Break render loop when close flag is set
```

```
docs: update README with build and run instructions
- Document Gradle wrapper commands
- Note Windows-only native dependency
```

```
style: reformat Window.java for consistent indentation
- Align brace style with rest of codebase
- Remove trailing whitespace
```

```
refactor: extract render loop into separate Renderer class
- Move OpenGL clear/swap calls out of Window
- Keep Window responsible only for GLFW lifecycle
```

```
test: add unit tests for collision detection
- Cover AABB overlap cases
- Cover edge-touching boundary case
```

```
chore: bump LWJGL to 3.4.2
- Update lwjglVersion in build.gradle
- Verify natives-windows artifacts resolve
```