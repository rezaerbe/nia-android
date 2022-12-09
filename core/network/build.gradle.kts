plugins {
    id("nowinandroid.android.library")
    id("nowinandroid.android.hilt")
}

android {
    namespace = "com.erbe.nowinandroid.core.network"
}

dependencies {

    implementation(project(":core:common"))

    implementation(libs.squareup.moshi.kotlin)
    kapt(libs.squareup.moshi.kotlin.codegen)

    implementation(libs.squareup.retrofit2)
    implementation(libs.squareup.retrofit2.converter.moshi)

    implementation(libs.squareup.okhttp3)

    implementation(libs.chuckerteam.chucker)

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.squareup.okhttp3.mockwebserver)
}