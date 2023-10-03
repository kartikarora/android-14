import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.3.0-alpha17" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20-Beta2" apply false
    id("com.github.ben-manes.versions") version "0.48.0"
}

tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
    // optional parameters
    checkForGradleUpdate = true
    outputFormatter = "html"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
}