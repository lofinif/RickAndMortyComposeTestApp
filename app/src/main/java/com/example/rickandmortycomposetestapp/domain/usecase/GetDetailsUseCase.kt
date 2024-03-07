package com.example.rickandmortycomposetestapp.domain.usecase

import com.example.rickandmortycomposetestapp.domain.repository.CharacterDetailsRepository
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(private val repo: CharacterDetailsRepository) {
suspend operator fun invoke(id: String) = repo.getCharacterDetails(id)
}