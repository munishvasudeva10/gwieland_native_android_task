plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {
    namespace = "com.example.gweilandnativeandroidtask"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.gweilandnativeandroidtask"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }


    buildTypes{
        debug{
            buildConfigField ("String", "BASE_URL", "\"https://pro-api.coinmarketcap.com/\"")
            buildConfigField ("String", "API_KEY", "\"78a211ac-0886-4f04-85bf-c57ee421af22\"")
        }
        release{
            buildConfigField ("String", "BASE_URL", "\"https://pro-api.coinmarketcap.com/\"")
            buildConfigField ("String", "API_KEY", "\"78a211ac-0886-4f04-85bf-c57ee421af22\"")
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation ("androidx.compose.material:material:1.4.2")
    implementation("androidx.compose.material3:material3:1.2.0-alpha09")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


    //Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")

    //Kotlin CoRoutines
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.44.2")
    kapt("com.google.dagger:hilt-android-compiler:2.44.2")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.2")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // Async Image Loading
    implementation("io.coil-kt:coil-compose:2.0.0-rc01")

    // Async Image Loading
    implementation ("com.github.skydoves:landscapist-glide:2.1.0")
    implementation ("com.github.skydoves:landscapist-placeholder:2.1.0")
}