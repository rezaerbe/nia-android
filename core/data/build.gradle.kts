plugins {
    id("nowinandroid.android.library")
    id("nowinandroid.android.hilt")
}

android {
    namespace = "com.erbe.nowinandroid.core.data"
}

dependencies {

    implementation(project(":core:network"))
    implementation(project(":core:common"))
}