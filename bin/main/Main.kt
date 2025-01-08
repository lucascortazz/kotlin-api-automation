import io.ktor.client.*                                 // Used for HttpClient
import io.ktor.client.call.body                         // Used for `.body<List<User>>()`
import io.ktor.client.engine.cio.*                      // Used for `CIO` engine
import io.ktor.client.plugins.contentnegotiation.*      // Used for ContentNegotiation plugin
import io.ktor.client.plugins.HttpRequestTimeoutException // Used for timeout handling
import io.ktor.client.statement.HttpResponse            // Used for HTTP response handling
import io.ktor.client.request.get                       // Used for `.get` calls
import io.ktor.http.HttpStatusCode                      // Used for HTTP status codes
import io.ktor.serialization.kotlinx.json.*             // Used for JSON parsing
import kotlinx.coroutines.runBlocking                   // Used to run the `main` function
import kotlinx.serialization.Serializable               // Used for @Serializable annotation
import kotlinx.serialization.json.Json                  // Used to configure JSON parsing

@Serializable
data class User(val name: String, val username: String, val email: String, val address: Address)

@Serializable
data class Address(val city: String)

fun main() = runBlocking {
    // Configure the HTTP client
    val client = HttpClient(CIO) {
        install(ContentNegotiation) { json(Json { ignoreUnknownKeys = true }) }
        expectSuccess = false // Prevent automatic throwing of exceptions for non-2xx responses
    }

    try {
        println("Fetching user data...")

        // Make the API request
        val response: HttpResponse = client.get("https://jsonplaceholder.typicode.com/users")

        if (response.status == HttpStatusCode.OK) {
            val users: List<User> = response.body()
            val filteredUsers = users.filter { it.address.city == "Gwenborough" }

            if (filteredUsers.isNotEmpty()) {
                println("Users found in Gwenborough:\n")
                filteredUsers.forEach { user ->
                    println("""
                        Name: ${user.name}
                        Username: ${user.username}
                        Email: ${user.email}
                        City: ${user.address.city}
                    """.trimIndent())
                }
            } else {
                println("No users found in the specified city.")
            }
        } else {
            println("Failed to fetch users. HTTP status: ${response.status}")
        }
    } catch (e: HttpRequestTimeoutException) {
        println("Request timed out. Please check your network connection and try again.")
    } catch (e: Exception) {
        println("An error occurred: ${e.message}")
    } finally {
        client.close()
    }
}