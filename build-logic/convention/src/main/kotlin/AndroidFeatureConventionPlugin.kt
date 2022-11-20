import com.android.build.gradle.LibraryExtension
import com.erbe.nowinandroid.configureAndroidViewBinding
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("nowinandroid.android.library")
                apply("nowinandroid.android.hilt")
                apply("androidx.navigation.safeargs.kotlin")
            }

            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidViewBinding(extension)

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                add("implementation", project(":core:data"))
                add("implementation", project(":core:common"))

                add("implementation", libs.findLibrary("androidx.constraintLayout").get())

                add("implementation", libs.findLibrary("androidx.fragment.ktx").get())
                add("implementation", libs.findLibrary("androidx.navigation.ktx").get())
                add("implementation", libs.findLibrary("androidx.navigation.ui").get())
            }
        }
    }
}