plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinx.serialization)
}

group = "com.devom.network"

kotlin {
    jvmToolchain(11)
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            api(project(":core:utils"))
            api(libs.kotlinx.coroutines.core)
            api(libs.ktor.client.core)
            api(libs.ktor.client.content.negotiation)
            api(libs.ktor.client.serialization)
            api(libs.ktor.client.logging)
            api(libs.coil.network.ktor)
            api(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kermit)
            implementation(libs.multiplatformSettings)
            implementation(libs.multiplatform.settings.no.arg)

        }
        androidMain.dependencies {
            api(libs.ktor.client.okhttp)
        }
        iosMain.dependencies {
            api(libs.ktor.client.darwin)
        }

    }
}

android {
    namespace = "com.devom.network"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
    }
}