package com.example.rickandmortycomposetestapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.rickandmortycomposetestapp.ui.navigation.Destination
import com.example.rickandmortycomposetestapp.ui.navigation.characterDetailsScreen
import com.example.rickandmortycomposetestapp.ui.navigation.characterListScreen

@Composable
fun RickAndMortyApp(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(navController = navController, startDestination = Destination.CharacterList.route){
            characterListScreen (
                onCharDetailsClick = { navController.navigate(
                    Destination.CharacterDetails.createRoute(it))
                }
            )
            characterDetailsScreen()
        }
    }
}