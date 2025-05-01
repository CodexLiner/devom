plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
}

group = "com.devom.domain"

kotlin {
    jvmToolchain(11)
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(project(":data:repository"))
            implementation(project(":core:models"))

        }
        androidMain.dependencies {

        }
        iosMain.dependencies {

        }

    }
}

android {
    namespace = "com.devom.domain"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
    }
}