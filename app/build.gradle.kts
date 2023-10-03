import java.util.Properties

plugins {
    alias(libs.plugins.android.gradlePlugin)
    alias(libs.plugins.kotlin)

}

kotlin {
    jvmToolchain(17)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

android {
    namespace = libs.versions.namespace.get()
    compileSdk = libs.versions.compileSdk.get().toInt()
    buildToolsVersion = libs.versions.buildTools.get()
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerExtensionVersion.get()
    }
    defaultConfig {
        val versionProperties = readProperties(file("../version.properties"))
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode =
            versionProperties.getProperty("VERSION_CODE").toInt()
        versionName = libs.versions.versionName.get()
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

    }
    signingConfigs {
        create("release") {
            val secretProperties = readProperties(file("../secret.properties"))
            storeFile = file(secretProperties.getProperty("SIGNING_KEYSTORE_PATH"))
            storePassword = secretProperties.getProperty("SIGNING_STORE_PASSWORD")
            keyAlias = secretProperties.getProperty("SIGNING_KEY_ALIAS")
            keyPassword = secretProperties.getProperty("SIGNING_KEY_PASSWORD")
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    androidResources {
        generateLocaleConfig = true
    }
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.coreKtx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle)
    implementation(libs.coil)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.androidx.compose)
    debugImplementation(libs.androidx.compose.uiTooling)
    testImplementation(libs.junit)
}

fun readProperties(propertiesFile: File) = Properties().apply {
    propertiesFile.inputStream().use { load(it) }
}

task("getVersionName") {
    println(libs.versions.versionName.get())
}
