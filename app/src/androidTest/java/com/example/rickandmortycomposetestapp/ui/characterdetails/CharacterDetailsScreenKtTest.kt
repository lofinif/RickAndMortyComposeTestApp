package com.example.rickandmortycomposetestapp.ui.characterdetails

import android.content.res.Resources
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.example.compose.RickAndMortyComposeTestAppTheme
import com.example.rickandmortycomposetestapp.ui.characterdetails.state.CharacterDetailsScreenState
import com.example.sharedtest.characterDetailsModelMock
import com.example.rickandmortycomposetestapp.R
import org.junit.Rule
import org.junit.Test


internal class CharacterDetailsScreenKtTest{
    @get:Rule
    val composeRule = createComposeRule()
    private val res: Resources =
        InstrumentationRegistry.getInstrumentation().targetContext.resources
    private val mock = characterDetailsModelMock

    @Test
    fun allDetailsIsShownWhenStateIsLoaded(){
        composeRule.setContent {
            RickAndMortyComposeTestAppTheme {
                CharacterDetailsScreen(
                    onReloadClick = {},
                    state = CharacterDetailsScreenState.Loaded(mock)
                )
            }
        }

        composeRule.onNodeWithText(res.getString(R.string.character_details_gender))
            .assertIsDisplayed()
        composeRule.onNodeWithText(res.getString(R.string.character_details_origin))
            .assertIsDisplayed()
        composeRule.onNodeWithText(res.getString(R.string.character_details_last_know_location))
            .assertIsDisplayed()

        composeRule.onNodeWithText(mock.gender).assertIsDisplayed()
        composeRule.onNodeWithText(mock.location).assertIsDisplayed()
        composeRule.onNodeWithText(mock.name).assertIsDisplayed()
        composeRule.onNodeWithText("${mock.status} - ${mock.species}").assertIsDisplayed()
        composeRule.onNodeWithText(mock.origin).assertIsDisplayed()
    }
}