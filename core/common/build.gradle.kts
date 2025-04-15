plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
}

android {
    namespace = "com.fcerio.core.common"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    // https://github.com/Kotlin/kotlinx.coroutines?tab=readme-ov-file
    packaging {
        resources {
            excludes += "DebugProbesKt.bin"
            excludes +="META-INF/INDEX.LIST"
        }
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    api(libs.timber)
    api(libs.bundles.coroutines)
}