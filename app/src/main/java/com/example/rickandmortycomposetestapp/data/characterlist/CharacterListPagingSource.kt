package com.example.rickandmortycomposetestapp.data.characterlist

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmortycomposetestapp.data.characterlist.dto.ResultsCharacterItems
import com.example.rickandmortycomposetestapp.data.network.RickAndMortyApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CharacterListPagingSource @Inject constructor(
    private val api: RickAndMortyApi,
): PagingSource<Int, ResultsCharacterItems>() {

    companion object {
        private const val TAG = "CharacterListSource"
        private const val STARTING_PAGE = 1
    }


    override fun getRefreshKey(state: PagingState<Int, ResultsCharacterItems>): Int? {
        return state.anchorPosition ?: STARTING_PAGE
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultsCharacterItems> = withContext(Dispatchers.IO) {
        val fromPage = params.key ?: STARTING_PAGE
        try {
            loadFromNet(fromPage)
        } catch (e: Exception){
            Log.e(TAG, "error while fetching data from rick and morty api", e)
            LoadResult.Error(e)
        }
    }

    private suspend fun loadFromNet(fromPage: Int): LoadResult<Int, ResultsCharacterItems> = withContext(
        Dispatchers.IO) {
        val characters = api.getCharacters(fromPage)
        val nextKey = if (fromPage < characters.info.pages) fromPage + 1 else null
        LoadResult.Page(characters.results, null, nextKey)
    }
}