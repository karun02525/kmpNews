package org.example.project.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import org.example.project.domain.model.Articles

data class DetailsScreen(val articles:Articles) :Screen {

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow


        Scaffold(
            contentColor = Color.Gray,
            topBar = {
                Text(text = "Complete Story",
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 30.dp, start = 16.dp)
                )
            },
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier.padding(it)
            ) {

                articles.urlImage?.let {
                    AsyncImage(
                        modifier = Modifier
                            .padding(16.dp)
                            .height(200.dp)
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(11.dp)),
                        model = articles.urlImage,
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                }

                articles.author?.let {
                    Text(text = it,
                        maxLines = 3,
                        fontSize = 17.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(
                            vertical = 9.dp, horizontal = 13.dp
                        ),
                        color = Color.Black
                    )
                }

                articles.title?.let {
                    Text(text = it,
                        maxLines = 3,
                        fontSize = 17.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(
                            vertical = 9.dp, horizontal = 13.dp
                        ),
                        color = Color.Black
                    )
                }

                articles.content?.let {
                    Text(text = it,
                        maxLines = 3,
                        fontSize = 17.sp,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(
                            vertical = 9.dp, horizontal = 13.dp
                        ),
                        color = Color.Black
                    )
                }

                Button(onClick = {
                    navigator.pop()
                },
                    modifier=Modifier.fillMaxWidth()

                ){
                    Text("Back")
                }

            }
        }
    }

}