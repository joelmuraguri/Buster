import com.android.build.api.dsl.LibraryExtension
import com.joel.convention.config.configureRoom
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class RoomAndroidConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("com.google.devtools.ksp")
            }

            extensions.configure<LibraryExtension>{
                configureRoom(this)
            }
        }
    }
}