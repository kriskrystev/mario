---
name: new-refactor
description: Sets up the local environment for a new refactor
---

# New Refactor Skill

Sets up the local environment for a new refactor

## Usage
```
/new-refactor <refactor-description>
```

## Behavior
1. Checkout the `main` branch
2. Pull changes and merge them in the main branch
3. Checkout into a new branch
    - The branch format should be `refactor/<refactor-description>`

## Example Output
```
/new-refactor extract-renderer

refactor: extract-renderer
```
