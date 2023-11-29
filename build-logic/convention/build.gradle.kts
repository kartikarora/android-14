plugins {
    `kotlin-dsl`
}

group = libs.versions.libraryPluginGroup.get()

kotlin {
    jvmToolchain(17)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        plugins {
            register("conventionAndroidLibrary") {
                id = libs.versions.conventionAndroidLibraryId.get()
                implementationClass = "AndroidLibraryConventionPlugin"
            }
        }
    }
}