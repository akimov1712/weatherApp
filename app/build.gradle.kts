plugins {
    id ("com.android.application")
    id ("org.jetbrains.kotlin.android")
    id ("com.google.devtools.ksp")
    id ("kotlin-kapt")
    id ("kotlin-parcelize")
    id ("androidx.navigation.safeargs.kotlin")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "ru.topbun.weather"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.topbun.weather"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    val roomVersion = "2.6.0"
    val hilt = "2.48.1"
    val navVersion = "2.7.5"
    val retrofitVersion = "2.9.0"

//    Hilt
    implementation ("com.google.dagger:hilt-android:$hilt")
    kapt ("com.google.dagger:hilt-compiler:$hilt")

//    Retrofit
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVersion")

//    View
    implementation ("com.squareup.picasso:picasso:2.71828")
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01")

//    Navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation ("androidx.navigation:navigation-ui-ktx:$navVersion")

//    JSON
    implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation ("com.google.code.gson:gson:2.10.1")

//    Room
    implementation("androidx.room:room-runtime:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

//    ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")

    implementation ("androidx.core:core-ktx:1.12.0")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("com.google.android.material:material:1.10.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.5")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.1")

}