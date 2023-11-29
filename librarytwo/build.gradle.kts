plugins {
    alias(libs.plugins.convention.androidLibrary)
}

android {
    namespace = libs.versions.libraryTwoNamespace.get()
}