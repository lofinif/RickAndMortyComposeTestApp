package com.example.rickandmortycomposetestapp.data.network

import com.example.rickandmortycomposetestapp.data.characterdetails.dto.CharacterDetailsItem
import com.example.rickandmortycomposetestapp.data.characterlist.dto.CharacterListItems
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(@Query("page") fromPage: Int): CharacterListItems

    @GET("character/{id}")
    suspend fun getCharacterDetails(@Path("id") id: String): CharacterDetailsItem

    companion object Service {
        fun create(retrofit: Retrofit): RickAndMortyApi = retrofit.create(RickAndMortyApi::class.java)
    }
}
