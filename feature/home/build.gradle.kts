plugins {
    id("nowinandroid.android.library")
    id("nowinandroid.android.hilt")
}

android {
    namespace = "com.erbe.nowinandroid.feature.home"
}

dependencies {

    implementation(project(":core:data"))
    implementation(project(":core:common"))
}