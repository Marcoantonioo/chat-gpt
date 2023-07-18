package com.example.chatgpt.features.chat.domain.usecase

import com.example.chatgpt.features.chat.data.mapper.mapToDomain
import com.example.chatgpt.features.chat.domain.repository.IOpenAiRepository
import com.example.utils.TestUtils
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MakeQuestionUseCaseTest {
    private val repository = mockk<IOpenAiRepository>()

    private lateinit var useCase: IMakeQuestionUseCase

    @Before
    fun setup() {
        useCase = MakeQuestionUseCase(repository = repository)
    }

    @Test
    fun `MakeQuestionUseCase should return a OpenAiDomain when success`() = runTest {
        val response = TestUtils.getOpenAiResponse().mapToDomain()

        coEvery { repository.makeQuestion(question) } returns response

        Assert.assertEquals(response, useCase.invoke(question))
    }

    companion object {
        const val question = "Test"
    }
}