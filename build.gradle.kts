@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.androidx.navigation) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}