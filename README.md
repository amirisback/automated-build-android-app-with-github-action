## Automated Build Android With Using Github Action
[![Generate APK / AAB Debug And Release](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/generate-apk-aab-debug-release.yml/badge.svg)](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/generate-apk-aab-debug-release.yml)
[![Scan with Detekt](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/detekt-analysis.yml/badge.svg)](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/detekt-analysis.yml)
[![pages-build-deployment](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/pages/pages-build-deployment/badge.svg)](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/pages/pages-build-deployment)
- Project Github Action Script
- Using Github Workflows
- Private Repository Tested (Passed Build App bundle(s) and APK generated successfully)
- Full Code For Github Action Workflows [Click Here](https://github.com/amirisback/automated-build-android-app-with-github-action/blob/master/.github/workflows/generate-apk-aab-debug-release.yml)

## Article Sources
- [How To Securely Build and Sign Your Android App With GitHub Actions](https://proandroiddev.com/how-to-securely-build-and-sign-your-android-app-with-github-actions-ad5323452ce)
- [How to Use GitHub Actions to Automate Android App Development](https://www.freecodecamp.org/news/use-github-actions-to-automate-android-development/)

## How To Use Workflows

<p align="center">
<img width="100px" height="100px" src="https://raw.githubusercontent.com/amirisback/amirisback/master/docs/image/bear-panda/panda-annoy.gif">
<img width="100px" height="100px" src="https://raw.githubusercontent.com/amirisback/amirisback/master/docs/image/bear-panda/bear-annoy.gif">
</p>

### Step 1. Upload Your Project on Github
- Project must be android studio project using gradle

### Step 2. Create files github workflows
- Create Files with name generate-apk-aab-debug-release.yml inside folder .github/workflows/
- .github/workflows/generate-apk-aab-debug-release.yml this is position files

### Step 3. Create Code
```yml
name: Generate APK / AAB Debug And Release

on:
  # Triggers the workflow on push or pull request events but only for default and protected branches
  push:
    branches: [ master ]
  pull_request:
    branches: [ master ]

jobs:
  build:

    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v1

      - name: Set Up JDK
        uses: actions/setup-java@v1
        with:
          java-version: 11

      - name: Change wrapper permissions
        run: chmod +x ./gradlew

      - name: Run tests
        run: ./gradlew test

      # Run Build Project
      - name: Build project
        run: ./gradlew build

      # Create APK Debug
      - name: Build apk debug project (APK)
        run: ./gradlew assembleDebug

      # Create APK Release
      - name: Build apk release project (APK)
        run: ./gradlew assemble

      # Create Bundle AAB Release
      # Noted for main module build [module-name]:bundleRelease
      - name: Build app bundle release (AAB)
        run: ./gradlew app:bundleRelease

      # Upload Artifact Build
      # Noted For Output [module-name]/build/outputs/
      - name: Upload debug build APK
        uses: actions/upload-artifact@v2
        with:
          name: App bundle(s) and APK(s) generated
          path: app/build/outputs/
```

### Step 4. Automated Build on Actions tab on your github repository
![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/ss-01.png?raw=true)

### Step 5. Download Artifact
![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/ss-02.png?raw=true)

### Extras (Private Repository Succesfully Build *Proven*)
![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/ss-private-repo.png?raw=true)

## Result Generated from Github Action

### APK Debug
![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/ss-apk-debug.png?raw=true)

### APK Release
![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/ss-apk-release.png?raw=true)

### AAB App Bundle
![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/ss-bundle.png?raw=true)

## Colaborator
Very open to anyone, I'll write your name under this, please contribute by sending an email to me

- Mail To faisalamircs@gmail.com
- Subject : Github _ [Github-Username-Account] _ [Language] _ [Repository-Name]
- Example : Github_amirisback_kotlin_admob-helper-implementation

Name Of Contribute
- Muhammad Faisal Amir
- Waiting List
- Waiting List

Waiting for your contribute

## Attention !!!
- Please enjoy and don't forget fork and give a star
- Don't Forget Follow My Github Account

![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/mad_score.png?raw=true)
