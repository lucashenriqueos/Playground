package com.lucashos.playground.data.service

import com.lucashos.playground.response.Response
import com.lucashos.playground.response.SucessResponse
import io.reactivex.Single
import retrofit2.http.GET

interface MockyService {

    @GET("5e330fc7320000a16994d3b5")
    fun getSuccess(): Single<Response<SucessResponse>>

    @GET("5e330fc7320000a16994d3b5")
    fun getError(): Single<Response<SucessResponse>>
}
