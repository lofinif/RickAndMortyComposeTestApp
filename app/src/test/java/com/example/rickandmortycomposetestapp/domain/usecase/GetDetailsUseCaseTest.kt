package com.example.rickandmortycomposetestapp.domain.usecase

import com.example.rickandmortycomposetestapp.BaseTest
import com.example.rickandmortycomposetestapp.data.CallResult
import com.example.rickandmortycomposetestapp.domain.repository.CharacterDetailsRepository
import com.example.sharedtest.characterDetailsItemMock
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetDetailsUseCaseTest: BaseTest() {

    @MockK
    private lateinit var repo: CharacterDetailsRepository

    private lateinit var useCase: GetDetailsUseCase

    override fun setUp() {
        super.setUp()
        MockKAnnotations.init(this)
        useCase = GetDetailsUseCase(repo)
    }

    @Test
    fun `Repo call returns success, use case also returns Success`() {
        coEvery { repo.getCharacterDetails(any()) }.returns(
            CallResult.Success(characterDetailsItemMock))
        runTest(UnconfinedTestDispatcher()) {
            val result = useCase("")
            assertThat(result).isInstanceOf(CallResult.Success::class.java)
            assertThat((result as CallResult.Success).value).isEqualTo(
                characterDetailsItemMock
            )
        }
    }

    @Test
    fun `Repo call returns IOError, use case also returns IOError`() {
        coEvery { repo.getCharacterDetails(any()) }.returns(CallResult.IOError)
        runTest(UnconfinedTestDispatcher()) {
            val result = useCase("")
            assertThat(result).isInstanceOf(CallResult.IOError::class.java)
        }
    }

    @Test
    fun `Repo call returns HttpException, use case also returns HttpError`() {
        coEvery { repo.getCharacterDetails(any()) }.returns(CallResult.HttpError(400, "msg"))
        runTest(UnconfinedTestDispatcher()) {
            val result = useCase("")
            assertThat(result).isInstanceOf(CallResult.HttpError::class.java)
            assertThat((result as CallResult.HttpError).code).isEqualTo(400)
            assertThat(result.message).isEqualTo("msg")
        }
    }

    @Test
    fun `Repo call returns UnknownError, use case also returns UnknownError`() {
        coEvery { repo.getCharacterDetails(any()) }.returns(CallResult.UnknownError)
        runTest(UnconfinedTestDispatcher()) {
            val result = useCase("")
            assertThat(result).isInstanceOf(CallResult.UnknownError::class.java)
        }
    }
}