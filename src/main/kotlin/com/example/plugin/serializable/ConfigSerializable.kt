
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json

fun Application.configureSerializable() {
    install(ContentNegotiation) {
        json(
            Json {
                encodeDefaults = false
                prettyPrint = true
                isLenient = true
            }
        )
    }
}
