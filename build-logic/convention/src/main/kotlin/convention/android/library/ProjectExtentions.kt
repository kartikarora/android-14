package convention.android.library

import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.configureKotlinAndroid(libraryExtension: LibraryExtension) {
    libraryExtension.apply {
        compileSdk = libs.findVersion("compileSdk").get().requiredVersion.toInt()

        defaultConfig {
            minSdk = libs.findVersion("minSdk").get().requiredVersion.toInt()
            consumerProguardFiles("consumer-rules.pro")
        }

        buildTypes {
            getByName("release"){
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }

    dependencies {
        add("implementation", libs.findLibrary("androidx.coreKtx").get())
        add("implementation", libs.findLibrary("androidx.appcompat").get())
        add("implementation", libs.findLibrary("material").get())
        add("testImplementation", libs.findLibrary("junit").get())
    }
}