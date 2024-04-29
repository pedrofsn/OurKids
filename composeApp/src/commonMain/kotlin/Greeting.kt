import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.contentType
import network.ResponseBibleAPI

class Greeting {

    private val platform = getPlatform()
    private val client = HttpClientModuleProviderBase()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    suspend fun doRequest(): String {
        val response: ResponseBibleAPI = client.configureClient()
            .get("https://www.abibliadigital.com.br/api/verses/nvi/random")
            .body()

        return response.toString()
    }
}
