package com.example.chatgpt.data.remote.entity

import com.google.gson.annotations.SerializedName

data class OpenAiResponse(
    @SerializedName("created") val created: Long? = null,
    @SerializedName("choices") val choices: List<OpenAiChoiceResponse>? = null
)

data class OpenAiChoiceResponse(
    @SerializedName("text") val text: String? = null
)
