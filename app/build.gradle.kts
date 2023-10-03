import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

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
    namespace = "me.kartikarora.android14"
    compileSdk = 34
    buildToolsVersion = "34.0.0"
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4-dev-k1.9.20-Beta2-ac5f960bdaf"
    }
    defaultConfig {
        val versionProperties = readProperties(file("../version.properties"))
        minSdk = 34
        targetSdk = 34
        versionCode =
            versionProperties.getProperty("VERSION_CODE").toInt()
        versionName = "0.2.2"
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
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.20-Beta2")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.7.0-alpha03")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0-alpha02")
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("androidx.navigation:navigation-compose:2.7.3")
    implementation(platform("androidx.compose:compose-bom:2023.09.02"))
    implementation("androidx.activity:activity-compose:1.8.0-rc01")
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.0-alpha06")
    debugImplementation("androidx.compose.ui:ui-tooling")
    testImplementation("junit:junit:4.13.2")
}

fun readProperties(propertiesFile: File) = Properties().apply {
    propertiesFile.inputStream().use { load(it) }
}

task("getVersionName") {
    println("0.2.2")
}
