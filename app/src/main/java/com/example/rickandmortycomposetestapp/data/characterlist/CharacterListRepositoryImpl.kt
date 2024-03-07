package com.example.rickandmortycomposetestapp.data.characterlist

import androidx.paging.Pager
import androidx.paging.PagingData
import com.example.rickandmortycomposetestapp.data.BaseRepo
import com.example.rickandmortycomposetestapp.data.CallResult
import com.example.rickandmortycomposetestapp.data.characterlist.dto.ResultsCharacterItems
import com.example.rickandmortycomposetestapp.domain.repository.CharacterListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
class CharacterListRepositoryImpl @Inject constructor(
    private val pager: Pager<Int, ResultsCharacterItems>,
): CharacterListRepository, BaseRepo() {
    override val pagedCharacterList: Flow<PagingData<ResultsCharacterItems>>
        get() = pager.flow
}

