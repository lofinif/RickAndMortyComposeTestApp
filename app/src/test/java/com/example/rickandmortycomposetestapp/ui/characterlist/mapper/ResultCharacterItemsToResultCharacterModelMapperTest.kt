package com.example.rickandmortycomposetestapp.ui.characterlist.mapper

import com.example.rickandmortycomposetestapp.BaseTest
import com.example.rickandmortycomposetestapp.ui.characterdetails.mapper.CharacterDetailsItemToCharacterDetailsModelMapper
import com.example.sharedtest.characterDetailsItemMock
import com.example.sharedtest.resultsCharacterItemMock
import com.google.common.truth.Truth
import org.junit.Test

class ResultCharacterItemsToResultCharacterModelMapperTest: BaseTest() {

    private lateinit var mapper: ResultCharacterItemsToResultCharacterModelMapper
    private val entity = resultsCharacterItemMock

    override fun setUp() {
        super.setUp()
        mapper = ResultCharacterItemsToResultCharacterModelMapper()
    }

    @Test
    fun `name is mapped correctly`(){
        val result = mapper.map(entity)
        Truth.assertThat(result.name).isEqualTo(entity.name)
    }

    @Test
    fun `location is mapped correctly`(){
        val result = mapper.map(entity)
        Truth.assertThat(result.location).isEqualTo(entity.location.name)
    }

    @Test
    fun `image is mapped correctly`(){
        val result = mapper.map(entity)
        Truth.assertThat(result.image).isEqualTo(entity.image)
    }

    @Test
    fun `id is mapped correctly`(){
        val result = mapper.map(entity)
        Truth.assertThat(result.id).isEqualTo(entity.id.toString())
    }

}