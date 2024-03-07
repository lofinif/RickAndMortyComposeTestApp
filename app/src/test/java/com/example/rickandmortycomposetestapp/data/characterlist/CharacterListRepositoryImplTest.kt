package com.example.rickandmortycomposetestapp.data.characterlist

import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.rickandmortycomposetestapp.BaseTest
import com.example.rickandmortycomposetestapp.data.characterlist.dto.ResultsCharacterItems
import com.example.rickandmortycomposetestapp.domain.repository.CharacterListRepository
import com.example.rickandmortycomposetestapp.ui.characterlist.model.ResultCharacterModel
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CharacterListRepositoryImplTest: BaseTest() {


    @MockK
    private lateinit var pager: Pager<Int, ResultsCharacterItems>

    private lateinit var repo: CharacterListRepository

    override fun setUp() {
        super.setUp()
        repo = CharacterListRepositoryImpl(pager)
    }

    @Test
    fun `repo returns correct flow`() {
        val flow = flowOf<PagingData<ResultsCharacterItems>>()
        every { pager.flow }.returns(flow)
        val result = pager.flow
        Truth.assertThat(result).isEqualTo(flow)
    }
}