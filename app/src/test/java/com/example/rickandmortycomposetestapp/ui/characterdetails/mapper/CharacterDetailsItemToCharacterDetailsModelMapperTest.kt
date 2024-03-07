package com.example.rickandmortycomposetestapp.ui.characterdetails.mapper

import com.example.rickandmortycomposetestapp.BaseTest
import com.example.sharedtest.characterDetailsItemMock
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class CharacterDetailsItemToCharacterDetailsModelMapperTest: BaseTest() {

    private lateinit var mapper: CharacterDetailsItemToCharacterDetailsModelMapper
    private val entity = characterDetailsItemMock

    override fun setUp() {
        super.setUp()
        mapper = CharacterDetailsItemToCharacterDetailsModelMapper()
    }

    @Test
    fun `name is mapped correctly`(){
        val result = mapper.map(entity)
        assertThat(result.name).isEqualTo(entity.name)
    }
    @Test
    fun `gender is mapped correctly`(){
        val result = mapper.map(entity)
        assertThat(result.gender).isEqualTo(entity.gender)
    }
    @Test
    fun `status is mapped correctly`(){
        val result = mapper.map(entity)
        assertThat(result.status).isEqualTo(entity.status)
    }
    @Test
    fun `species is mapped correctly`(){
        val result = mapper.map(entity)
        assertThat(result.species).isEqualTo(entity.species)
    }
    @Test
    fun `avatar is mapped correctly`(){
        val result = mapper.map(entity)
        assertThat(result.avatar).isEqualTo(entity.image)
    }
    @Test
    fun `origin is mapped correctly`(){
        val result = mapper.map(entity)
        assertThat(result.origin).isEqualTo(entity.origin.name)
    }
    @Test
    fun `location is mapped correctly`(){
        val result = mapper.map(entity)
        assertThat(result.location).isEqualTo(entity.location.name)
    }

}