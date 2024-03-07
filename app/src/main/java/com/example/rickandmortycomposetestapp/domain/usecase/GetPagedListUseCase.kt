package com.example.rickandmortycomposetestapp.domain.usecase

import com.example.rickandmortycomposetestapp.domain.repository.CharacterListRepository
import javax.inject.Inject

class GetPagedListUseCase @Inject constructor(private val repo: CharacterListRepository) {
    val flowList get() = repo.pagedCharacterList
}
