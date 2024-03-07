package com.example.sharedtest

import com.example.rickandmortycomposetestapp.data.characterdetails.dto.CharacterDetailsItem
import com.example.rickandmortycomposetestapp.data.characterdetails.dto.LocationDetailsItem
import com.example.rickandmortycomposetestapp.data.characterdetails.dto.OriginDetailsItem
import com.example.rickandmortycomposetestapp.data.characterlist.dto.CharacterListItems
import com.example.rickandmortycomposetestapp.data.characterlist.dto.InfoCharacterItems
import com.example.rickandmortycomposetestapp.data.characterlist.dto.LocationCharacterItems
import com.example.rickandmortycomposetestapp.data.characterlist.dto.ResultsCharacterItems
import com.example.rickandmortycomposetestapp.ui.characterdetails.model.CharacterDetailsModel
import com.example.rickandmortycomposetestapp.ui.characterlist.model.ResultCharacterModel
import com.example.rickandmortycomposetestapp.ui.navigation.Destination

val resultsCharacterModelMock
    get() = ResultCharacterModel("1", "name", "image", "location")

val resultsCharacterItemMock
    get() = ResultsCharacterItems(1, "name", "image", locationCharacterItemMock)

val characterDetailsModelMock
    get() = CharacterDetailsModel("1", "name","gender", "status", "species", "origin", "location", "avatar")

val characterDetailsItemMock
    get() = CharacterDetailsItem(1, "name", "species", "status", "gender", "image", originDetailsItemMock, locationDetailsItemsMock)

val originDetailsItemMock
    get() = OriginDetailsItem("name","url")

val locationDetailsItemsMock
    get() = LocationDetailsItem("name", "url")

val locationCharacterItemMock
    get() = LocationCharacterItems("name")

val resultsCharacterItemListMock
    get() = listOf(resultsCharacterItemMock)

val characterListItemMock
    get() = CharacterListItems(infoCharacterItemsMock, resultsCharacterItemListMock)

val infoCharacterItemsMock
    get() = InfoCharacterItems(1, 1)