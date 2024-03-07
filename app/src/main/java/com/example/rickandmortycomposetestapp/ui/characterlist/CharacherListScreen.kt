package com.example.rickandmortycomposetestapp.ui.characterlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.rickandmortycomposetestapp.R
import com.example.rickandmortycomposetestapp.data.characterlist.dto.ResultsCharacterItems
import com.example.rickandmortycomposetestapp.ui.characterlist.model.ResultCharacterModel
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.flow.Flow

@Composable
fun CharacterListScreen(list: Flow<PagingData<ResultCharacterModel>>, onCharDetailsClick: (String) -> Unit) {
    val items = list.collectAsLazyPagingItems()
    Box {
        ShowInitialLoad(items)
        CharacterList(items, onCharDetailsClick)
    }
}

@Composable
fun CharacterList(items: LazyPagingItems<ResultCharacterModel>, onCharDetailsClick: (String) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(
            top = 16.dp,
            bottom = 16.dp,
            start = 8.dp,
            end = 8.dp
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        showLoadedItems(items = items, onCharDetailsClick = onCharDetailsClick)
        when (val state = items.loadState.append) {
            is LoadState.Loading -> {
                item {
                    LoadingUserListItem()
                }
            }

            is LoadState.Error -> {
                singleItemLoadingError()
            }

            else -> {}
        }
    }
}

private fun LazyListScope.singleItemLoadingError() {
    item {
        Surface {
            val errorMsg = stringResource(id = R.string.character_item_loading_error)
            val tryAgainMsg = stringResource(id = R.string.character_item_try_again)
            Column {
                Text(
                    text = errorMsg,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = tryAgainMsg,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

private fun LazyListScope.showLoadedItems(
    items: LazyPagingItems<ResultCharacterModel>,
    onCharDetailsClick: (String) -> Unit
) {
    items(items.itemCount) {
        val character = items[it]
        character?.let {
            CharacterListItem(
                name = character!!.name,
                location = character!!.location,
                avatarUrl = character!!.image,
                id = character!!.id,
                onCharDetailsClick = onCharDetailsClick
            )
        }
    }
}

@Composable
private fun BoxScope.ShowInitialLoad(items: LazyPagingItems<ResultCharacterModel>) {
    when (items.loadState.refresh) {
        is LoadState.Loading -> {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(top = 16.dp, start = 12.dp, end = 12.dp)
            ) {
                repeat(7) {
                    LoadingUserListItem()
                }
            }
        }

        is LoadState.Error -> {
            RefreshErrorState {
                items.refresh()
            }
        }
        else -> {}
    }
}

@Composable
fun LoadingUserListItem(){
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.tertiary,
        modifier = Modifier
            .shimmer()
            .fillMaxWidth()
            .height(100.dp)

    ) {
    }
}

@Composable
fun BoxScope.RefreshErrorState(
    onReloadClick: () -> Unit,
) {
    val errorMsg = stringResource(id = R.string.character_item_loading_error)
    val tryAgainMsg = stringResource(id = R.string.character_item_try_again)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = errorMsg, style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.size(8.dp))

        Button(
            onClick = { onReloadClick() },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text(
                text = tryAgainMsg,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}

@Composable
fun CharacterListItem(
    id: String,
    name: String,
    location: String,
    avatarUrl: String,
    onCharDetailsClick: (String) ->  Unit
    ) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.tertiary,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCharDetailsClick(id) }
    ) {
        Row {
            AsyncImage(
                model = avatarUrl,
                contentDescription = "Character avatar",
                modifier = Modifier.size(100.dp)
            )
            Column {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .padding(vertical = 6.dp, horizontal = 12.dp),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = stringResource(id = R.string.character_item_last_know_location),
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.LightGray,
                    modifier = Modifier.padding(top = 6.dp, start = 12.dp, end = 12.dp),
                )
                Text(
                    text = location,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 3.dp),
                    )
            }
        }
    }
}
