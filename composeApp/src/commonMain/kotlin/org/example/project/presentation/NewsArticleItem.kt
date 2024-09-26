package org.example.project.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import org.example.project.domain.model.Articles

@Composable
fun NewsArticleItem(articles: Articles,onClick:(Articles)->Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(18.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.clickable {
                onClick(articles)
            }
        ) {
            articles.urlImage?.let {
                AsyncImage(
                    modifier = Modifier
                        .size(121.dp)
                        .clip(shape = RoundedCornerShape(11.dp)),
                    model = articles.urlImage,
                    contentDescription = "",
                    contentScale = ContentScale.Crop
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
        }
    }
}