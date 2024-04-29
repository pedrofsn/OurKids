import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.observer.ResponseObserver

actual class HttpClientModuleProvider actual constructor() {
    actual fun getPlatformSpecificClient(): HttpClient {
        return HttpClient(OkHttp) {
            installFeatures(this@HttpClient)
        }
    }

    private fun installFeatures(config: HttpClientConfig<*>) = with(config) {
        install(ResponseObserver) {
            onResponse { response ->
                println("HTTP status Android: ${response.status}")
            }
        }
    }
}
