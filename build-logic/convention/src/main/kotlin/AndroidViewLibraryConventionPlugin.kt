import com.android.build.gradle.LibraryExtension
import com.erbe.nowinandroid.configureAndroidView
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidViewLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            val extension = extensions.getByType<LibraryExtension>()
            configureAndroidView(extension)
        }
    }
}