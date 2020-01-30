package com.lucashos.playground.response

class Response<T>(
    val data: T,
    val header: Map<String, String>?,
    val statusCode: Int
)
