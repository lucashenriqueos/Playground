package com.lucashos.playground.core

import com.google.gson.*
import com.lucashos.playground.response.ErrorResponse
import com.lucashos.playground.response.Response
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResponseDeserializer : JsonDeserializer<Response<Any>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Response<Any> {
        if (json?.isJsonObject == true) {
            val parameterizedType = typeOfT as ParameterizedType

            val statusCode = json?.asJsonObject.get("statusCode").asInt
            if (statusCode in 200..299) {
                return getSuccessResponse(statusCode, json, parameterizedType)
            } else {
                getErrorResponse(context, json)
            }
        }
        throw JsonParseException("An unexpected error occurred")
    }


    private fun getErrorResponse(
        context: JsonDeserializationContext?,
        json: JsonElement?
    ) {
        val errorResponse = context?.deserialize<ErrorResponse>(
            json?.asJsonObject?.get("data"),
            ErrorResponse::class.java
        )
        throw BackendException(
            errorResponse?.title,
            errorResponse!!
        )
    }

    private fun getSuccessResponse(
        statusCode: Int,
        json: JsonElement?,
        parameterizedType: ParameterizedType
    ):Response<Any> = Response(
        statusCode = statusCode,
        header = gson.fromJson<Map<String, String>>(
            json?.asJsonObject?.get("header"),
            Map::class.java
        ),
        data = gson.fromJson<Any>(
            json?.asJsonObject?.get("data"),
            parameterizedType.actualTypeArguments[0]
        )
    )

    val gson = GsonBuilder().create()
//        override fun deserialize(
//            json: JsonElement?,
//            typeOfT: Type?,
//            context: JsonDeserializationContext?
//        ): Response<Any> {
//            json?.takeIf {
//                it.isJsonObject
//            }.let {
//                it as JsonObject
//            }.takeIf {
//                it.has("statusCode")
//            }?.let {
//                val statusCode = it.getSuccess("statusCode").asInt
//                if (statusCode in 200..299) {
//                    return gson.fromJson<Response<Any>>(json, Response::class.java)
//                }
//                val errorResponse = context?.deserialize<ErrorResponse>(it.getSuccess("data"), ErrorResponse::class.java)
//                throw BackendException(
//                    errorResponse?.title,
//                    errorResponse!!
//                )
//            }
//            throw JsonParseException("Error")
//        }
}