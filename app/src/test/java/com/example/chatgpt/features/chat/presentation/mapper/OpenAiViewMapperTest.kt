package com.example.chatgpt.features.chat.presentation.mapper

import com.example.chatgpt.features.chat.data.mapper.mapToDomain
import com.example.utils.TestUtils
import org.junit.Assert
import org.junit.Test
import java.util.*

class OpenAiViewMapperTest {

    @Test
    fun `OpenAiDomain should success mapper to MessageView`() {
        val domain = TestUtils.getOpenAiResponse().mapToDomain()

        val (created, choices) = domain

        val view = domain.mapToView()

        view.run {
            Assert.assertEquals(created, createdAt)
            Assert.assertEquals(choices?.map { it.text }, message)
            Assert.assertEquals(false, isFromMe)
        }
    }

    @Test
    fun `String should success mapper to MessageView`() {
        val result = "Teste"
        val createAtMock = Calendar.getInstance().time.time

        val view = result.mapMessageToMe().copy(
            createdAt = createAtMock
        )

        view.run {
            Assert.assertEquals(listOf(result), message)
            Assert.assertEquals(true, isFromMe)
            Assert.assertEquals(createAtMock, this.createdAt)
        }
    }
}