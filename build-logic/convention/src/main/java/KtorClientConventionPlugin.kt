import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import com.joel.convention.config.configAndroidLibrary
import com.joel.convention.config.configureKtorClient
import com.joel.convention.config.configureSharedLibrary
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class KtorClientConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("org.jetbrains.kotlin.plugin.serialization")
            }

            extensions.findByType(ApplicationExtension::class.java)?.let {
                configureKtorClient(it)
            }

            extensions.findByType(LibraryExtension::class.java)?.let {
                configureKtorClient(it)
            }
        }
    }
}