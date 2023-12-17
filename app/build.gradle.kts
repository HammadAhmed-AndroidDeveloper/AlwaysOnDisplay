plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 34

    defaultConfig {
        applicationId = "com.alwaysondisplay.analogclock.digital.nightclock"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug") {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        viewBinding = true
    }
    namespace = "com.alwaysondisplay.analogclock.digital.nightclock"
}

dependencies {
    implementation("com.google.android.material:material:1.10.0")
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("com.intuit.ssp:ssp-android:1.1.0")
    implementation("com.mikhaellopez:circularprogressbar:3.1.0")
    implementation("com.github.leondzn:simple-analog-clock:1.0.1")
    implementation("com.github.jakob-grabner:Circle-Progress-View:1.4")
    implementation("com.airbnb.android:lottie:5.2.0")
    implementation("androidx.work:work-runtime:2.9.0")
    implementation("com.github.ybq:Android-SpinKit:1.4.0")
}