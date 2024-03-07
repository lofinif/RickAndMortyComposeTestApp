package com.example.rickandmortycomposetestapp.domain.repository

import androidx.paging.PagingData
import com.example.rickandmortycomposetestapp.data.CallResult
import com.example.rickandmortycomposetestapp.data.characterlist.dto.ResultsCharacterItems
import kotlinx.coroutines.flow.Flow

interface CharacterListRepository {
    val pagedCharacterList: Flow<PagingData<ResultsCharacterItems>>
}