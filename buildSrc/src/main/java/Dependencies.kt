object Dependencies {
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.gson}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val okHttp = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val mock_web = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val hilt_compiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    const val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val test = "androidx.test.ext:junit:${Versions.test}"
    const val junit = "junit:junit:${Versions.junit}"
    const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
    const val paging_test = "androidx.paging:paging-common:${Versions.paging}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"
    const val mockito_inline = "org.mockito:mockito-inline:${Versions.mockito}"
    const val mockito_core = "org.mockito:mockito-inline:${Versions.mockito}"
    const val coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4"
}

object Plugins {
    const val navigation = "androidx.navigation.safeargs"
}