import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = AndroidConfig.Namespace
    compileSdk = AndroidConfig.CompileSdk
    buildToolsVersion = AndroidConfig.BuildTools
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AndroidConfig.KotlinCompilerExtensionVersion
    }
    defaultConfig {
        val versionProperties = readProperties(file("../version.properties"))
        applicationId = AndroidConfig.Namespace
        minSdkPreview = AndroidConfig.MinSdk
        targetSdkPreview = AndroidConfig.TargetSdk
        versionCode =
            versionProperties.getProperty("VERSION_CODE").toInt()
        versionName = AndroidConfig.VersionName
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
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
    implementation(Libraries.coreKtx)
    implementation(Libraries.appcompat)
    implementation(Libraries.lifecycle)
    implementation(Libraries.coil)
    implementation(Libraries.navigationCompose)
    implementation(platform(Libraries.composeBom))
    implementation(Libraries.composeActivity)
    implementation(Libraries.composeRuntimeLiveData)
    implementation(Libraries.composeUi)
    implementation(Libraries.composeMaterial3)
    implementation(Libraries.composeUiToolingPreview)
    debugImplementation(Libraries.composeUiTooling)
    testImplementation(Libraries.junit)
}

fun readProperties(propertiesFile: File) = Properties().apply {
    propertiesFile.inputStream().use { load(it) }
}

task("getVersionName") {
    println(AndroidConfig.VersionName)
}
