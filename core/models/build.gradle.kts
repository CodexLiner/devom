plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinx.serialization)
}

group = "com.devom.core.models"

kotlin {
    androidTarget()
    jvmToolchain(11)
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {

        }
        androidMain.dependencies {

        }
        iosMain.dependencies {

        }

    }
}

android {
    namespace = "com.devom.core"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
    }
}