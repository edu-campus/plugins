# GitHub Packages Deployment Guide

This project is configured to deploy artifacts to GitHub Packages. Follow these steps to set up and use this configuration.

## Project Configuration

The project is already configured with:

1. GitHub Packages repository in the `pom.xml`
2. Distribution management for deploying to GitHub Packages
3. Maven deploy plugin configuration with altDeploymentRepository

## Setup Instructions

### 1. Update the pom.xml (Optional)

The `pom.xml` contains a placeholder for your GitHub username:

```xml
<properties>
  <github.owner>GithubforBen</github.owner>
  <github.repo>plugins</github.repo>
  <!-- other properties -->
</properties>
```

You can either:
- Replace `GITHUB_USERNAME` directly in the pom.xml with your actual GitHub username
- Or use the settings.xml approach described below (recommended)

### 2. Configure Maven Settings

A template for `settings.xml` is provided at `src/main/resources/settings.xml.template`. 

1. Copy this template to your Maven settings location:
   - Windows: `%USERPROFILE%\.m2\settings.xml`
   - Linux/macOS: `~/.m2/settings.xml`

2. Edit the settings.xml file and replace:
   - `YOUR_GITHUB_USERNAME` with your actual GitHub username
   - `YOUR_GITHUB_TOKEN` with your GitHub Personal Access Token

```xml
<servers>
  <server>
    <id>github</id>
    <username>YOUR_GITHUB_USERNAME</username>
    <password>YOUR_GITHUB_TOKEN</password>
  </server>
</servers>
```

Here's a complete example of what your settings.xml should look like:

> **Important**: Make sure the server `id` in settings.xml matches the repository `id` in pom.xml (both should be "github").

```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">

  <activeProfiles>
    <activeProfile>github</activeProfile>
  </activeProfiles>

  <profiles>
    <profile>
      <id>github</id>
      <properties>
        <github.owner>GithubforBen</github.owner>
      </properties>
    </profile>
  </profiles>

  <servers>
    <server>
      <id>github</id>
      <username>GithubforBen</username>
      <password>ghp_your_personal_access_token_here</password>
    </server>
  </servers>
</settings>
```

### 3. Creating a GitHub Personal Access Token

> **Note**: For Maven deployments, use a **classic** token rather than a fine-grained token. Fine-grained tokens may not work correctly with Maven's authentication mechanism.

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

The token will start with `ghp_` and should be used as the password in your settings.xml file.

## Deploying to GitHub Packages

> **Note**: Make sure you're using Maven 3.6.0 or newer. Older versions may have issues with GitHub Packages.

Once you've set up the authentication, you can deploy to GitHub Packages using:

```bash
mvn deploy
```

You can check your Maven version with:

```bash
mvn --version
```

## Using the Package as a Dependency

To use this package as a dependency in another project:

1. Add the GitHub Packages repository to your project's pom.xml:

```xml
<repositories>
  <repository>
    <id>github</id>
    <url>https://maven.pkg.github.com/GithubforBen/plugins</url>
  </repository>
</repositories>
```

2. Add the dependency:

```xml
<dependency>
  <groupId>de.hems</groupId>
  <artifactId>plugins</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

## Troubleshooting

If you encounter a 401 Unauthorized error:

1. Verify that your settings.xml has the correct GitHub credentials
2. Ensure your Personal Access Token has the necessary permissions
3. Check that your token hasn't expired (GitHub PATs can expire)
4. Confirm that you have access to the repository specified in the pom.xml

If you encounter other issues:

1. Make sure you've replaced all placeholder values in both pom.xml and settings.xml
2. Verify that the repository URL in pom.xml matches your GitHub username and repository name
3. Check Maven logs for detailed error messages using `mvn deploy -X`
4. Ensure your GitHub account has the necessary permissions to create packages
5. Try clearing your local Maven repository cache: `rm -rf ~/.m2/repository/de/hems/plugins`
6. If you're behind a corporate firewall or using a proxy, add proxy settings to your settings.xml:

```xml
<proxies>
  <proxy>
    <id>optional</id>
    <active>true</active>
    <protocol>http</protocol>
    <host>proxy.company.com</host>
    <port>8080</port>
    <username>proxyuser</username>
    <password>proxypass</password>
    <nonProxyHosts>localhost|127.0.0.1</nonProxyHosts>
  </proxy>
</proxies>
```
