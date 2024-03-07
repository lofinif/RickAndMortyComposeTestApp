package com.example.rickandmortycomposetestapp.ui.characterdetails

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortycomposetestapp.data.CallResult
import com.example.rickandmortycomposetestapp.domain.usecase.GetDetailsUseCase
import com.example.rickandmortycomposetestapp.ui.characterdetails.mapper.CharacterDetailsItemToCharacterDetailsModelMapper
import com.example.rickandmortycomposetestapp.ui.characterdetails.model.CharacterDetailsModel
import com.example.rickandmortycomposetestapp.ui.characterdetails.state.CharacterDetailsScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val getDetailsUseCase: GetDetailsUseCase,
    private val mapper: CharacterDetailsItemToCharacterDetailsModelMapper,
    private val state: SavedStateHandle
): ViewModel() {

    companion object {
        const val CHARACTER_DETAILS_KEY = "character_details"
    }

    var screenState by mutableStateOf<CharacterDetailsScreenState>(CharacterDetailsScreenState.Loading)
        private set

    fun fetchDetails(id: String) {
        val savedState: CharacterDetailsModel? = state[CHARACTER_DETAILS_KEY]
        if (savedState != null) screenState = CharacterDetailsScreenState.Loaded(savedState)
        else {
            viewModelScope.launch {
                when (val details = getDetailsUseCase(id)) {
                    is CallResult.Success -> {
                        val model = mapper.map(details.value)
                        screenState = CharacterDetailsScreenState.Loaded(model)
                    }

                    else -> {
                        screenState = CharacterDetailsScreenState.Error
                    }
                }
            }
        }
    }
}