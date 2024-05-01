package br.com.velantasistemas.ourkids.network
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.observer.ResponseObserver

actual fun getCustomClient(): HttpClient {
    return HttpClient(Darwin) {
        install(ResponseObserver) {
            onResponse { response ->
                println("HTTP status IOS: ${response.status}")
            }
        }
    }
}
