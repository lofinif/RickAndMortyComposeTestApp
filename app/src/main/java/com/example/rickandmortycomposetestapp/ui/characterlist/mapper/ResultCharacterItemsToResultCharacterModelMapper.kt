package com.example.rickandmortycomposetestapp.ui.characterlist.mapper

import com.example.rickandmortycomposetestapp.data.characterlist.dto.ResultsCharacterItems
import com.example.rickandmortycomposetestapp.ui.BaseMapper
import com.example.rickandmortycomposetestapp.ui.characterlist.model.ResultCharacterModel
import javax.inject.Inject

class ResultCharacterItemsToResultCharacterModelMapper @Inject constructor(): BaseMapper<ResultsCharacterItems, ResultCharacterModel> {
    override fun map(item: ResultsCharacterItems): ResultCharacterModel {
        return ResultCharacterModel(
            id = item.id.toString(),
            name = item.name,
            image = item.image,
            location = item.location.name,
        )
    }

}