plugins {
    id("nowinandroid.android.library")
    id("nowinandroid.android.library.view")
    id("nowinandroid.android.navigation")
    id("nowinandroid.android.hilt")
}

android {
    namespace = "com.erbe.nowinandroid.feature.home"
}

dependencies {

    implementation(project(":core:data"))
    implementation(project(":core:common"))

    implementation(libs.androidx.fragment.ktx)
}