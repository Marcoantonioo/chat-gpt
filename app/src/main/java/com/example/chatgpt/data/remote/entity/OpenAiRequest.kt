package com.example.chatgpt.data.remote.entity

import com.google.gson.annotations.SerializedName

data class OpenAiRequest(
    @SerializedName("prompt") val prompt: String,
    @SerializedName("model") val model: String = "text-davinci-003",
    @SerializedName("temperature") val temperature: Int = 0,
    @SerializedName("max_tokens") val maxTokens: Int = 512,
)
