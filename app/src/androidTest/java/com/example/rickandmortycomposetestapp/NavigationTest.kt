package com.example.rickandmortycomposetestapp

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.testing.TestNavHostController
import com.example.rickandmortycomposetestapp.ui.RickAndMortyApp
import com.example.rickandmortycomposetestapp.ui.characterlist.CharacterList
import com.example.rickandmortycomposetestapp.ui.navigation.Destination
import com.example.rickandmortycomposetestapp.ui.navigation.characterListScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NavigationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<TestHiltActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setup(){
        hiltRule.inject()
        composeRule.setContent{
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
                RickAndMortyApp(navController)
        }
    }

    @Test
    fun testNavigation() {
        val oldRoute = navController.currentBackStackEntry?.destination?.route
        assertEquals(oldRoute, Destination.CharacterList.route)
        composeRule.onNodeWithText("Last known location:").performClick()
        val newRoute = navController.currentBackStackEntry?.destination?.route
        assertEquals(newRoute, Destination.CharacterDetails.route)
    }
}