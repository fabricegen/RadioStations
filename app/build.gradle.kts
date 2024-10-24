plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.sample.radiostations"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sample.radiostations"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            buildConfigField("String", "API_KEY", "\"84c107b0-22f0-4958-883d-381edaa54174\"")
            buildConfigField("String", "BASE_URL", "\"https://openapi.radiofrance.fr/\"")
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        debug {
            buildConfigField("String", "API_KEY", "\"84c107b0-22f0-4958-883d-381edaa54174\"")
            buildConfigField("String", "BASE_URL", "\"https://openapi.radiofrance.fr/\"")
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
        compose = true
        buildConfig = true
    }

}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.timber)

    implementation(project(":app:presentation"))
    implementation(project(":app:domain"))
    implementation(project(":app:data"))

    implementation(project(":core:commons:api"))
    implementation(project(":core:commons:impl"))

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
}