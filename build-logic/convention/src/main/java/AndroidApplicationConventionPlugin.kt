import com.android.build.api.dsl.ApplicationExtension
import com.joel.convention.config.configureAndroidApplication
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project>{

    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("com.android.application")
            }

            extensions.configure<ApplicationExtension> {
                configureAndroidApplication(this)
            }
        }
    }

}