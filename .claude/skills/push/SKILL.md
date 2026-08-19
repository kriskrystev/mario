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
   - Label: derive the `<type>` prefix from the branch name (e.g. `feat/...` → `feat`) and use it as the label name. If a label with that name doesn't exist in the repo yet, create it first (`gh label create <type>`).
   - Assignee: the authenticated GitHub user pushing this branch and opening the PR — i.e. the code's owner (`@me`)
4. Show the generated title, body, label, and assignee, and ask for confirmation. Y/N choice.
5. On confirmation:
   - If no PR exists - create one with the generated title, body, label, and assignee
   - If a PR exists - update its title, body, label, and assignee with the generated details
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

Label: feat
Assignee: @me

Create PR with these details? (Y/N)

Created PR: https://github.com/<org>/<repo>/pull/<number>
```
