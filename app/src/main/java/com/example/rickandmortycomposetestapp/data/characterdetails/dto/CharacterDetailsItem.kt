package com.example.rickandmortycomposetestapp.data.characterdetails.dto

data class CharacterDetailsItem(
    val id: Int,
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val image: String,
    val origin: OriginDetailsItem,
    val location: LocationDetailsItem
)