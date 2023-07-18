package com.example.chatgpt.features.chat.data.mapper

import com.example.utils.TestUtils
import org.junit.Assert
import org.junit.Test

class OpenAiResponseMapper {

    @Test
    fun `OpenAiResponse should success mapper to OpenAiDomain`() {
        val response = TestUtils.getOpenAiResponse()

        val (created, choices) = response

        val domain = response.mapToDomain()

        domain.run {
            Assert.assertEquals(created, created)
            Assert.assertEquals(choices, choices)
        }
    }
}