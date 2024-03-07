package com.example.rickandmortycomposetestapp.data.characterlist

import androidx.paging.PagingData
import com.example.rickandmortycomposetestapp.data.characterlist.dto.ResultsCharacterItems
import com.example.rickandmortycomposetestapp.domain.repository.CharacterListRepository
import com.example.sharedtest.resultsCharacterItemListMock
import com.example.sharedtest.resultsCharacterModelMock
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class CharacterListRepositoryImplTest @Inject constructor(): CharacterListRepository {
    override val pagedCharacterList: Flow<PagingData<ResultsCharacterItems>>
        get() = flowOf(PagingData.from(resultsCharacterItemListMock))

}