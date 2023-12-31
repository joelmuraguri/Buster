import com.android.build.api.dsl.LibraryExtension
import com.joel.convention.config.configAndroidLibrary
import com.joel.convention.config.configureKtorClient
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class KtorClientConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            extensions.configure<LibraryExtension>{
                configureKtorClient(this)
            }
        }
    }
}