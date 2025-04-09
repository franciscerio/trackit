plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp.library)
    alias(libs.plugins.hilt)
    alias(libs.plugins.room.compiler)
    kotlin("plugin.serialization") version libs.versions.kotlin
}

android {
    namespace = "com.fcerio.core.local"
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {
    api(project(":core:domain"))
    api(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    // EncryptedSharedPreferences
    api(libs.security.crypto)

    ksp(libs.hilt.compiler)

    api(libs.hilt.android)
    // Room
    api(libs.room.runtime)

    implementation(libs.kotlinx.serialization.json)

    // SQLCipher
    api(libs.sql.cipher)
    ksp(libs.room.compiler)
    api(libs.room.ktx)
    api(libs.moshi)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}