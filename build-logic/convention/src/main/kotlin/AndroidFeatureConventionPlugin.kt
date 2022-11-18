import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("nowinandroid.android.library")
                apply("nowinandroid.android.hilt")
            }
        }
    }
}