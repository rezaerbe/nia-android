plugins {
    `kotlin-dsl`
}

group = "com.erbe.nowinandroid.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "nowinandroid.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "nowinandroid.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidViewApplication") {
            id = "nowinandroid.android.application.view"
            implementationClass = "AndroidViewApplicationConventionPlugin"
        }
        register("androidViewLibrary") {
            id = "nowinandroid.android.library.view"
            implementationClass = "AndroidViewLibraryConventionPlugin"
        }
        register("androidNavigation") {
            id = "nowinandroid.android.navigation"
            implementationClass = "AndroidNavigationConventionPlugin"
        }
        register("androidHilt") {
            id = "nowinandroid.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
    }
}