plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = AndroidConfig.Namespace
    compileSdkPreview = AndroidConfig.CompileSdk
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = AndroidConfig.KotlinCompilerExtensionVersion
    }
    defaultConfig {
        applicationId = AndroidConfig.Namespace
        minSdkVersion(AndroidConfig.MinSdk)
        targetSdkVersion(AndroidConfig.TargetSdk)
        versionCode = AndroidConfig.VersionCode
        versionName = AndroidConfig.VersionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
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
