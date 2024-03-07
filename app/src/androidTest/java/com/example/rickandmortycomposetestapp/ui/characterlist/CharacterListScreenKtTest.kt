package com.example.rickandmortycomposetestapp.ui.characterlist

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.PagingData
import com.example.compose.RickAndMortyComposeTestAppTheme
import com.example.rickandmortycomposetestapp.data.characterlist.dto.ResultsCharacterItems
import com.example.sharedtest.resultsCharacterModelMock
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
internal class CharacterListScreenKtTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun characterListIsShownTest(){
        composeRule.setContent {
            RickAndMortyComposeTestAppTheme {
                CharacterListScreen(
                    list = flowOf(PagingData.from(listOf(
                        resultsCharacterModelMock.copy(name = "name1"),
                        resultsCharacterModelMock.copy(name = "name2"),
                        resultsCharacterModelMock.copy(name = "name3")
                    ))),
                    onCharDetailsClick ={})
            }
        }
        composeRule.onNodeWithText("name1").assertExists()
        composeRule.onNodeWithText("name2").assertExists()
        composeRule.onNodeWithText("name3").assertExists()
    }
}