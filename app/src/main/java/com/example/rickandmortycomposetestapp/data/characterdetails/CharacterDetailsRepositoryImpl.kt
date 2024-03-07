package com.example.rickandmortycomposetestapp.data.characterdetails

import com.example.rickandmortycomposetestapp.data.BaseRepo
import com.example.rickandmortycomposetestapp.data.CallResult
import com.example.rickandmortycomposetestapp.data.characterdetails.dto.CharacterDetailsItem
import com.example.rickandmortycomposetestapp.data.network.RickAndMortyApi
import com.example.rickandmortycomposetestapp.domain.repository.CharacterDetailsRepository
import javax.inject.Inject

class CharacterDetailsRepositoryImpl @Inject constructor(private val api: RickAndMortyApi): BaseRepo(), CharacterDetailsRepository {
    override suspend fun getCharacterDetails(id: String): CallResult<CharacterDetailsItem> {
       return safeApiCall { api.getCharacterDetails(id) }
    }
}