plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = ProjectSetting.PROJECT_COMPILE_SDK
    namespace = ProjectSetting.PROJECT_NAME_SPACE

    defaultConfig {

        applicationId = ProjectSetting.PROJECT_APP_ID
        minSdk = ProjectSetting.PROJECT_MIN_SDK
        targetSdk = ProjectSetting.PROJECT_TARGET_SDK
        versionCode = ProjectSetting.PROJECT_VERSION_CODE
        versionName = ProjectSetting.PROJECT_VERSION_NAME

        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Naming APK // AAB
        setProperty("archivesBaseName", "${ProjectSetting.NAME_APK}[${versionName}]")

    }

    signingConfigs {
        create("release") {
            // You need to specify either an absolute path or include the
            // keystore file in the same directory as the build.gradle file.
            // [PROJECT FOLDER NAME/app/[COPY YOUT KEY STORE] .jks in here
            storeFile = file(ProjectSetting.KEY_PATH)
            storePassword = ProjectSetting.KEY_STORE_PASSWORD
            keyAlias = ProjectSetting.KEY_ALIAS
            keyPassword = ProjectSetting.KEY_ALIAS_PASSWORD
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false

            // Disable Debug Mode
            isDebuggable = false
            isJniDebuggable = false
            isPseudoLocalesEnabled = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            // Generated Signed APK / AAB
            signingConfig = signingConfigs.getByName("release")

        }
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.material)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}