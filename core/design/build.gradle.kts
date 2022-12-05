plugins {
    id("nowinandroid.android.library")
    id("nowinandroid.android.library.view")
}

android {
    namespace = "com.erbe.nowinandroid.core.design"
}

dependencies {

    implementation(project(":core:common"))

    implementation(libs.coil)
}