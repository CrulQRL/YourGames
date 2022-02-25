package com.faqrulans.core

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.faqrulans.core.data.Resource
import com.faqrulans.core.domain.model.Developer
import com.faqrulans.core.domain.repository.DeveloperRepository
import com.faqrulans.core.domain.usecase.DeveloperUseCase
import com.faqrulans.core.domain.usecase.impl.DeveloperUseCaseImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class DeveloperUseCaseTest {

    private lateinit var developerUseCase: DeveloperUseCase
    @Mock
    private lateinit var developerRepository: DeveloperRepository

    @get:Rule
    val instantExecutor = InstantTaskExecutorRule()
    @ExperimentalCoroutinesApi
    private val testCoroutineDispatcher = TestCoroutineDispatcher()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        developerUseCase = DeveloperUseCaseImpl(developerRepository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should get the list of developers from repository`() {
        val fakeDevelopers = listOf(Developer(
            id = "123",
            name = "Ubisoft",
            gamesCount = 20,
            imageBackground = "image_url",
            isFavorite = false
        ))

        Mockito.`when`(developerRepository.getDevelopers())
            .thenReturn(flowOf(Resource.Success(fakeDevelopers)))

        val result = developerUseCase.getDevelopers()

        Mockito.verify(developerRepository).getDevelopers()
        Mockito.verifyNoMoreInteractions(developerRepository)
        runBlockingTest(testCoroutineDispatcher) {
            result.collect {
                Assert.assertEquals(fakeDevelopers, it.data)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should get the list of favorite developers from repository`() {
        val fakeDevelopers = listOf(Developer(
            id = "123",
            name = "Ubisoft",
            gamesCount = 20,
            imageBackground = "image_url",
            isFavorite = true
        ))

        Mockito.`when`(developerRepository.getFavoriteDeveloper())
            .thenReturn(flowOf(fakeDevelopers))

        val result = developerUseCase.getFavoriteDeveloper()

        Mockito.verify(developerRepository).getFavoriteDeveloper()
        Mockito.verifyNoMoreInteractions(developerRepository)
        runBlockingTest(testCoroutineDispatcher) {
            result.collect {
                Assert.assertEquals(fakeDevelopers, it)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should get the list of searched developers from repository`() {
        val fakeDevelopers = listOf(Developer(
            id = "123",
            name = "Ubisoft",
            gamesCount = 20,
            imageBackground = "image_url",
            isFavorite = true
        ))

        val query = "Ubi"

        Mockito.`when`(developerRepository.searchDeveloperByName(query))
            .thenReturn(flowOf(fakeDevelopers))

        val result = developerUseCase.searchDeveloperByName(query)

        Mockito.verify(developerRepository).searchDeveloperByName(query)
        Mockito.verifyNoMoreInteractions(developerRepository)
        runBlockingTest(testCoroutineDispatcher) {
            result.collect {
                Assert.assertEquals(fakeDevelopers, it)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should update favorite to repository`() {
        val fakeDevelopers = listOf(Developer(
            id = "123",
            name = "Ubisoft",
            gamesCount = 20,
            imageBackground = "image_url",
            isFavorite = false
        ))

        runBlockingTest(testCoroutineDispatcher) {
            Mockito.`when`(developerRepository.updateFavoriteDeveloper(fakeDevelopers[0].id, true))
                .thenReturn(1)

            val result = developerUseCase.updateFavoriteDeveloper(fakeDevelopers[0].id, true)

            Mockito.verify(developerRepository).updateFavoriteDeveloper(fakeDevelopers[0].id, true)
            Mockito.verifyNoMoreInteractions(developerRepository)

            Assert.assertEquals(1, result)
        }
    }

}
