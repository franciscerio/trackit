plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp.library)
    alias(libs.plugins.hilt)
    kotlin("plugin.serialization")
}

apply(from = "$rootDir/secret.gradle.kts")

val stagingApi: List<Pair<String, String>> by project.extra
val releaseApi: List<Pair<String, String>> by project.extra

android {
    namespace = "com.fcerio.features.tracks.data"
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
    implementation(projects.core.common)
    api(projects.core.domain)
    implementation(projects.core.network)
    implementation(projects.core.local)

    implementation(projects.features.tracks.domain)
    api(projects.features.tracks.network)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.androidx.paging.compose)

    // Dagger-Hilt
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}