package com.lucashos.playground.data.repotistory

import com.lucashos.playground.api.MockyApiClient
import com.lucashos.playground.data.service.MockyService

object MockyRepository {
    fun getSuccess() =
        MockyApiClient().getRetrofit().create(MockyService::class.java).getSuccess()

    fun getError() =
        MockyApiClient().getRetrofit().create(MockyService::class.java).getError()
}