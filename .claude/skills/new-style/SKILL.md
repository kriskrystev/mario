---
name: new-style
description: Sets up the local environment for new code style changes
---

# New Style Skill

Sets up the local environment for new code style changes

## Usage
```
/new-style <style-description>
```

## Behavior
1. Checkout the `main` branch
2. Pull changes and merge them in the main branch
3. Checkout into a new branch
    - The branch format should be `style/<style-description>`

## Example Output
```
/new-style reformat-window-class

style: reformat-window-class
```
