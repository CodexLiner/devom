plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
}

group = "com.devom.core.utils"

kotlin {
    androidTarget()
    jvmToolchain(11)
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)
        }
        androidMain.dependencies {
            implementation(libs.androidx.startup.runtime)
            implementation("androidx.core:core-ktx:1.16.0")
        }
        iosMain.dependencies {

        }

    }
}

android {
    namespace = "com.devom.core.utils"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
    }
}