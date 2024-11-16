/**
 * Created by faisalamir on 19/09/21
 * FrogoRecyclerView
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 * Copyright (C) 2021 FrogoBox Inc.
 * All rights reserved
 *
 */

object ProjectSetting {

    // ---------------------------------------------------------------------------------------------

    // Declaration app name
    const val NAME_APP = "Github Action Automated"

    const val APP_DOMAIN = "com"
    const val APP_PLAY_CONSOLE = "frogobox"
    const val APP_NAME = "githubaction"

    const val VERSION_MAJOR = 2
    const val VERSION_MINOR = 2
    const val VERSION_PATCH = 5

    const val PROJECT_MIN_SDK = 24
    const val PROJECT_COMPILE_SDK = 35

    // Setup Publish Setting
    const val KEY_PATH = "frogoboxdev.jks"
    const val KEY_STORE_PASSWORD = "cronoclez"
    const val KEY_ALIAS = "frogobox"
    const val KEY_ALIAS_PASSWORD = "xeonranger"

    // ---------------------------------------------------------------------------------------------

    // Constant Setup (Do not change this)

    const val PROJECT_TARGET_SDK = PROJECT_COMPILE_SDK

    const val PROJECT_APP_ID = "$APP_DOMAIN.$APP_PLAY_CONSOLE.$APP_NAME"
    const val PROJECT_NAME_SPACE = "com.frogobox.githubaction"
    const val PROJECT_VERSION_CODE = (VERSION_MAJOR * 100) + (VERSION_MINOR * 10) + (VERSION_PATCH * 1)
    const val PROJECT_VERSION_NAME = "$VERSION_MAJOR.$VERSION_MINOR.$VERSION_PATCH"

    // Declaration apk / aab name
    val NAME_APK = NAME_APP.lowercase().replace(" ", "-")
    val NAME_DB = NAME_APP.lowercase().replace(" ", "_")
    val DB = "\"$NAME_DB.db\""

}