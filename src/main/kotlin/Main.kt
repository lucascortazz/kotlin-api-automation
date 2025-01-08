import io.ktor.client.*                                 // Used for HttpClient
import io.ktor.client.call.body                         // Used for the `.body<List<User>>()` call
import io.ktor.client.engine.cio.*                      // Used for `CIO` engine in HttpClient
import io.ktor.client.plugins.contentnegotiation.*      // Used for ContentNegotiation plugin
import io.ktor.client.request.get                       // Used for `.get` in `client.get(...)`
import io.ktor.serialization.kotlinx.json.*             // Used for `.json(Json { ... })`
import kotlinx.serialization.Serializable               // Used for `@Serializable` annotations
import kotlinx.serialization.json.Json                  // Used to configure JSON parsing
import kotlinx.coroutines.runBlocking                   // Used to run the `main` function as a coroutine

@Serializable
data class User(val name: String, val username: String, val email: String, val address: Address)
@Serializable
data class Address(val city: String)

fun main() = runBlocking {
    HttpClient(CIO) {
        install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
    }.use { client ->
        client.get("https://jsonplaceholder.typicode.com/users").body<List<User>>()
            .filter { it.address.city == "Gwenborough" }              //Available cities: Gwenborough, Wisokyburgh, McKenziehaven, South Elvis, Roscoeview, South Christy, Howemouth, Aliyaview, Bartholomebury, Lebsackbury
            .takeIf { it.isNotEmpty() }
            ?.forEach { println("Name: ${it.name}\nUsername: ${it.username}\nEmail: ${it.email}\nCity: ${it.address.city}\n") }
            ?: println("No users found in the specified city.")
    }
}
