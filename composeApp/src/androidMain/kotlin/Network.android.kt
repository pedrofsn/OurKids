
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.observer.ResponseObserver

actual fun getCustomClient(): HttpClient {
    return HttpClient(OkHttp) {
        install(ResponseObserver) {
            onResponse { response ->
                println("HTTP status Android: ${response.status}")
            }
        }
    }
}
