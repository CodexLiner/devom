plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
}

group = "com.devom.core.utils"

kotlin {
    jvm()
    jvmToolchain(17)
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
            }
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