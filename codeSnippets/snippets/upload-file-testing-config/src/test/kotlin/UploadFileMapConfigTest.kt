package com.example

import com.typesafe.config.ConfigFactory
import io.ktor.application.*
import io.ktor.config.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.testing.*
import io.ktor.utils.io.streams.*
import java.io.File
import kotlin.test.*

class UploadFileMapConfigTest {
    @Test
    fun testUploadMapApplicationConfig() = withTestApplication() {
        (environment.config as MapApplicationConfig).apply {
            put("upload.dir", "uploads")
        }
        application.main()
        with(handleRequest(HttpMethod.Post, "/upload"){
            val boundary = "WebAppBoundary"
            val fileBytes = File("ktor_logo.png").readBytes()

            addHeader(HttpHeaders.ContentType, ContentType.MultiPart.FormData.withParameter("boundary", boundary).toString())
            setBody(boundary, listOf(
                PartData.FormItem("Ktor logo", { }, headersOf(
                    HttpHeaders.ContentDisposition,
                    ContentDisposition.Inline
                        .withParameter(ContentDisposition.Parameters.Name, "description")
                        .toString()
                )),
                PartData.FileItem({ fileBytes.inputStream().asInput() }, {}, headersOf(
                    HttpHeaders.ContentDisposition,
                    ContentDisposition.File
                        .withParameter(ContentDisposition.Parameters.Name, "image")
                        .withParameter(ContentDisposition.Parameters.FileName, "ktor_logo.png")
                        .toString()
                ))
            ))
        }) {
            assertEquals("Ktor logo is uploaded to 'uploads/ktor_logo.png'", response.content)
        }
    }
}