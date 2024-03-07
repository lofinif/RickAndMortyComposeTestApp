package com.example.rickandmortycomposetestapp.ui.characterdetails.mapper

import com.example.rickandmortycomposetestapp.data.characterdetails.dto.CharacterDetailsItem
import com.example.rickandmortycomposetestapp.ui.BaseMapper
import com.example.rickandmortycomposetestapp.ui.characterdetails.model.CharacterDetailsModel
import javax.inject.Inject

class CharacterDetailsItemToCharacterDetailsModelMapper @Inject constructor(): BaseMapper<CharacterDetailsItem, CharacterDetailsModel> {
    override fun map(item: CharacterDetailsItem): CharacterDetailsModel {
        return CharacterDetailsModel(
            id = item.id.toString(),
            name = item.name,
            gender = item.gender,
            status = item.status,
            species = item.species,
            avatar = item.image,
            origin = item.origin.name,
            location = item.location.name
        )
    }
}