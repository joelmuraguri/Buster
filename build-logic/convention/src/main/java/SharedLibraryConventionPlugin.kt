import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.joel.convention.config.configureSharedLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project

class SharedLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){


            extensions.findByType(ApplicationExtension::class.java)?.let {
                configureSharedLibrary(it)
            }

            extensions.findByType(LibraryExtension::class.java)?.let {
                configureSharedLibrary(it)
            }
        }
    }
}