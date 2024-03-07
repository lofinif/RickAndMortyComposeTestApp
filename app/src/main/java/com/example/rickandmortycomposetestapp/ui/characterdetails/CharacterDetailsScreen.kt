package com.example.rickandmortycomposetestapp.ui.characterdetails

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.size.Dimension
import com.example.rickandmortycomposetestapp.R
import com.example.rickandmortycomposetestapp.ui.characterdetails.model.CharacterDetailsModel
import com.example.rickandmortycomposetestapp.ui.characterdetails.state.CharacterDetailsScreenState
import com.example.rickandmortycomposetestapp.ui.navigation.Destination
import com.valentinilk.shimmer.shimmer

@Composable
fun CharacterDetailsScreen(
    onReloadClick: () -> Unit,
    state: CharacterDetailsScreenState
){
    Box {
        when (state) {
            CharacterDetailsScreenState.Loading -> {
                LoadingState()
            }
            CharacterDetailsScreenState.Error -> {
                ErrorState(onReloadClick)
            }
            is CharacterDetailsScreenState.Loaded -> {
                LoadedState(state.model)
            }
        }
    }
}


@Composable
fun AvatarSection(model: CharacterDetailsModel) {
    Box() {
        AsyncImage(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .heightIn(0.dp, 380.dp)
                .clip(RoundedCornerShape(7.dp))
                .border(1.5.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(7.dp))
                .semantics {
                    this.testTag = model.avatar
                },
            model = model.avatar,
            contentDescription = "avatar",
        )
    }
}


@Composable
fun DetailsSection(model: CharacterDetailsModel){
    Surface(
        color = MaterialTheme.colorScheme.tertiary,
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {
        ConstraintLayout(modifier = Modifier.padding(8.dp)) {
            val (name, status, genderLabel, gender, locationLabel, location, originLabel, origin) = createRefs()
            Text(
                modifier = Modifier
                    .constrainAs(name){
                           width = androidx.constraintlayout.compose.Dimension.preferredWrapContent
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                            linkTo(parent.start, parent.end, bias = 0f)
                    },
                text = model.name,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                modifier = Modifier.constrainAs(status){
                    top.linkTo(name.bottom)
                    linkTo(parent.start, parent.end, bias = 0f, startMargin = 10.dp)
                },
                text = "${model.status} - ${model.species}",
                style = MaterialTheme.typography.titleSmall
            )
            val genderLabelString = stringResource(id = R.string.character_details_gender)
            Text(
                modifier = Modifier.constrainAs(genderLabel){
                    top.linkTo(status.bottom, margin = 6.dp)
                    start.linkTo(parent.start)
                },
                text = genderLabelString,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                modifier = Modifier.constrainAs(gender){
                    baseline.linkTo(genderLabel.baseline)
                    start.linkTo(genderLabel.end, margin = 7.dp)
                },
                text = model.gender,
                style = MaterialTheme.typography.titleMedium
            )
            val locationLabelString = stringResource(id = R.string.character_details_last_know_location)
            Text(
                modifier = Modifier.constrainAs(locationLabel){
                    top.linkTo(genderLabel.bottom, margin = 3.dp)
                    start.linkTo(parent.start)
                },
                text = locationLabelString,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                modifier = Modifier.constrainAs(location){
                    width = androidx.constraintlayout.compose.Dimension.preferredWrapContent
                    start.linkTo(locationLabel.end)
                    end.linkTo(parent.end)
                    baseline.linkTo(locationLabel.baseline)
                    linkTo(locationLabel.end, parent.end, bias = 0f, startMargin = 7.dp)
                },
                text = model.location,
                style = MaterialTheme.typography.titleMedium
            )
            val originLabelString = stringResource(id = R.string.character_details_origin)
            Text(
                modifier = Modifier.constrainAs(originLabel){
                    top.linkTo(location.bottom, margin = 3.dp)
                    start.linkTo(parent.start)
                },
                text = originLabelString,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                modifier = Modifier.constrainAs(origin){
                    width = androidx.constraintlayout.compose.Dimension.preferredWrapContent
                    baseline.linkTo(originLabel.baseline)
                    start.linkTo(originLabel.end)
                    end.linkTo(parent.end)
                    linkTo(originLabel.end, parent.end, bias = 0f, startMargin = 7.dp)
                },
                text = model.origin,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun BoxScope.LoadingState(){
    Column(modifier = Modifier.shimmer()) {
        Surface(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(380.dp)
            .clip(RoundedCornerShape(7.dp)),
            color = MaterialTheme.colorScheme.tertiary
        ) {}
        Surface(modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(6.dp)),
            color = MaterialTheme.colorScheme.tertiary
            ) {}
    }
}

@Composable
fun BoxScope.ErrorState(
    onReloadClick: () -> Unit
){
    val errorMsg = stringResource(id = R.string.character_details_loading_error)
    val tryAgainMsg = stringResource(id = R.string.character_details_try_again)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = errorMsg, style = MaterialTheme.typography.titleSmall)
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            modifier = Modifier.clickable {
                onReloadClick()
            },
            text = tryAgainMsg,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }

}

@Composable
fun BoxScope.LoadedState(model: CharacterDetailsModel){
    Column {
        AvatarSection(model = model)
        DetailsSection(model = model)
    }
}

@Preview
@Composable
fun LoadedStatePreview(){
    Box() {
        LoadedState(
            CharacterDetailsModel(
                "1",
                "Rick Sanchez",
                "male",
                "Alive",
                "Human",
                "Earth (C-137)",
                "Citadel of Ricks",
                "https://rickandmortyapi.com/api/character/avatar/1.jpeg"
            )
        )
    }
}
@Preview
@Composable
fun ErrorStatePreview(){
    Box() {
        ErrorState {}
    }
}

@Preview
@Composable
fun LoadingStatePreview(){
    Box() {
        LoadingState()
    }
}
