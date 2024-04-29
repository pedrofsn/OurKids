import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.observer.ResponseObserver

actual class HttpClientModuleProvider {

    actual fun getPlatformSpecificClient() = HttpClient(Darwin) {
        installFeatures(this@HttpClient)
    }

    private fun installFeatures(config: HttpClientConfig<*>) = with(config) {
        install(ResponseObserver) {
            onResponse { response ->
                println("HTTP status IOS: ${response.status}")
            }
        }
    }
}
