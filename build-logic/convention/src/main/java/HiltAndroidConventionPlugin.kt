import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.joel.convention.config.configureHilt
import org.gradle.api.Plugin
import org.gradle.api.Project

class HiltAndroidConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("com.google.dagger.hilt.android")
                apply("org.jetbrains.kotlin.kapt")
            }

            extensions.findByType(ApplicationExtension::class.java)?.let {
                configureHilt(it)
            }

            extensions.findByType(LibraryExtension::class.java)?.let {
                configureHilt(it)
            }
        }
    }
}