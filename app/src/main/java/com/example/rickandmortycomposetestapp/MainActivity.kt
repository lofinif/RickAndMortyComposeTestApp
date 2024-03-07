package com.example.rickandmortycomposetestapp

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.RickAndMortyComposeTestAppTheme
import com.example.rickandmortycomposetestapp.ui.RickAndMortyApp
import com.example.rickandmortycomposetestapp.ui.characterdetails.CharacterDetailsViewModel
import com.example.rickandmortycomposetestapp.ui.characterdetails.model.CharacterDetailsModel
import com.example.rickandmortycomposetestapp.ui.characterlist.CharacterListScreen
import com.example.rickandmortycomposetestapp.ui.navigation.Destination
import com.example.rickandmortycomposetestapp.ui.navigation.characterDetailsScreen
import com.example.rickandmortycomposetestapp.ui.navigation.characterListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyComposeTestAppTheme {
                val navController = rememberNavController()
                RickAndMortyApp(navController = navController)
            }
        }
    }
}