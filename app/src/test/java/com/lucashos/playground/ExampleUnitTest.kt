package com.lucashos.playground

import com.google.gson.*
import com.lucashos.playground.core.BackendException
import com.lucashos.playground.core.ResponseDeserializer
import com.lucashos.playground.response.ErrorResponse
import com.lucashos.playground.response.Response
import com.lucashos.playground.response.SucessResponse
import org.junit.Test
import java.lang.reflect.Type

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    val successResponse: String = "{\n" +
            "  \"header\": null,\n" +
            "  \"statusCode\": 200,\n" +
            "  \"data\": {\n" +
            "      \"id\": 4919192,\n" +
            "      \"printed_name\": \"TESTE K\"\n" +
            "}}"

    val errorResponse: String = "{\n" +
            "  \"header\": null,\n" +
            "  \"statusCode\": 400,\n" +
            "  \"data\": {\n" +
            "    \"title\": \"Validation\"\n" +
            "  }\n" +
            "}"

    @Test
    fun addition_isCorrect() {
        try {
            gson().fromJson<Response<SucessResponse>>(successResponse, Response::class.java)
        } catch (e: BackendException) {
            e.errorResponse?.title
        }
    }

    fun gson(): Gson = GsonBuilder()
        .registerTypeAdapter(Response::class.java, ResponseDeserializer())
        .create()

}
