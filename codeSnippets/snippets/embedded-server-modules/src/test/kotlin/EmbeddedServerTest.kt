import e2e.TestServer
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class EmbeddedServerTest: TestServer() {
    @Test
    fun rootRouteRespondsWithHelloString(): Unit = runBlocking {
        val response: String = HttpClient().get<HttpResponse>("http://localhost:8080/module").receive()
        assertEquals("Hello from 'module'!", response)
    }
}