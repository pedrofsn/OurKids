import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

expect class HttpClientModuleProvider() {
    fun getPlatformSpecificClient(): HttpClient
}

class HttpClientModuleProviderBase {

    fun configureClient(): HttpClient {
        return HttpClientModuleProvider().getPlatformSpecificClient().apply {
            config {

                install(HttpRequestRetry) {
                    retryOnServerErrors(maxRetries = 5)
                }

                install(ContentNegotiation) {
                    json(
                        Json {
                            prettyPrint = true
                            isLenient = true
                            ignoreUnknownKeys = true
                        }
                    )
                }
            }
        }
    }
}
