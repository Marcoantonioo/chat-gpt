package com.example.chatgpt.data.remote.service

import com.example.chatgpt.BuildConfig
import com.example.chatgpt.data.remote.entity.OpenAiRequest
import com.example.chatgpt.data.remote.entity.OpenAiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface OpenAiApi {

    @POST("completions")
    @Headers("Content-Type: application/json")
    suspend fun makeQuestion(
        @Header("Authorization") apiKey: String = BuildConfig.AUTHORIZATION,
        @Header("OpenAI-Organization") organizationKey: String = BuildConfig.OPEN_IA_ORGANIZATION,
        @Body request: OpenAiRequest
    ): Response<OpenAiResponse>
}