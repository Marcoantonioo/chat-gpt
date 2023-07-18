package com.example.chatgpt.features.chat.data.repository

import com.example.chatgpt.features.chat.data.mapper.mapToDomain
import com.example.chatgpt.features.chat.data.remote.entity.OpenAiRequest
import com.example.chatgpt.features.chat.data.remote.service.OpenAiApi
import com.example.chatgpt.features.chat.domain.repository.IOpenAiRepository
import com.example.utils.TestUtils
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class OpenAiRepositoryTest {

    private val api = mockk<OpenAiApi>()

    private lateinit var repository: IOpenAiRepository

    @Before
    fun setup() {
        repository = OpenAiRepositoryImpl(api = api)
    }

    @Test
    fun `makeQuestion must return a OpenAiDomain (Domain)`() = runTest {
        val request = OpenAiRequest(prompt = PROMPT)
        val response = Response.success(TestUtils.getOpenAiResponse())
        coEvery { api.makeQuestion(request = request) } returns response

        Assert.assertEquals(response.body()?.mapToDomain(), repository.makeQuestion(PROMPT))
    }

    companion object {
        const val PROMPT = "Question"
    }
}