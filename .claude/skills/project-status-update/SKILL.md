---
name: project-status-update
description: Posts a status update to a GitHub Project (v2), summarizing recent progress and project health
---

# Project Status Update Skill

Adds a status update to a GitHub Project (v2) — a brief report tracking the project's health and progress.

## Usage
```
/project-status-update [project name or number]
```

## Behavior
1. Check `gh auth status` for the `project` scope. If missing, run:
   ```
   gh auth refresh -h github.com -s project,read:project
   ```
   This opens a device-code flow — relay the one-time code and URL to the user and wait for approval.
2. Determine the target project:
   - If an argument was given, match it against `gh project list --owner <owner>` (by number or title).
   - If no argument and there's exactly one project whose title plausibly matches the current repo, use it.
   - Otherwise, list the owner's projects (`gh project list --owner <owner>`) and ask the user to pick one.
   - `<owner>` defaults to the authenticated user (`gh api user --jq .login`) unless the repo's org is the obvious owner.
3. Gather recent context to draft the update:
   - Recent merged PRs / commits since the last status update (`gh pr list --state merged --limit 10`, `git log`)
   - If a previous status update exists on the project, use its date as the "since" cutoff
4. Draft:
   - **Status**: one of `ON_TRACK`, `AT_RISK`, `OFF_TRACK`, `COMPLETE`, `INACTIVE` — infer from recent activity (e.g. steady merged PRs → ON_TRACK; no activity / blocked → AT_RISK or OFF_TRACK), defaulting to `ON_TRACK` absent signals otherwise.
   - **Body**: a short prose/bullet summary of what shipped and what's next (see Body Format below).
5. Show the target project, status, and body, and ask for confirmation. Y/N choice.
6. On confirmation:
   - Resolve the project's node ID: `gh project view <number> --owner <owner> --format json --jq .id`
   - Post the update via GraphQL:
     ```
     gh api graphql -f query='
     mutation($projectId: ID!, $status: ProjectV2StatusUpdateStatus!, $body: String!) {
       createProjectV2StatusUpdate(input: { projectId: $projectId, status: $status, body: $body }) {
         statusUpdate { id status createdAt }
       }
     }' -f projectId="<id>" -f status="<STATUS>" -f body="<body>"
     ```
7. Confirm success to the user (status + timestamp). GitHub Projects has no CLI view URL for a single status update — point the user to the project's URL (`https://github.com/users/<owner>/projects/<number>` or `https://github.com/orgs/<owner>/projects/<number>`) if they want to see it.

## Body Format
Short prose or a few bullet points covering:
- What shipped since the last update
- Any blockers or risks (if status isn't ON_TRACK)
- What's next

## Example Output
```
/project-status-update mario game

Target project: mario game
Status: On Track
Body:
GLFW window and OpenGL context are up and running as the engine foundation. Since the last check-in:
- Added mouse and keyboard input handling
- Added delta-time tracking to the render loop

Next up: using dt to drive frame-independent game logic.

Post this status update? (Y/N)

Status update posted to "mario game" (On Track, 2026-08-22T11:25:46Z).
```
