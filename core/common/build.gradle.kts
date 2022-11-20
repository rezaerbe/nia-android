plugins {
    id("nowinandroid.android.library")
    id("nowinandroid.android.library.view")
    id("nowinandroid.android.hilt")
}

android {
    namespace = "com.erbe.nowinandroid.core.common"
}

dependencies {

    implementation(libs.androidx.lifecycle.runtime.ktx)
}