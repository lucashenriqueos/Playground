package com.lucashos.playground.core

import com.lucashos.playground.response.ErrorResponse

class BackendException(title: String?, val errorResponse: ErrorResponse) : Throwable(title)