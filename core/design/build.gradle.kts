plugins {
    id("nowinandroid.android.library")
}

android {
    namespace = "com.erbe.nowinandroid.core.design"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {

    implementation(libs.android.material)
}