package com.example.chatgpt.commons.request

import retrofit2.Response

suspend fun <T> doRequest(block: suspend () -> Response<T>): T {
    return try {

        val response = block.invoke()

        response.run {
            when (isSuccessful && body() != null) {
                true -> {
                    body()!!
                }
                else -> {
                    throw Exception("Error body")
                }
            }
        }
    } catch (ex: Exception) {
        throw Exception("Error body")
    }
}
