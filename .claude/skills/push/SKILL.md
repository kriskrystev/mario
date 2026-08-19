---
name: push
description: Pushes the current branch to origin and creates or updates its pull request
---

# Push Skill

Pushes the current branch to origin and creates or updates its pull request

## Usage
```
/push
```

## Behavior
1. Push the current branch to `origin`
   - If the branch has no upstream yet, push with `-u` to set it
2. Check whether a pull request already exists for this branch
3. Generate PR details from the branch's commits/diff against `main`:
   - Title: short summary of the change
   - Body: using the PR Body Format below
4. Show the generated title and body, and ask for confirmation. Y/N choice.
5. On confirmation:
   - If no PR exists - create one with the generated title and body
   - If a PR exists - update its title and body with the generated details
6. Output the PR URL

## PR Body Format
```
## Summary
- <bullet points summarizing the change>

## Test plan
- [ ] <bullet points describing how the change was/should be verified>
```

## Example Output
```
/push

Pushed feat/user-jump-mechanism to origin.

Title: Add jumping to the game
Body:
## Summary
- Handle jump keypress
- Move character vertically

## Test plan
- [ ] Verified jump triggers on keypress
- [ ] Verified character returns to ground

Create PR with these details? (Y/N)

Created PR: https://github.com/<org>/<repo>/pull/<number>
```
