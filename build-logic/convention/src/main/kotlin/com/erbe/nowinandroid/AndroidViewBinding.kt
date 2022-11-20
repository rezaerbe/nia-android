package com.erbe.nowinandroid

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

@Suppress("UnstableApiUsage")
internal fun Project.configureAndroidViewBinding(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            viewBinding = true
        }
    }
}