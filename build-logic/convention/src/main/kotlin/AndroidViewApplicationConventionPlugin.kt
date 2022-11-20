import com.android.build.api.dsl.ApplicationExtension
import com.erbe.nowinandroid.configureAndroidView
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidViewApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidView(extension)
        }
    }
}