object Libraries {
    // Kotlin
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    // Core
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val junit = "junit:junit:${Versions.junit}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"

    const val composeActivity = "androidx.activity:activity-compose:${Versions.activity}"

    // Compose
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBOM}"
    const val composeRuntimeLiveData =
        "androidx.compose.runtime:runtime-livedata"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeMaterial3 = "androidx.compose.material3:material3"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.composeUiTooling}"
    const val navigationCompose =
        "androidx.navigation:navigation-compose:${Versions.navigationCompose}"

    // coil
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
}