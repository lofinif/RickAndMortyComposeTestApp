package com.example.rickandmortycomposetestapp.ui.characterlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.rickandmortycomposetestapp.domain.usecase.GetPagedListUseCase
import com.example.rickandmortycomposetestapp.ui.characterlist.mapper.ResultCharacterItemsToResultCharacterModelMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterListViewModel @Inject constructor(
    getPagedListUseCase: GetPagedListUseCase,
    private val mapper: ResultCharacterItemsToResultCharacterModelMapper
) : ViewModel() {
    val characterList = getPagedListUseCase.flowList.cachedIn(viewModelScope).map {
        it.map(mapper::map)
    }
}