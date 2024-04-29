package network
import kotlinx.serialization.Serializable

@Serializable
data class ResponseBibleAPI(
    val book: Book?,
    val chapter: Int?,
    val number: Int?,
    val text: String?
)

@Serializable
data class Book(
    val abbrev: Abbrev?,
    val author: String?,
    val group: String?,
    val name: String?,
    val version: String?
)

@Serializable
data class Abbrev(
    val en: String?,
    val pt: String?
)
