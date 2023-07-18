package com.example.chatgpt.features.chat.presentation.viewmodel

import app.cash.turbine.test
import com.example.chatgpt.features.chat.domain.model.OpenAiChoiceDomain
import com.example.chatgpt.features.chat.domain.model.OpenAiDomain
import com.example.chatgpt.features.chat.domain.usecase.IMakeQuestionUseCase
import com.example.chatgpt.features.chat.presentation.mapper.mapMessageToMe
import com.example.chatgpt.features.chat.presentation.mapper.mapToView
import com.example.chatgpt.features.chat.presentation.model.OpenAiEvent
import com.example.chatgpt.features.chat.presentation.model.OpenAiScreenState
import com.example.utils.TestDispatcherRule
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class OpenAiViewModelTest {

    private lateinit var viewModel: OpenAiViewModel

    private val useCase = mockk<IMakeQuestionUseCase>()

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Before
    fun setup() {
        viewModel = OpenAiViewModel(
            userCase = useCase
        )
    }

    @Test
    fun `given a dispatchEvent of type MakeQuestion should emit a success state`() = runTest {
        val message = "Teste"

        val answerMock = OpenAiDomain(
            created = 2,
            choices = listOf(
                OpenAiChoiceDomain(
                    text = "Resposta"
                )
            )
        )

        val answerToMe = message.mapMessageToMe()

        coEvery { useCase.invoke(message) } returns answerMock

        viewModel.state.test {

            viewModel.dispatchEvent(OpenAiEvent.MakeQuestion(message))

            assertEquals(awaitItem(), OpenAiScreenState.Loading(isLoading = true))

            assertEquals(
                awaitItem(),
                OpenAiScreenState.Success(
                    data = mutableListOf(answerToMe, answerMock.mapToView()),
                    isLocal = true
                )
            )

            awaitEvent()

            assertEquals(awaitItem(), OpenAiScreenState.Loading(isLoading = false))

            expectNoEvents()
        }
    }

    @Test
    fun `given a dispatchEvent of type MakeQuestion should emit a error state`() = runTest {
        val message = "Teste"

        val exception = Exception("Error")

        coEvery { useCase.invoke(message) }.throws(exception)

        viewModel.state.test {

            viewModel.dispatchEvent(OpenAiEvent.MakeQuestion(message))

            assertEquals(awaitItem(), OpenAiScreenState.Loading(isLoading = true))

            skipItems(1)

            assertEquals(awaitItem(), OpenAiScreenState.Error(exception.message ?: "Error"))

            assertEquals(awaitItem(), OpenAiScreenState.Loading(isLoading = false))

            expectNoEvents()
        }
    }
}