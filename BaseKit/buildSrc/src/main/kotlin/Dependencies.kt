object Versions {
    //Android and UI
    const val appCompatVersion = "1.6.1"
    const val supportLibraryVersion = "28.0.0"
    const val kotlinVersion = "1.8.0"
    const val materialVersion = "1.5.0"
    const val constraintLayoutVersion = "2.1.4"

    const val hiltVersion = "2.44"

    const val navigationVersion = "2.5.3"

    const val retrofitVersion = "2.9.0"
    const val retrofitScalarVersion = "2.9.0"
    const val okhttpVersion = "4.9.1"
    const val glideVersion = "4.12.0"

    // Coroutines
    const val coroutinesVersion = "1.3.5"

    // Local storage
    const val sqlCipherVersion = "4.4.3"
    const val sqliteVersion = "2.1.0"
    const val roomVersion = "2.5.1"

    //Tests
    const val junitVersion = "4.13.2"
    const val jUnitExtVersion = "1.1.5"
    const val espressoVersion = "3.5.1"
}

object Libs {
    //Android and UI
    const val coreKtx = "androidx.core:core-ktx:${Versions.kotlinVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val materialLib = "com.google.android.material:material:${Versions.materialVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"

    //Dependency Injection
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hiltVersion}"

    //Navigation
    const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigationVersion}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigationVersion}"
    // Jetpack Compose Navigation Integration
    const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.navigationVersion}"
    const val navigationDynamicFeaturesFragment = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigationVersion}"

    //Networking
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val retrofitConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    const val retrofitConverterScalars = "com.squareup.retrofit2:converter-scalars:${Versions.retrofitScalarVersion}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"

    //Coroutine
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"

    //Local Storage
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.roomVersion}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val sqlCipher = "net.zetetic:android-database-sqlcipher:${Versions.sqlCipherVersion}"
    const val sqlite = "androidx.sqlite:sqlite:${Versions.sqliteVersion}"

    /**
     * Testing Dependencies
     */
    // Testing Navigation
    const val navigationTesting = "androidx.navigation:navigation-testing:${Versions.navigationVersion}"

    // Jetpack Compose Integration
    const val navigationComposeTesting = "androidx.navigation:navigation-compose:${Versions.navigationVersion}"

    const val junit = "junit:junit:${Versions.junitVersion}"
    const val androidxTestJunit = "androidx.test.ext:junit:${Versions.jUnitExtVersion}}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
}