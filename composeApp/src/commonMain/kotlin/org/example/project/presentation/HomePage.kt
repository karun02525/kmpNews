package org.example.project.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.domain.remote.RequestCondition

class HomePage : Screen {

    @Composable
    override fun Content() {
      val navigator = LocalNavigator.currentOrThrow
      val viewModel = getScreenModel<NewsViewModel>()


        Scaffold(
            contentColor = Color.Gray,
            topBar = {
                Text(text = "HeadLines",
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 30.dp, start = 16.dp)
                    )
            }
        ) {
            when(viewModel.allNews.value){

                is RequestCondition.ErrorCondition->{
                    val errorMessage= viewModel.allNews.value.getErrorMessageInfo()
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                       Text(errorMessage,
                           color = Color.Red
                           )
                    }
                }

                RequestCondition.IdleCondition -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Connection is Idle",
                            color = Color.Black
                        )
                    }
                }


                RequestCondition.LoadingCondition ->{
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is RequestCondition.SuccessCondition -> {
                     val lists= viewModel.allNews.value.getSuccessDataInfo()

                    LazyColumn {
                        items(lists){
                            it?.let {
                                NewsArticleItem(it){
                                    navigator.push(DetailsScreen(it))
                                }
                            }

                        }
                    }
                }
            }

        }

    }
}