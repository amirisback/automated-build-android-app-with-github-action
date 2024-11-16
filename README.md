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
- Have Bundle Tool
- Download Artifact
- Upload Artifact
- Clear (Articfact naming)
- Sample Naming : ${date_today} - ${repository_name} - ${playstore_name} - APK(s) release generated
- Private Repository Tested (Passed Build App bundle(s) and APK generated successfully)
- Local Run With .run configuration in Local Machine
- Full Code For Github Action Workflows [Click Here](https://github.com/amirisback/automated-build-android-app-with-github-action/blob/master/.github/workflows/generate-apk-aab-debug-release.yml)

## Version Release
This Is Latest Release

    $version_release = 2.2.5

What's New??

    * Update Target SDK 35 *
    * Update Action Script *
    * Update Android Studio Latest Version *
    * Update Gradle Latest Version *
    * Update Kotlin Latest Version *
    * Update Java Version From 11 to 17 *
    * Update Java Action version to 3 *
    * Update Android SDK Tools * 
    * Add Bundletool.jar for workflow github action *
    * Add .run configuration *
    * Update To Libs.Version.Toml

## Article Sources
- [How To Securely Build and Sign Your Android App With GitHub Actions](https://proandroiddev.com/how-to-securely-build-and-sign-your-android-app-with-github-actions-ad5323452ce)
- [How to Use GitHub Actions to Automate Android App Development](https://www.freecodecamp.org/news/use-github-actions-to-automate-android-development/)
- [Update Java Checkout Version CI](https://github.com/actions/setup-java)

## Guide Sources (Github Action)
- [Download Artifact From Github Action](https://github.com/actions/download-artifact)
- [Upload Artifact From Github Action](https://github.com/actions/upload-artifact)
- [Remove Artifact](https://github.com/c-hive/gha-remove-artifacts)

# Run Using Github Action

## How To Use Workflows

### Step 1. Upload Your Project on Github
- Project must be android studio project using gradle

### Step 2. Create files github workflows
- Create Files with name generate-apk-aab-debug-release.yml inside folder .github/workflows/
- .github/workflows/generate-apk-aab-debug-release.yml this is position files

### Step 3. Create Code
```yml
name: Generated APK AAB (Upload - Create Artifact To Github Action)

env:
  # The name of the main module repository
  main_project_module: app

  # The name of the Play Store
  playstore_name: Frogobox ID

on:

  push:
    branches:
      - 'release/**'

  # Allows you to run this workflow manually from the Actions tab
  workflow_dispatch:

jobs:
  build:

    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v4

      # Set Current Date As Env Variable
      - name: Set current date as env variable
        run: echo "date_today=$(date +'%Y-%m-%d')" >> $GITHUB_ENV

      # Set Repository Name As Env Variable
      - name: Set repository name as env variable
        run: echo "repository_name=$(echo '${{ github.repository }}' | awk -F '/' '{print $2}')" >> $GITHUB_ENV

      - name: Set Up JDK
        uses: actions/setup-java@v4
        with:
          distribution: 'zulu' # See 'Supported distributions' for available options
          java-version: '17'
          cache: 'gradle'

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
        uses: actions/upload-artifact@v4
        with:
          name: ${{ env.date_today }} - ${{ env.playstore_name }} - ${{ env.repository_name }} - APK(s) debug generated
          path: ${{ env.main_project_module }}/build/outputs/apk/debug/

      # Noted For Output [main_project_module]/build/outputs/apk/release/
      - name: Upload APK Release - ${{ env.repository_name }}
        uses: actions/upload-artifact@v4
        with:
          name: ${{ env.date_today }} - ${{ env.playstore_name }} - ${{ env.repository_name }} - APK(s) release generated
          path: ${{ env.main_project_module }}/build/outputs/apk/release/

      # Noted For Output [main_project_module]/build/outputs/bundle/release/
      - name: Upload AAB (App Bundle) Release - ${{ env.repository_name }}
        uses: actions/upload-artifact@v4
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
        uses: c-hive/gha-remove-artifacts@v4
        with:
          age: '60 seconds' # '<number> <unit>', e.g. 5 days, 2 years, 90 seconds, parsed by Moment.js
          # Optional inputs
          # skip-tags: true
          # skip-recent: 5
```

### Using Bundle Tool

#### Step 1. Prepare Bundle Tool
- Check Bundletool on (.github/lib/bundletool.jar) [Download Latest](https://github.com/google/bundletool/releases)

#### Step 2. Code Action in file [android-ci-generate-apk-aab-upload-3.yml](https://github.com/amirisback/automated-build-android-app-with-github-action/blob/master/.github/workflows/android-ci-generate-apk-aab-upload-3.yml)

```yml
name: Generated APK AAB 2 Bundle Tool (Upload - Create Artifact To Github Action)

env:
  # The name of the main module repository
  main_project_module: app

  # The name of the Play Store
  playstore_name: Frogobox ID

  # Keystore Path
  ks_path: frogoboxdev.jks

  # Keystore Password
  ks_store_pass: cronoclez

  # Keystore Alias
  ks_alias: frogobox

  # Keystore Alias Password
  ks_alias_pass: xeonranger

on:

  push:
    branches:
      - 'release/**'

  # Allows you to run this workflow manually from the Actions tab
  workflow_dispatch:

jobs:
  build:

    runs-on: ubuntu-latest

    steps:
      - uses: actions/checkout@v4

      # Set Current Date As Env Variable
      - name: Set current date as env variable
        run: echo "date_today=$(date +'%Y-%m-%d')" >> $GITHUB_ENV

      # Set Repository Name As Env Variable
      - name: Set repository name as env variable
        run: echo "repository_name=$(echo '${{ github.repository }}' | awk -F '/' '{print $2}')" >> $GITHUB_ENV

      - name: Set Up JDK
        uses: actions/setup-java@v4
        with:
          distribution: 'zulu' # See 'Supported distributions' for available options
          java-version: '17'
          cache: 'gradle'

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

      # - name: Build APK(s) Debug from bundle using bundletool
      #   run: java -jar ".github/lib/bundletool.jar" build-apks --bundle=${{ env.main_project_module }}/build/outputs/bundle/debug/${{ env.artifact_name }}-debug.aab --output=${{ env.main_project_module }}/build/outputs/bundle/debug/${{ env.artifact_name }}-debug.apks --mode=universal

      - name: Set Env Artifact name from generated aab
        run: |
          cd ${{ env.main_project_module }}/build/outputs/bundle/release/
          files=(*)
          echo "generated_name_aab=${files[0]%.*}" >> $GITHUB_ENV

      # Build APK From Bundle Using Bundletool
      # Noted For Output [main_project_module]/build/outputs/bundle/release/
      - name: Build APK(s) Release from bundle using bundletool (Path same with bundle output)
        run: java -jar ".github/lib/bundletool.jar" build-apks --bundle=${{ env.main_project_module }}/build/outputs/bundle/release/${{ env.generated_name_aab }}.aab --output=${{ env.main_project_module }}/build/outputs/bundle/release/${{ env.generated_name_aab }}.apks --mode=universal --ks="app/${{ env.ks_path }}" --ks-pass=pass:${{ env.ks_store_pass }} --ks-key-alias=${{ env.ks_alias }} --key-pass=pass:${{ env.ks_alias_pass }}

      # Duplicate APK(s) Release to zip file and extract
      - name: Duplicate APK(s) Release to zip file and extract
        run: |
          cd ${{ env.main_project_module }}/build/outputs/bundle/release/
          unzip -p ${{ env.generated_name_aab }}.apks universal.apk > ${{ env.generated_name_aab }}.apk

      # Upload Artifact Build
      # Noted For Output [main_project_module]/build/outputs/apk/debug/
      - name: Upload APK Debug - ${{ env.repository_name }}
        uses: actions/upload-artifact@v4
        with:
          name: ${{ env.date_today }} - ${{ env.playstore_name }} - ${{ env.repository_name }} - APK(s) debug generated
          path: ${{ env.main_project_module }}/build/outputs/apk/debug/

      # Noted For Output [main_project_module]/build/outputs/apk/release/
      - name: Upload APK Release - ${{ env.repository_name }}
        uses: actions/upload-artifact@v4
        with:
          name: ${{ env.date_today }} - ${{ env.playstore_name }} - ${{ env.repository_name }} - APK(s) release generated
          path: ${{ env.main_project_module }}/build/outputs/apk/release/

      # Noted For Output [main_project_module]/build/outputs/bundle/release/
      - name: Upload AAB (App Bundle) Release - ${{ env.repository_name }}
        uses: actions/upload-artifact@v4
        with:
          name: ${{ env.date_today }} - ${{ env.playstore_name }} - ${{ env.repository_name }} - App bundle(s) AAB release generated
          path: ${{ env.main_project_module }}/build/outputs/bundle/release/
```

### Step 3. Running Action
![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/bundletool/ss_bundle_1.png?raw=true)

### Step 4. Waiting Running Action
#### Waiting for running action
![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/bundletool/ss_bundle_2.png?raw=true)
#### Check periodically, afraid there is an error
![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/bundletool/ss_bundle_3.png?raw=true)


### Step 5. Download Artifact and Extract it
#### Download Artifact (AAB Artifact)
![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/bundletool/ss_bundle_4.png?raw=true)
#### Extract it !!! Done
![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/bundletool/ss_bundle_5.png?raw=true)

# Run Using Gradle Configuration

## .run Configuration (Alternative if you don't have github action)
- Run on your local machine
  ![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/ss-configuration-01.png?raw=true)

### Step 1: Create Folder .run on Root Project Directory
![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/ss-configuration-02.png?raw=true)

### Step 2: Create File [name-config].run.xml
```xml
<component name="ProjectRunConfigurationManager">
  <!-- Add Name Configuration Here -->
  <configuration default="false" name="${your-config-name}" type="GradleRunConfiguration" factoryName="Gradle">
    <ExternalSystemSettings>
      <option name="executionName" />
      <option name="externalProjectPath" value="$PROJECT_DIR$" />
      <option name="externalSystemIdString" value="GRADLE" />
      <option name="scriptParameters" value="" />
      <option name="taskDescriptions">
        <list />
      </option>
      <option name="taskNames">
        <list>
          <!-- TODO : add your task here -->
          <option value=":app:assembleDebug" />
        </list>
      </option>
      <option name="vmOptions" />
    </ExternalSystemSettings>
    <ExternalSystemDebugServerProcess>true</ExternalSystemDebugServerProcess>
    <ExternalSystemReattachDebugProcess>true</ExternalSystemReattachDebugProcess>
    <DebugAllEnabled>false</DebugAllEnabled>
    <RunAsTest>false</RunAsTest>
    <method v="2" />
  </configuration>
</component>
```
- Note : if you confuse you can use this feature
  ![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/ss-configuration-03.png?raw=true)

### Step 3: Your Configuration Will Appears on this Menu
![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/ss-configuration-04.png?raw=true)

### Step 4: Result run multiple task
![ScreenShot](https://raw.githubusercontent.com/amirisback/automated-build-android-app-with-github-action/master/docs/image/ss-configuration-05.png?raw=true)

### Sample Configuration (signingreport)
```xml
<component name="ProjectRunConfigurationManager">
  <configuration default="false" name="signingreport" type="GradleRunConfiguration" factoryName="Gradle">
    <ExternalSystemSettings>
      <option name="executionName" />
      <option name="externalProjectPath" value="$PROJECT_DIR$" />
      <option name="externalSystemIdString" value="GRADLE" />
      <option name="scriptParameters" value="" />
      <option name="taskDescriptions">
        <list />
      </option>
      <option name="taskNames">
        <list>
          <option value="signingreport" />
        </list>
      </option>
      <option name="vmOptions" />
    </ExternalSystemSettings>
    <ExternalSystemDebugServerProcess>true</ExternalSystemDebugServerProcess>
    <ExternalSystemReattachDebugProcess>true</ExternalSystemReattachDebugProcess>
    <DebugAllEnabled>false</DebugAllEnabled>
    <RunAsTest>false</RunAsTest>
    <method v="2" />
  </configuration>
</component>
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