package org.company.app

import co.touchlab.kermit.Logger
import com.russhwolf.settings.set
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import nl.adaptivity.xmlutil.EventType
import nl.adaptivity.xmlutil.XmlStreaming
import nl.adaptivity.xmlutil.core.impl.multiplatform.StringReader

// data classes

@Serializable
data class LocalLanguages(val languageCode : String, val xmlValues : Map<String , String>)

object KtorClient {

    private const val baseUrl = "https://tolgee-stg.digivalet.com/"
    private const val apiKey = "tgpak_gm3v65donezxa4lgg52ww5lggrshkztimfygyodunyzdmyi"

    private val ktorClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(Logging) {
            level = LogLevel.BODY
            logger = object : io.ktor.client.plugins.logging.Logger {
                override fun log(message: String) {
                    Logger.d("KtorClientConfig />> $message")
                }
            }
        }
    }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            Logger.d("KtorClientConfig calling api")
            getSupportedResources(listOf("hi" , "en")).let {
                settings["ALL_LANGUAGES"] = Json.encodeToJsonElement(it)
            }
        }
    }

    suspend fun getSupportedResources(language: List<String>): List<LocalLanguages?> {
        return language.map {
            getResources(it)?.let { it1 -> LocalLanguages(it , it1) }
        }
    }

    private suspend fun getResources(language: String): Map<String, String>? {
        val endpoint = "v2/projects/37/export"
        return runCatching {
            val response: HttpResponse = ktorClient.get(baseUrl + endpoint) {
                header("X-API-Key", apiKey)
                header(HttpHeaders.Accept, "application/json")
                url {
                    parameters.append("format", "ANDROID_XML")
                    parameters.append("zip", "false")
                    parameters.append("languages", language)
                }
            }
            Logger.d("KtorClientConfig ${response.body<String>()}")
            androidStringsXml(response.body<String>())
        }.getOrNull()
    }
}


fun androidStringsXml(xmlContent: String): Map<String, String> {
    val map = mutableMapOf<String, String>()
    val reader = XmlStreaming.newReader(StringReader(xmlContent))
    while (reader.hasNext()) {
        when (reader.next()) {
            EventType.START_ELEMENT -> {
                if (reader.localName == "string") {
                    val key = reader.getAttributeValue(null, "name")
                    val valueBuilder = StringBuilder()
                    while (reader.hasNext()) {
                        when (reader.next()) {
                            EventType.TEXT -> valueBuilder.append(reader.text)
                            EventType.START_ELEMENT -> valueBuilder.append("<${reader.localName}>")
                            EventType.END_ELEMENT -> {
                                if (reader.localName == "string") break
                                valueBuilder.append("</${reader.localName}>")
                            }
                            else -> {}
                        }
                    }

                    key?.let { map[it] = valueBuilder.toString().trim() }
                }
            }
            else -> Unit
        }
    }

    return map
}
