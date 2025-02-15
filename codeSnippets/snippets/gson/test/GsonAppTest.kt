import io.ktor.application.*
import io.ktor.http.*
import io.ktor.snippets.gson.*
import io.ktor.server.testing.*
import org.junit.Ignore
import kotlin.test.*

class GsonAppTest {
    @Test
    @Ignore
    fun testV1(): Unit = withTestApplication(Application::main) {
        handleRequest(HttpMethod.Get, "/v1").apply {
            assertEquals(200, response.status()?.value)
            assertEquals(
                """
                    |{
                    |  "name": "root",
                    |  "items": [
                    |    {
                    |      "key": "A",
                    |      "value": "Apache"
                    |    },
                    |    {
                    |      "key": "B",
                    |      "value": "Bing"
                    |    }
                    |  ],
                    |  "date": {
                    |    "year": 2018,
                    |    "month": 4,
                    |    "day": 13
                    |  }
                    |}
                """.trimMargin(),
                response.content
            )
        }
    }

    @Test
    @Ignore
    fun testV1ItemKey(): Unit = withTestApplication(Application::main) {
        handleRequest(HttpMethod.Get, "/v1/item/B").apply {
            assertEquals(200, response.status()?.value)
            assertEquals(
                """
                    |{
                    |  "key": "B",
                    |  "value": "Bing"
                    |}
                """.trimMargin(),
                response.content
            )
        }
    }

    @Test
    fun testV1ItemKeyUnexistant(): Unit = withTestApplication(Application::main) {
        handleRequest(HttpMethod.Get, "/v1/item/unexistant_key").apply {
            assertEquals(404, response.status()?.value)
        }
    }
}