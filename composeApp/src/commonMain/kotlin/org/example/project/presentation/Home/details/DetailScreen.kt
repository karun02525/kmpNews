package org.example.project.presentation.home.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.example.project.presentation.home.viewmodel.DetailsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun DetailScreen(
    viewModel: DetailsViewModel = koinViewModel<DetailsViewModel>(),
    id:String,
    navigateBack:()->Unit = {}
) {
    val detailState by viewModel.detailState.collectAsState()
    LaunchedEffect(true){
        viewModel.fetchMealById(id)
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        IconButton(onClick = navigateBack, modifier = Modifier.padding(top=10.dp)){
            Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "back")
        }

           if(detailState.loading){
               CircularProgressIndicator()
           }
           if(detailState.error !=null){
               Text(detailState.error.orEmpty())
           }

        LazyColumn {
            item{

                detailState.meals?.let {meal->
                    AsyncImage(meal.strMealThumb,
                        contentDescription = "Image",
                        modifier = Modifier.fillMaxWidth()
                            .height(400.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop

                        )
                   Spacer(Modifier.height(16.dp))
                    Surface(
                        modifier = Modifier.fillMaxWidth() ,
                        color = MaterialTheme.colorScheme.surface.copy(0.5f)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(8.dp).fillMaxWidth()
                        ) {
                            Text(text = meal.strMeal,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )
                        }
                    }
                    Spacer(Modifier.height(8.dp))
                    Text(text = meal.strInstructions,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(text = meal.strArea,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }
        }

    }
}