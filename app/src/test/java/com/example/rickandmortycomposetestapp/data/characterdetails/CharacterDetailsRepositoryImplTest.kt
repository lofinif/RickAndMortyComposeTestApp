package com.example.rickandmortycomposetestapp.data.characterdetails

import com.example.rickandmortycomposetestapp.BaseTest
import com.example.rickandmortycomposetestapp.data.CallResult
import com.example.rickandmortycomposetestapp.data.network.RickAndMortyApi
import com.example.rickandmortycomposetestapp.domain.repository.CharacterDetailsRepository
import com.example.sharedtest.characterDetailsItemMock
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterDetailsRepositoryImplTest: BaseTest() {

    @MockK
    private lateinit var api: RickAndMortyApi

    private lateinit var repo: CharacterDetailsRepository

    override fun setUp() {
        super.setUp()
        repo = CharacterDetailsRepositoryImpl(api)
    }

    @Test
    fun `Api call finishes without exception, result is Success`() {
        coEvery { api.getCharacterDetails(any()) }.returns(characterDetailsItemMock)
        runTest(UnconfinedTestDispatcher()) {
            val result = repo.getCharacterDetails("")
            assertThat(result).isInstanceOf(CallResult.Success::class.java)
            assertThat((result as CallResult.Success).value).isEqualTo(
                characterDetailsItemMock
            )
        }
    }

    @Test
    fun `Api throws IOException, result is IOError`() {
        coEvery { api.getCharacterDetails(any()) }.throws(IOException())
        runTest(UnconfinedTestDispatcher()) {
            val result = repo.getCharacterDetails("")
            assertThat(result).isInstanceOf(CallResult.IOError::class.java)
        }
    }

    @Test
    fun `Api throws HttpException, result is HttpError`() {
        coEvery { api.getCharacterDetails(any()) }.throws(HttpException(Response.error<Nothing>(400, "".toResponseBody())))
        runTest(UnconfinedTestDispatcher()) {
            val result = repo.getCharacterDetails("")
            assertThat(result).isInstanceOf(CallResult.HttpError::class.java)
            assertThat((result as CallResult.HttpError).code).isEqualTo(400)
        }
    }
}