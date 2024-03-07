package com.example.rickandmortycomposetestapp.domain.usecase

import androidx.paging.PagingData
import com.example.rickandmortycomposetestapp.BaseTest
import com.example.rickandmortycomposetestapp.data.characterlist.dto.ResultsCharacterItems
import com.example.rickandmortycomposetestapp.domain.repository.CharacterListRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Test

class GetPagedListUseCaseTest: BaseTest() {

    @MockK
    private lateinit var repo: CharacterListRepository

    private lateinit var useCase: GetPagedListUseCase

    override fun setUp() {
        super.setUp()
        MockKAnnotations.init(this)
        useCase = GetPagedListUseCase(repo)
    }

    @Test
    fun `useCase returns correct flow`() {
        val flow = flowOf<PagingData<ResultsCharacterItems>>()
        every { repo.pagedCharacterList }.returns(flow)
        val result = useCase.flowList
        assertThat(result).isEqualTo(flow)
    }
}