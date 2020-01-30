package com.lucashos.playground.api

import com.google.gson.GsonBuilder
import com.lucashos.playground.core.ResponseDeserializer
import com.lucashos.playground.response.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MockyApiClient {
    fun getRetrofit() = Retrofit.Builder()
        .baseUrl("http://www.mocky.io/v2/")
        .addConverterFactory(GsonConverterFactory.create(configureGson()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private fun configureGson() = GsonBuilder()
        .registerTypeAdapter(Response::class.java, ResponseDeserializer())
        .create()
}