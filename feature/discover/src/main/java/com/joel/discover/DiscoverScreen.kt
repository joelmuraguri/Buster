package com.joel.discover

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.network.HttpException
import com.example.common.Constants.BASE_BACKDROP_IMAGE_URL
import com.example.network.model.FilmDTO
import java.io.IOException


@Composable
fun DiscoverScreen(
    discoverViewModel: DiscoverViewModel
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "DISCOVER", fontSize = 25.sp)
    }


    val films = discoverViewModel.trendingMoviesState.value.collectAsLazyPagingItems()


    DiscoverContent(pagingItems = films, onErrorClick = {discoverViewModel.refresh()})


}


@Composable
fun DiscoverContent(
    pagingItems : LazyPagingItems<FilmDTO>,
    onErrorClick : () -> Unit
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(195.dp)
    ) {
        when (pagingItems.loadState.refresh) {
            is LoadState.Loading -> {
                CircularProgressIndicator()
            }
            is LoadState.NotLoading -> {
                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                    items(pagingItems) { film ->
                        val imagePath = "$BASE_BACKDROP_IMAGE_URL/${film!!.backdrop_path}"
                        FilmItem(
                            imageUrl = imagePath,
                            modifier = Modifier
                                .width(130.dp)
                                .height(195.dp)
                        )
                    }
                }
            }
            is LoadState.Error -> {
                val error = pagingItems.loadState.refresh as LoadState.Error
                val errorMessage = when (error.error) {
                    is HttpException -> "Sorry, Something went wrong!\nTap to retry"
                    is IOException -> "Connection failed. Tap to retry!"
                    else -> "Failed! Tap to retry!"
                }
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(161.25.dp) // maintain the vertical space between two categories
                        .clickable {
                            onErrorClick()
                        }
                ) {
                    Text(
                        text = errorMessage,
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFFE28B8B),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            else -> {
            }
        }
    }
}

@Composable
fun FilmItem(
    imageUrl : String,
    modifier: Modifier = Modifier
){

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 5.dp
        ),
        modifier = Modifier
            .padding(12.dp)
    ) {
        SubcomposeAsyncImage(
            model =  imageUrl,
            contentDescription = imageUrl,
            modifier = Modifier
                .width(230.dp)
                .height(230.dp),
            contentScale = ContentScale.FillBounds
        ) {

            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error){
                CircularProgressIndicator(
                    modifier = Modifier
                        .scale(0.5f),
                    color = Color.Yellow
                )
            }
            else{
                SubcomposeAsyncImageContent()
            }
        }
    }
}