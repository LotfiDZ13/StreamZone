plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.DZ.StreamZone"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.DZ.StreamZone"
        minSdk = 24
        targetSdk = 34
        versionCode 1
        versionName "1.0"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // AndroidX Media3 (ExoPlayer) - مشغل الفيديو المتقدم
    implementation("androidx.media3:media3-exoplayer:1.2.1")
    implementation("androidx.media3:media3-exoplayer-hls:1.2.1") // لدعم بث القنوات المباشرة .m3u8
    implementation("androidx.media3:media3-ui:1.2.1")

    // Retrofit & Gson - لجلب البيانات وقوائم التشغيل من السيرفرات
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    
    // Coroutines - للعمليات في الخلفية دون تجميد الشاشة
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
}