---
name: new-docs
description: Sets up the local environment for new documentation changes
---

# New Docs Skill

Sets up the local environment for new documentation changes

## Usage
```
/new-docs <docs-description>
```

## Behavior
1. Checkout the `main` branch
2. Pull changes and merge them in the main branch
3. Checkout into a new branch
    - The branch format should be `docs/<docs-description>`

## Example Output
```
/new-docs update-readme

docs: update-readme
```
