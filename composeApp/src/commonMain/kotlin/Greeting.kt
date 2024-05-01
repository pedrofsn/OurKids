
import br.com.velantasistemas.ourkids.network.HttpClientModuleProviderBase
import br.com.velantasistemas.ourkids.network.ResponseBibleAPI
import io.ktor.client.call.body
import io.ktor.client.request.get

class Greeting {

    private val platform = getPlatform()
    private val client = HttpClientModuleProviderBase()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }

    suspend fun doRequest(): String {
        val response: ResponseBibleAPI = client.customClient()
            .get("https://www.abibliadigital.com.br/api/verses/nvi/random")
            .body<ResponseBibleAPI>()

        return response.book?.name.orEmpty()
    }
}
