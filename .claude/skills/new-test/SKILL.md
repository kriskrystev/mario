---
name: new-test
description: Sets up the local environment for new or modified tests
---

# New Test Skill

Sets up the local environment for new or modified tests

## Usage
```
/new-test <test-description>
```

## Behavior
1. Checkout the `main` branch
2. Pull changes and merge them in the main branch
3. Checkout into a new branch
    - The branch format should be `test/<test-description>`

## Example Output
```
/new-test collision-detection

test: collision-detection
```
