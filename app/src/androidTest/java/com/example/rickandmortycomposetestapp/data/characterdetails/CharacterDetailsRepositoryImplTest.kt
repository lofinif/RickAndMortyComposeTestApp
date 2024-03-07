package com.example.rickandmortycomposetestapp.data.characterdetails

import com.example.rickandmortycomposetestapp.data.CallResult
import com.example.rickandmortycomposetestapp.data.characterdetails.dto.CharacterDetailsItem
import com.example.rickandmortycomposetestapp.domain.repository.CharacterDetailsRepository
import com.example.sharedtest.characterDetailsItemMock
import com.example.sharedtest.characterDetailsModelMock
import java.util.concurrent.Callable
import javax.inject.Inject

class CharacterDetailsRepositoryImplTest @Inject constructor(): CharacterDetailsRepository {
    override suspend fun getCharacterDetails(id: String): CallResult<CharacterDetailsItem> {
        return CallResult.Success(characterDetailsItemMock)
    }
}