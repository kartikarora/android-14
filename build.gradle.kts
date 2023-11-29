import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(libs.plugins.android.gradlePlugin.get().pluginId) apply false
    id(libs.plugins.kotlin.get().pluginId) apply false
    alias(libs.plugins.gradleVersionsPlugin)
    id(libs.plugins.androidLibrary.get().pluginId) apply false
}

tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
    // optional parameters
    checkForGradleUpdate = true
    outputFormatter = "html"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
}