package org.example.project.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import kmprestapi.composeapp.generated.resources.Res
import kmprestapi.composeapp.generated.resources.compose_multiplatform
import org.example.project.domain.model.Meal
import org.example.project.presentation.home.viewmodel.HomeViewModel
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = koinViewModel<HomeViewModel>(),onClick:(Meal)->Unit) {

    val homeState by viewModel.homeState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(homeState.isLoading){
            CircularProgressIndicator()
        }
        if (homeState.error !=null){
            Text(homeState.error.orEmpty())
        }

        LazyVerticalGrid(columns = GridCells.Fixed(2)){
            items(homeState.meals){
                FoodItem(it,onClick)
            }
        }

    }

}

@Composable
fun FoodItem(item: Meal, onClick:(Meal)->Unit) {
    Box(
        modifier = Modifier.padding(16.dp).clickable { onClick(item) }
    ) {

        AsyncImage(
            model = item.strMealThumb,
            modifier = Modifier.fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp))
            ,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            error = painterResource(Res.drawable.compose_multiplatform),
            onError = {state->
                state.result.throwable.printStackTrace()
            }
        )
        Surface(
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.BottomCenter),
            color = MaterialTheme.colorScheme.surface.copy(0.5f)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(8.dp).fillMaxWidth()
            ) {
                Text(text = item.strMeal,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )

            }
        }
    }
}