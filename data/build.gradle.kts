plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.neugelb.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
        buildConfigField("String", "API_KEY", "\"bba188120b5bfcf085e218e125f15200\"")
    }

    buildTypes {
        getByName("debug") {
            buildConfigField("boolean", "DEBUG", "true")
        }
        getByName("release") {
            buildConfigField("boolean", "DEBUG", "false")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(Dependencies.hilt_android)
    kapt(Dependencies.hilt_compiler)
    kaptTest(Dependencies.hilt_compiler)
    androidTestImplementation(Dependencies.hilt_test)
    testImplementation(Dependencies.mockito)
    testImplementation(Dependencies.mockito_inline)
    testImplementation(Dependencies.mockito_core)

    implementation(Dependencies.gson)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.okHttp)
    testImplementation(Dependencies.mock_web)

    implementation(Dependencies.paging)
}