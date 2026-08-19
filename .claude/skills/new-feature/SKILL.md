---
name: new-feature
description: Sets up the local environment for a new feature
---

# New Feature Skill

Sets up the local environment for a new feature

## Usage
```
/new-feature <feature-description>
```

## Behavior
1. Checkout the `main` branch
2. Pull changes and merge them in the main branch
3. Checkout into a new branch
    - The branch format should be ```<type>/<feature-description>```

## Example Output
```
/new-feature user-jump-mechanism

feat: user-jump-mechanism
```
