# GitHub Packages Deployment Guide

This project is configured to deploy artifacts to GitHub Packages. To successfully deploy, you need to set up authentication with GitHub.

## Authentication Setup

The project uses environment variables for GitHub authentication to avoid hardcoding credentials in configuration files.

### Setting Environment Variables

#### Windows

```powershell
# Set GitHub username
$env:GITHUB_USERNAME = "your-github-username"

# Set GitHub Personal Access Token
$env:GITHUB_TOKEN = "your-personal-access-token"
```

#### Linux/macOS

```bash
# Set GitHub username
export GITHUB_USERNAME="your-github-username"

# Set GitHub Personal Access Token
export GITHUB_TOKEN="your-personal-access-token"
```

### Creating a GitHub Personal Access Token

1. Go to your GitHub account settings
2. Select "Developer settings" from the sidebar
3. Click on "Personal access tokens" and then "Tokens (classic)"
4. Click "Generate new token" and select "Generate new token (classic)"
5. Give your token a descriptive name
6. Select the following scopes:
   - `read:packages`
   - `write:packages`
   - `delete:packages` (optional)
   - `repo` (if your repository is private)
7. Click "Generate token"
8. Copy the token immediately (you won't be able to see it again)

## Deploying to GitHub Packages

Once you've set up the environment variables, you can deploy to GitHub Packages using:

```bash
mvn deploy
```

## Troubleshooting

If you encounter a 401 Unauthorized error:

1. Verify that your environment variables are set correctly
2. Ensure your Personal Access Token has the necessary permissions
3. Check that your token hasn't expired (GitHub PATs can expire)
4. Confirm that you have access to the repository specified in the pom.xml