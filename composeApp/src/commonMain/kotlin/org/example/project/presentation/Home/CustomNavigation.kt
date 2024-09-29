package org.example.project.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.example.project.presentation.home.details.DetailScreen


enum class Screen{
    Home,
    Detail
}

@Composable
fun CustomNavigation() {
    Column(
        modifier=Modifier.fillMaxSize()
    ) {
        var currentScreen by remember { mutableStateOf(Screen.Home) }
        var selectedId by remember { mutableStateOf("") }
        when(currentScreen){
           Screen.Home->{
               HomeScreen {
                   currentScreen = Screen.Detail
                   selectedId = it.idMeal
               }
           }
            Screen.Detail->{
               DetailScreen(
                   id=selectedId,
                   navigateBack = {
                       currentScreen = Screen.Home
                       selectedId =""
                   }
               )
            }
        }

    }
}