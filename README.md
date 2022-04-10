<p align="center">
<!-- Walk Together -->
<img width="180px" height="180px" src="https://raw.githubusercontent.com/amirisback/amirisback/master/docs/image/bear-panda/couple/bear-panda-walk-together.gif">
</p>

## Automated Build Android With Using Github Action
[![Android CI](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/generate-apk-aab-debug-release.yml/badge.svg)](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/generate-apk-aab-debug-release.yml)
[![Scan with Detekt](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/detekt-analysis.yml/badge.svg)](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/detekt-analysis.yml)
[![pages-build-deployment](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/pages/pages-build-deployment/badge.svg)](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/pages/pages-build-deployment)
- Project Github Action Script
- Using Github Workflows
- Private Repository Tested (Passed Build App bundle(s) and APK generated successfully)
- Full Code For Github Action Workflows [Click Here](https://github.com/amirisback/automated-build-android-app-with-github-action/blob/master/.github/workflows/generate-apk-aab-debug-release.yml)

## Version Release
This Is Latest Release

    $version_release = 2.0.0

What's New??

    * Update Action Script *
    * Update Android Studio Latest Version *

## Article Sources
- [How To Securely Build and Sign Your Android App With GitHub Actions](https://proandroiddev.com/how-to-securely-build-and-sign-your-android-app-with-github-actions-ad5323452ce)
- [How to Use GitHub Actions to Automate Android App Development](https://www.freecodecamp.org/news/use-github-actions-to-automate-android-development/)

## How To Use Workflows

### Step 1. Upload Your Project on Github
- Project must be android studio project using gradle

### Step 2. Create files github workflows
- Create Files with name generate-apk-aab-debug-release.yml inside folder .github/workflows/
- .github/workflows/generate-apk-aab-debug-release.yml this is position files

### Step 3. Create Code
```yml
name: Android CI

env:
  # The name of the main module repository
  main_project_module: app

  # The name of the Play Store
  playstore_name: Frogobox ID

on:
  # Triggers the workflow on push or pull request events but only for default and protected branches
  push:
    branches: [ master ]
  pull_request:
    branches: [ master ]

  workflow_dispatch:
    # The workflow will be dispatched to the default queue
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
      - name: Build apk debug project (APK) Module >> ${{ env.main_project_module }}
        run: ./gradlew assembleDebug

      # Create APK Release
      - name: Build apk release project (APK) Module >> ${{ env.main_project_module }}
        run: ./gradlew assemble

      # Create Bundle AAB Release
      # Noted for main module build [MAIN-APP-MODULE]:bundleRelease
      - name: Build app bundle release (AAB) Module >> ${{ env.main_project_module }}
        run: ./gradlew ${{ env.main_project_module }}:bundleRelease

      # Upload Artifact Build
      # Noted For Output [MAIN-APP-MODULE]/build/outputs/
      - name: Upload APK Debug ${{ env.playstore_name }}
        uses: actions/upload-artifact@v2
        with:
          name: ${{ github.repository }} | APK(s) debug generated ${{ env.playstore_name }}
          path: ${{ env.main_project_module }}/build/outputs/apk/debug/

      # Noted For Output [MAIN-APP-MODULE]/build/outputs/
      - name: Upload APK Release ${{ env.playstore_name }}
        uses: actions/upload-artifact@v2
        with:
          name: ${{ github.repository }} | APK(s) release generated ${{ env.playstore_name }}
          path: ${{ env.main_project_module }}/build/outputs/apk/release/

      # Noted For Output [MAIN-APP-MODULE]/build/outputs/
      - name: Upload AAB (App Bundle) Release ${{ env.playstore_name }}
        uses: actions/upload-artifact@v2
        with:
          name: ${{ github.repository }} | App bundle(s) release generated ${{ env.playstore_name }}
          path: ${{ env.main_project_module }}/build/outputs/bundle/release/
```

### Step 4. Automated Build on Actions tab on your github repository
![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/ss-01.png?raw=true)

### Step 5. Download Artifact
![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/ss-02.png?raw=true)

### Extras (Private Repository Succesfully Build *Proven*)
![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/ss-private-repo.png?raw=true)

## Result Generated from Github Action

### APK(s) debug generated
![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/ss-apk-debug.png?raw=true)

### APK(s) release generated
![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/ss-apk-release.png?raw=true)

### App bundle(s) release generated
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