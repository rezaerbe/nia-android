plugins {
    id("nowinandroid.android.application")
    id("nowinandroid.android.application.view")
    id("nowinandroid.android.navigation")
    id("nowinandroid.android.hilt")
}

android {
    namespace = "com.erbe.nowinandroid"

    defaultConfig {
        applicationId = "com.erbe.nowinandroid"
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

    hilt {
        enableAggregatingTask = true
    }
}

dependencies {

    implementation(project(":core:design"))
    implementation(project(":core:common"))

    implementation(project(":feature:home"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
}

kapt {
    correctErrorTypes = true
}