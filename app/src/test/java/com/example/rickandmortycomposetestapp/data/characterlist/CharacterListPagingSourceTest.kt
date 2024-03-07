package com.example.rickandmortycomposetestapp.data.characterlist

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortycomposetestapp.BaseTest
import com.example.rickandmortycomposetestapp.data.network.RickAndMortyApi
import com.example.sharedtest.characterListItemMock
import com.example.sharedtest.resultsCharacterItemListMock
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
class CharacterListPagingSourceTest : BaseTest() {

    @MockK
    private lateinit var api: RickAndMortyApi

    private lateinit var pagingSource: CharacterListPagingSource

    private lateinit var pagingConfig: PagingConfig

    override fun setUp() {
        super.setUp()
        pagingConfig = PagingConfig(50)
    }

    @Test
    fun `getRefreshKey return 1`() {
        pagingSource = CharacterListPagingSource(api)
        val result = pagingSource.getRefreshKey(PagingState(listOf(), null, pagingConfig, 0))
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun `Api call finishes without exception, result is Page`() {
        pagingSource = CharacterListPagingSource(api)
        coEvery { api.getCharacters(any()) }.returns(characterListItemMock)
        runTest(UnconfinedTestDispatcher()) {
            val params = PagingSource.LoadParams.Append(1, 50, false)
            val result = pagingSource.load(params)
            assertThat(result).isInstanceOf(PagingSource.LoadResult.Page::class.java)
            assertThat((result as PagingSource.LoadResult.Page).data).isEqualTo(
                resultsCharacterItemListMock
            )
        }
    }

    @Test
    fun `Api throws IOException, result is Error`() {
        pagingSource = CharacterListPagingSource(api)
        coEvery { api.getCharacters(any()) }.throws(IOException())
        runTest(UnconfinedTestDispatcher()) {
            val params = PagingSource.LoadParams.Append(1, 50, false)
            val result = pagingSource.load(params)
            assertThat(result).isInstanceOf(PagingSource.LoadResult.Error::class.java)
            assertThat((result as PagingSource.LoadResult.Error).throwable).isInstanceOf(
                IOException::class.java
            )
        }
    }

    @Test
    fun `Api throws HttpException, result is Error`() {
        pagingSource = CharacterListPagingSource(api)
        coEvery { api.getCharacters(any()) }.throws(
            HttpException(
                Response.error<Nothing>(
                    400,
                    "".toResponseBody()
                )
            )
        )
        runTest(UnconfinedTestDispatcher()) {
            val params = PagingSource.LoadParams.Append(1, 50, false)
            val result = pagingSource.load(params)
            assertThat(result).isInstanceOf(PagingSource.LoadResult.Error::class.java)
            assertThat((result as PagingSource.LoadResult.Error).throwable).isInstanceOf(
                HttpException::class.java
            )
        }
    }
}