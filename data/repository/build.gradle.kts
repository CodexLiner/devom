plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
}

group = "com.devom.data.repository"

kotlin {
    androidTarget()
    jvmToolchain(11)
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":core:models"))
            implementation(project(":core:utils"))
            implementation(project(":data:cache"))
            implementation(project(":data:server"))

            implementation(libs.kotlinx.coroutines.core)
        }
        androidMain.dependencies {

        }
        iosMain.dependencies {

        }

    }
}

android {
    namespace = "com.devom.data.repository"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
    }
}