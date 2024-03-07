package com.example.rickandmortycomposetestapp.ui.characterdetails.state

import com.example.rickandmortycomposetestapp.ui.characterdetails.model.CharacterDetailsModel

sealed interface CharacterDetailsScreenState {
    object Loading: CharacterDetailsScreenState
    object Error: CharacterDetailsScreenState
    data class Loaded(val model: CharacterDetailsModel): CharacterDetailsScreenState
}