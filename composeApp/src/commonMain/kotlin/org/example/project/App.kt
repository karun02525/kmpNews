package org.example.project

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kmprestapi.composeapp.generated.resources.Res
import kmprestapi.composeapp.generated.resources.compose_multiplatform
import okio.FileSystem
import org.example.project.presentation.HomePage
import org.example.project.presentation.home.CustomNavigation
import org.example.project.presentation.home.HomeScreen

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {


        setSingletonImageLoaderFactory {
            context ->
            readAsyncImage(context)
        }

        CustomNavigation()


      /*   Navigator(HomePage()){
            SlideTransition(it)
        }*/
    }
}

fun readAsyncImage(context: PlatformContext)  =
    ImageLoader.Builder(context).memoryCachePolicy(CachePolicy.ENABLED).memoryCache {
            MemoryCache.Builder().maxSizePercent(context,0.3).strongReferencesEnabled(true).build()
        }.diskCachePolicy(CachePolicy.ENABLED).networkCachePolicy(CachePolicy.ENABLED).diskCache {
      diskCacheNew()
    }.crossfade(true).logger(DebugLogger()).build()

fun diskCacheNew():DiskCache{
    return DiskCache.Builder().directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "image_cache")
        .maxSizeBytes(1024L * 1024 * 1024)
        .build()
}