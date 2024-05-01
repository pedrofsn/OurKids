
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

expect fun getCustomClient(): HttpClient

class HttpClientModuleProviderBase {

    fun customClient(): HttpClient {
        return getCustomClient().run {
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
