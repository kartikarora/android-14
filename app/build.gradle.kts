plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = AndroidConfig.Namespace
    compileSdkPreview = AndroidConfig.CompileSdk
    buildToolsVersion = AndroidConfig.BuildTools
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AndroidConfig.KotlinCompilerExtensionVersion
    }
    defaultConfig {
        applicationId = AndroidConfig.Namespace
        minSdkPreview = AndroidConfig.MinSdk
        targetSdkPreview = AndroidConfig.TargetSdk
        versionCode = AndroidConfig.VersionCode
        versionName = AndroidConfig.VersionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        resourceConfigurations += listOf("en_AU", "fr_FR", "es_ES")

    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    implementation(Libraries.composeActivity)
    implementation(Libraries.lifecycle)
    implementation(Libraries.composeRuntimeLiveData)
    implementation(Libraries.composeUi)
    implementation(Libraries.composeMaterial3)
    implementation(Libraries.composeUiToolingPreview)
    implementation(Libraries.navigationCompose)
    implementation(Libraries.coil)
    debugImplementation(Libraries.composeUiTooling)
    testImplementation(Libraries.junit)
    implementation(platform(Libraries.composeBom))
}
