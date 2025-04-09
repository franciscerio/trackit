plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp.library)
    alias(libs.plugins.hilt)
    kotlin("plugin.serialization") version libs.versions.kotlin
}

apply(from = "$rootDir/secret.gradle.kts")

val stagingApi: List<Pair<String, String>> by project.extra
val releaseApi: List<Pair<String, String>> by project.extra

android {
    namespace = "com.fcerio.core.network"
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
            releaseApi.forEach { buildConfigField("String", it.first, "\"${it.second}\"") }
        }
        debug {
            stagingApi.forEach { buildConfigField("String", it.first, "\"${it.second}\"") }
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
}

dependencies {
    api(libs.timber)
    api(project(":core:domain"))
    api(libs.moshi)
    api(libs.moshi.kotlin)
    api(libs.kotlin.reflect)
    // Retrofit
    ksp(libs.moshi.codegen)
    api(libs.retrofit)
    api(libs.retrofit.moshi)
    api(libs.retrofit.scalars)

    // OkHttp
    api(libs.okhttp)
    api(libs.okhttp.logging)

    // Dagger-Hilt
    ksp(libs.hilt.compiler)
    api(libs.hilt.android)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Chucker
    debugApi(libs.chucker)
    releaseApi(libs.chucker.noop)

    // Testing
    testImplementation(testLibs.junit)
    testImplementation(testLibs.mockk)
    testImplementation(testLibs.mockk.jvm)
    testImplementation(testLibs.google.truth)
    testImplementation(testLibs.coroutines.test)
    androidTestImplementation(testLibs.androidx.test.runner)
    androidTestImplementation(testLibs.androidx.espresso)
    androidTestImplementation(testLibs.coroutines.test)
}