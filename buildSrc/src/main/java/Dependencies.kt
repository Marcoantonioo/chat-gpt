object  Dependencies {
    const val coreKtx = "androidx.core:core-ktx:${Versions.ktx}"
    const val appCompact = "androidx.appcompat:appcompat:${Versions.appCompact}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val junit = "junit:junit:${Versions.jUnit}"
    const val junitExt = "androidx.test.ext:junit:${Versions.jUnitExt}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLogging}"

    const val koin = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinTest =  "io.insert-koin:koin-test:${Versions.koin}"

    const val runtimeLifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val viewModelLifecycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

    const val mockk = "io.mockk:mockk:${Versions.mockk}"

    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val turbine = "app.cash.turbine:turbine:${Versions.turbine}"
}