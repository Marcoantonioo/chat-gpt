package com.example.chatgpt.features.chat.data.mapper

import org.junit.Assert
import org.junit.Test

class OpenAiRequestMapperTest {

    @Test
    fun `String should success mapper to  OpenAiRequest`() {
        val message = "Test"

        val mapped = message.mapToRequest()

        Assert.assertEquals(message, mapped.prompt)
    }
}