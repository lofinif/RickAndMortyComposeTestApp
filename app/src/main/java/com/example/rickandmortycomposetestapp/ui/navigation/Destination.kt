package com.example.rickandmortycomposetestapp.ui.navigation

sealed class Destination(val route: String) {
    object CharacterList: Destination("character_list")
    object CharacterDetails: Destination("character_details/{id}"){
        fun createRoute(id: String) = "character_details/$id"
    }
}