---
name: new-chore
description: Sets up the local environment for a new maintenance task
---

# New Chore Skill

Sets up the local environment for a new maintenance task

## Usage
```
/new-chore <chore-description>
```

## Behavior
1. Checkout the `main` branch
2. Pull changes and merge them in the main branch
3. Checkout into a new branch
    - The branch format should be `chore/<chore-description>`

## Example Output
```
/new-chore bump-lwjgl-version

chore: bump-lwjgl-version
```
