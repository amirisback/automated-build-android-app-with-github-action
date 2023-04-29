<!-- <p align="center"> -->
<!-- Run -->
<!-- <img width="330px" height="200px" src="https://raw.githubusercontent.com/amirisback/amirisback/master/docs/image/bear-panda/couple/bear-panda-run.gif"> -->
<!-- </p> -->


## Automated Build Android Using Github Action
[![Android CI](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/android-ci.yml/badge.svg)](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/android-ci.yml)
[![Download Generated APK AAB](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/android-ci-generate-apk-aab-download.yml/badge.svg)](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/android-ci-generate-apk-aab-download.yml)
[![Upload Generated APK AAB](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/android-ci-generate-apk-aab-upload.yml/badge.svg)](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/android-ci-generate-apk-aab-upload.yml)
[![Scan with Detekt](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/detekt-analysis.yml/badge.svg)](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/detekt-analysis.yml)
[![pages-build-deployment](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/pages/pages-build-deployment/badge.svg)](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/pages/pages-build-deployment)
[![Generated APK AAB (Clean)](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/android-ci-generate-apk-aab-clean.yml/badge.svg)](https://github.com/amirisback/automated-build-android-app-with-github-action/actions/workflows/android-ci-generate-apk-aab-clean.yml)
- Available on Google Dev Library [Click Here](https://devlibrary.withgoogle.com/products/android/repos/amirisback-automated-build-android-app-with-github-action)
- Project Github Action Script YAML
- Using Github Workflows
- Automated Build AAB (release)
- Automated Build APK (release and debug)
- Download Artifact
- Upload Artifact
- Clear (Articfact naming)
- Sample Naming : ${date_today} - ${repository_name} - ${playstore_name} - APK(s) release generated
- Private Repository Tested (Passed Build App bundle(s) and APK generated successfully)
- Full Code For Github Action Workflows [Click Here](https://github.com/amirisback/automated-build-android-app-with-github-action/blob/master/.github/workflows/generate-apk-aab-debug-release.yml)

## Version Release
This Is Latest Release

    $version_release = 2.2.0

What's New??

    * Update Action Script *
    * Update Android Studio Latest Version *
    * Update Gradle Latest Version *
    * Update Kotlin Latest Version *
    * Update Java Version From 11 to 17 *

## Article Sources
- [How To Securely Build and Sign Your Android App With GitHub Actions](https://proandroiddev.com/how-to-securely-build-and-sign-your-android-app-with-github-actions-ad5323452ce)
- [How to Use GitHub Actions to Automate Android App Development](https://www.freecodecamp.org/news/use-github-actions-to-automate-android-development/)

## Guide Sources (Github Action)
- [Download Artifact From Github Action](https://github.com/actions/download-artifact)
- [Upload Artifact From Github Action](https://github.com/actions/upload-artifact)
- [Remove Artifact](https://github.com/c-hive/gha-remove-artifacts)

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

  # Allows you to run this workflow manually from the Actions tab
  workflow_dispatch:

jobs:
  build:

    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v1

      # Set Current Date As Env Variable
      - name: Set current date as env variable
        run: echo "date_today=$(date +'%Y-%m-%d')" >> $GITHUB_ENV

      # Set Repository Name As Env Variable
      - name: Set repository name as env variable
        run: echo "repository_name=$(echo '${{ github.repository }}' | awk -F '/' '{print $2}')" >> $GITHUB_ENV

      - name: Set Up JDK
        uses: actions/setup-java@v1
        with:
          java-version: 17

      - name: Change wrapper permissions
        run: chmod +x ./gradlew

      # Run Tests Build
      - name: Run gradle tests
        run: ./gradlew test

      # Run Build Project
      - name: Build gradle project
        run: ./gradlew build

      # Create APK Debug
      - name: Build apk debug project (APK) - ${{ env.main_project_module }} module
        run: ./gradlew assembleDebug

      # Create APK Release
      - name: Build apk release project (APK) - ${{ env.main_project_module }} module
        run: ./gradlew assemble

      # Create Bundle AAB Release
      # Noted for main module build [main_project_module]:bundleRelease
      - name: Build app bundle release (AAB) - ${{ env.main_project_module }} module
        run: ./gradlew ${{ env.main_project_module }}:bundleRelease

      # Upload Artifact Build
      # Noted For Output [main_project_module]/build/outputs/apk/debug/
      - name: Upload APK Debug - ${{ env.repository_name }}
        uses: actions/upload-artifact@v2
        with:
          name: ${{ env.date_today }} - ${{ env.playstore_name }} - ${{ env.repository_name }} - APK(s) debug generated
          path: ${{ env.main_project_module }}/build/outputs/apk/debug/

      # Noted For Output [main_project_module]/build/outputs/apk/release/
      - name: Upload APK Release - ${{ env.repository_name }}
        uses: actions/upload-artifact@v2
        with:
          name: ${{ env.date_today }} - ${{ env.playstore_name }} - ${{ env.repository_name }} - APK(s) release generated
          path: ${{ env.main_project_module }}/build/outputs/apk/release/

      # Noted For Output [main_project_module]/build/outputs/bundle/release/
      - name: Upload AAB (App Bundle) Release - ${{ env.repository_name }}
        uses: actions/upload-artifact@v2
        with:
          name: ${{ env.date_today }} - ${{ env.playstore_name }} - ${{ env.repository_name }} - App bundle(s) AAB release generated
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

### Clean Up Artifact
```yml
name: Generated APK AAB (Clean)

on:
  # Allows you to run this workflow manually from the Actions tab
  workflow_dispatch:

  schedule:
    # Every day at 1am
    - cron: '0 1 * * *'

jobs:
  remove-old-artifacts:
    runs-on: ubuntu-latest
    timeout-minutes: 10

    steps:
      - name: Clean all artifacts
        uses: c-hive/gha-remove-artifacts@v1
        with:
          age: '60 seconds' # '<number> <unit>', e.g. 5 days, 2 years, 90 seconds, parsed by Moment.js
          # Optional inputs
          # skip-tags: true
          # skip-recent: 5
```

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
