plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.sqlDelight)

}

sqldelight {
    databases {
        create("LocalCacheDatabase") {
            packageName.set("com.devom")
            srcDirs("src/commonMain/kotlin/sqldelight")
        }
    }
}

group = "com.devom.data.cache"

kotlin {
    androidTarget()
    jvmToolchain(11)
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            api(libs.ktor.serialization.kotlinx.json)
            implementation(project(":core:models"))
            implementation(project(":core:utils"))
            implementation(libs.coroutines.extensions)
        }
        androidMain.dependencies {
            implementation(libs.sqlDelight.driver.android)
            implementation(libs.androidx.startup.runtime)
        }
        iosMain.dependencies {
            implementation(libs.sqlDelight.driver.native)
        }


    }
}

android {
    namespace = "com.devom.data.cache"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
    }
}