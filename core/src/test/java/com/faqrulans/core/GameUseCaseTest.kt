package com.faqrulans.core

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.faqrulans.core.data.Resource
import com.faqrulans.core.domain.model.Game
import com.faqrulans.core.domain.repository.GameRepository
import com.faqrulans.core.domain.usecase.GameUseCase
import com.faqrulans.core.domain.usecase.impl.GameUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class GameUseCaseTest {

    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()
    @ExperimentalCoroutinesApi
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var gameUseCase: GameUseCase

    @Mock
    private lateinit var gameRepository: GameRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        gameUseCase = GameUseCaseImpl(gameRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should get the list of games from repository`() {
        val fakeDeveloperId = "123"
        val fakeGame = listOf(Game(
            name = "GTA",
            released = "7-12-2015",
            rating = 4.0,
            backgroundImage = ""
        ))

        Mockito.`when`(gameRepository.getGames(fakeDeveloperId))
            .thenReturn(flowOf(Resource.Success(fakeGame)))


        val result = gameUseCase.getGames(fakeDeveloperId)

        Mockito.verify(gameRepository).getGames(fakeDeveloperId)
        Mockito.verifyNoMoreInteractions(gameRepository)
        runBlockingTest(testCoroutineDispatcher) {
            result.collect {
                Assert.assertEquals(fakeGame, it.data)
            }
        }
    }


}
