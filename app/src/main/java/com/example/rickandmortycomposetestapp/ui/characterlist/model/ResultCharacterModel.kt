package com.example.rickandmortycomposetestapp.ui.characterlist.model

import com.example.rickandmortycomposetestapp.data.characterlist.dto.LocationCharacterItems

data class ResultCharacterModel (
    val id: String,
    val name: String,
    val image: String,
    val location: String
)