package org.company.app

// Custom serializer to read the text content of the `<string>` tag
import co.touchlab.kermit.Logger
import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.json.Json
import nl.adaptivity.xmlutil.serialization.XML


val settings = Settings()

object LogicKt {
    var serverStrings = mapOf<String , String>()

    fun parseXmlString(xml: String): Map<String, String> {
        Logger.d("LanguageLocal parsing to xml")
        val resources = XML.decodeFromString(Resources.serializer(), xml)
        return resources.string.associate { it.name to it.value }
    }

    suspend fun getOrDownload(): Languages {
        return settings.get<String>("ALL_LANG")?.let { Json.decodeFromString<Languages>(it) } ?: getLanguages()
    }

    fun setSelectedLanguage(code: String) {
//         settings.get<String>("ALL_LANG")?.let {
//             Json.decodeFromString<Languages>(it)
//         }?.languages?.get(code)?.let {
//             Logger.d("LanguageLocal code changed to $code  data =  $it")
//             serverStrings = parseXmlString(it)
//         }
    }
}

suspend fun getLanguages(): Languages {
    Logger.d("LanguageLocal downloading languages")
    val response = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true // Useful if your JSON may have extra fields
            })
        }
    }.get("https://meenagopal24.me/resources/languages.json")


    Logger.d("ResponseAsString ${Json.encodeToString(response.body<Languages>())}")
    settings.putString("ALL_LANG", Json.encodeToString(response.body<Languages>()))

    return response.body<Languages>()

}


//object LocalizedStringSerializer : kotlinx.serialization.KSerializer<LocalizedString> {
//    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("LocalizedString") {
//        element<String>("name")
//        element<String>("value")
//    }
//    override fun deserialize(decoder: Decoder): LocalizedString {
//        return decoder.decodeStructure(descriptor) {
//            var name: String? = null
//            var value: String? = null
//
//            loop@ while (true) {
//                when (val index = decodeElementIndex(descriptor)) {
//                    CompositeDecoder.DECODE_DONE -> break@loop
//                    0 -> name = decodeStringElement(descriptor, index)
//                    1 -> value = decodeStringElement(descriptor, index)
//                    else -> throw IllegalStateException("Unexpected index: $index")
//                }
//            }
//
//            LocalizedString(
//                name ?: throw IllegalStateException("Missing 'name' attribute"),
//                value ?: ""
//            )
//        }
//    }
//
//    override fun serialize(encoder: kotlinx.serialization.encoding.Encoder, value: LocalizedString) {
//        throw NotImplementedError("Serialization not needed")
//    }
//}
