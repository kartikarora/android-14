plugins {
    id(libs.plugins.precompiled.androidLibrary.get().pluginId)
}

android {
    namespace = libs.versions.libraryOneNamespace.get()
}