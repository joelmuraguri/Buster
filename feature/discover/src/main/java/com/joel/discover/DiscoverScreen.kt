package com.joel.discover

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.common.Constants.BASE_POSTER_IMAGE_URL
import com.example.network.model.FilmDTO
import kotlinx.coroutines.flow.Flow


@Composable
fun DiscoverScreen(){
//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = Modifier
//            .fillMaxSize()
//    ) {
//        Text(text = "DISCOVER", fontSize = 25.sp)
//    }

    PopularContent()
}

@Composable
fun PopularContent(
    viewModel: DiscoverViewModel = hiltViewModel()
){
    Box(contentAlignment = Alignment.Center) {
        Column {
            Text(
                text = "Popular Tv Shows",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            LazyRowScrollableItems(pagingItems = viewModel.popularFilms )
        }
    }
}


@Composable
fun LazyRowScrollableItems(
    pagingItems : Flow<PagingData<FilmDTO>>
){

    val popularFilms = pagingItems.collectAsLazyPagingItems()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(195.dp)
    ) {
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ){
            items(count = popularFilms.itemCount, key = popularFilms.itemKey(), contentType = popularFilms.itemContentType()
    ) { index ->
        val item = popularFilms[index]
                FilmItem(filmDTO = item!!)
            }

           popularFilms.apply {
               when{
                   loadState.refresh is LoadState.Loading ->{
                       item {
                           Box(
                               contentAlignment = Alignment.Center,
                               modifier = Modifier.fillMaxWidth()
                           ) {
                               CircularProgressIndicator(
                                   modifier = Modifier
                                       .scale(0.5f)
                                       .align(Alignment.Center)
                                       .fillMaxWidth(),
                                   color = Color.Yellow
                               )
                           }

                       }
                   }
                   loadState.append is LoadState.Loading -> {
                       item {
                           Box(
                               contentAlignment = Alignment.Center,
                               modifier = Modifier.fillMaxWidth()
                           ) {
                               CircularProgressIndicator(
                                   modifier = Modifier
                                       .scale(0.5f)
                                       .align(Alignment.Center)
                                       .fillMaxWidth(),
                                   color = Color.Yellow
                               )
                           }
                       }
                   }
                   loadState.refresh is LoadState.Error -> {
                       val error = popularFilms.loadState.refresh as LoadState.Error
                       item {
                           Column(
                               verticalArrangement = Arrangement.Center,
                               horizontalAlignment = Alignment.CenterHorizontally
                           ) {
                               error.error.localizedMessage?.let {
                                   Text(
                                       text = it,
                                       color = Color.Red
                                   )
                               }
                               Spacer(modifier = Modifier.height(8.dp))
                               Button(onClick = { refresh() }) {
                                   Text(text = "Retry")
                               }
                           }
                       }
                   }
                   loadState.append is LoadState.Error -> {
                       val error = popularFilms.loadState.append as LoadState.Error
                       item {
                           Column(
                               verticalArrangement = Arrangement.Center,
                               horizontalAlignment = Alignment.CenterHorizontally
                           ) {
                               error.error.localizedMessage?.let {
                                   Text(
                                       text = it,
                                       color = Color.Red
                                   )
                               }
                               Spacer(modifier = Modifier.height(8.dp))
                               Button(onClick = { refresh() }) {
                                   Text(text = "Retry")
                               }
                           }
                       }
                   }
               }
           }
        }
    }
}


@Composable
fun FilmItem(
    filmDTO: FilmDTO,
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        
        Card(
            shape = RectangleShape
        ) {

//            val imagePath = if (landscape) "$BASE_BACKDROP_IMAGE_URL/${filmDTO!!.backdrop_path}" else "$BASE_POSTER_IMAGE_URL/${filmDTO!!.poster_path}"

            SubcomposeAsyncImage(
                model = "$BASE_POSTER_IMAGE_URL/${filmDTO.poster_path}",
                contentDescription = filmDTO.title,
                modifier = Modifier
                    .width(130.dp)
                    .height(195.dp),
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
}

@Preview("Film Item contents")
@Preview("Film Item (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAFilmItemRail() {




}