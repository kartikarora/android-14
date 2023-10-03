import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version Versions.gradlePlugin apply false
    id("org.jetbrains.kotlin.android") version Versions.kotlin apply false
    id("com.github.ben-manes.versions") version Versions.gradleVersionsPlugin
}

tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
    // optional parameters
    checkForGradleUpdate = true
    outputFormatter = "html"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
}