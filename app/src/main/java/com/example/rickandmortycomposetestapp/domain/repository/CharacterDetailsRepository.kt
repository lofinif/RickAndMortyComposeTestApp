package com.example.rickandmortycomposetestapp.domain.repository

import com.example.rickandmortycomposetestapp.data.CallResult
import com.example.rickandmortycomposetestapp.data.characterdetails.dto.CharacterDetailsItem

interface CharacterDetailsRepository {
    suspend fun getCharacterDetails(id: String): CallResult<CharacterDetailsItem>
}