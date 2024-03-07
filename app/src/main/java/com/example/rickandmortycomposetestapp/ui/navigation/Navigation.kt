package com.example.rickandmortycomposetestapp.ui.navigation

import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickandmortycomposetestapp.data.characterlist.dto.ResultsCharacterItems
import com.example.rickandmortycomposetestapp.ui.characterdetails.CharacterDetailsScreen
import com.example.rickandmortycomposetestapp.ui.characterdetails.CharacterDetailsViewModel
import com.example.rickandmortycomposetestapp.ui.characterlist.CharacterListScreen
import com.example.rickandmortycomposetestapp.ui.characterlist.CharacterListViewModel

fun NavGraphBuilder.characterListScreen(onCharDetailsClick: (String) -> Unit){
    composable(Destination.CharacterList.route) {
        val viewModel: CharacterListViewModel = hiltViewModel()
        CharacterListScreen(
            list = viewModel.characterList,
            onCharDetailsClick = onCharDetailsClick
        )
    }
}

fun NavGraphBuilder.characterDetailsScreen(){
    composable(Destination.CharacterDetails.route) { navBackStackEntry ->
        val id = navBackStackEntry.arguments?.getString("id")
        val viewModel: CharacterDetailsViewModel = hiltViewModel()
        LaunchedEffect(id) {
                viewModel.fetchDetails(id ?: "")
        }
        val state = viewModel.screenState
        CharacterDetailsScreen(onReloadClick = { viewModel.fetchDetails(id ?: "") }, state = state)
    }
}