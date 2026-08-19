---
name: new-fix
description: Sets up the local environment for a new bug fix
---

# New Fix Skill

Sets up the local environment for a new bug fix

## Usage
```
/new-fix <fix-description>
```

## Behavior
1. Checkout the `main` branch
2. Pull changes and merge them in the main branch
3. Checkout into a new branch
    - The branch format should be `fix/<fix-description>`

## Example Output
```
/new-fix window-not-closing

fix: window-not-closing
```
