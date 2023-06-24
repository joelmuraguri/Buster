package com.joel.discover

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.common.Constants.BASE_POSTER_IMAGE_URL
import com.example.common.Constants.IMAGE_URL
import com.example.network.model.FilmDTO
import com.joel.design.components.TranslucentStatusBarLayout
import com.joel.design.ext.landscapeCutoutPadding
import kotlinx.coroutines.flow.Flow


@Composable
fun DiscoverScreen(
    discoverViewModel: DiscoverViewModel = hiltViewModel()
){

    val state = discoverViewModel.latestFilmState.value

    val scrollState = rememberScrollState()
    TranslucentStatusBarLayout(scrollState = scrollState, distanceUntilAnimated = 200.dp) {
        Column(
            modifier = Modifier
                .navigationBarsPadding()
        ) {
            Box {
//                Image(
//                    painter = painterResource(R.drawable.background),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(200.dp),
////                        .bannerParallax(scrollState),
//                    contentScale = ContentScale.Crop,
//                    alignment = Alignment.TopCenter
//                )

                state.latestFilm?.let {film ->
                    ImageBanner(
                        imageURL = "$IMAGE_URL/${film.poster_path}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                    )
                    Text(
                        text = film.title,
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(
                                start = 24.dp,
                                top = 16.dp
                            )
                            .landscapeCutoutPadding()
                    )

                }
                if (state.error.isNotBlank()){
                    Text(
                        text = state.error,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
                if (state.isLoading){
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .scale(0.5f)
                    )
                }
                Box(
                    modifier = Modifier
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.secondaryContainer.copy(
                                        alpha = 0.5f
                                    )
                                )
                            )
                        )
                        .fillMaxWidth()
                        .height(200.dp)
                ) { }
                Text(
                    text = "Latest",
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(
                            start = 24.dp,
                            top = 16.dp
                        )
                        .landscapeCutoutPadding()
                )
            }
            TabLayout(viewModel = discoverViewModel, scrollState = scrollState)
        }
    }
}

@Composable
fun LatestFilmContents(
    filmDTO: FilmDTO
){

    Column {
        Text(text = filmDTO.title)
    }


}



@Preview("Latest Film Contents")
@Preview("Latest Film Contents(dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewLatestFilmContents() {

}


@Composable
fun NowPlayingContents(discoverViewModel: DiscoverViewModel, scrollState: ScrollState){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .verticalScroll(scrollState)
    ) {
        TrendingFilms(viewModel = discoverViewModel)
        PopularFilms(viewModel = discoverViewModel)
        TopRatedFilms(viewModel = discoverViewModel)

    }
}

@Composable
fun PopularFilms(
    viewModel: DiscoverViewModel
){
    Box(contentAlignment = Alignment.Center) {
        Column {
            Text(
                text = "Most Popular",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            LazyRowScrollableItems(pagedFilms = viewModel.popularFilms)
        }
    }
}

@Composable
fun TrendingFilms(
    viewModel: DiscoverViewModel
){
    Box(contentAlignment = Alignment.Center) {
        Column {
            Text(
                text = "Trending",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            LazyRowScrollableItems(pagedFilms = viewModel.trendingFilms)
        }
    }
}


@Composable
fun TopRatedFilms(
    viewModel: DiscoverViewModel
){
    Box(contentAlignment = Alignment.Center) {
        Column {
            Text(
                text = "Top Rated",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            LazyRowScrollableItems(pagedFilms = viewModel.topRatedFilms)
        }
    }
}

@Composable
fun UpcomingFilms(
    viewModel: DiscoverViewModel
){
    Box(contentAlignment = Alignment.Center) {

        UpcomingFilmsScrollableItems(popularFilms = viewModel.upcomingFilms)

    }
}

@Composable
fun UpcomingFilmsScrollableItems(
    popularFilms : Flow<PagingData<FilmDTO>>
){
    val lazyFilmItems = popularFilms.collectAsLazyPagingItems()


    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ){
        items(lazyFilmItems.itemCount){ index ->
            lazyFilmItems[index]?.let { film ->
                FilmItem(
                    filmDTO = film,
                )
            }
        }

        lazyFilmItems.apply {
            when{

                loadState.refresh is LoadState.Loading -> {
                    item {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .scale(0.5f)
                                    .align(Alignment.Center),
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
                    val error = lazyFilmItems.loadState.refresh as LoadState.Error
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
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
                loadState.append is LoadState.Error -> {
                    val error = lazyFilmItems.loadState.append as LoadState.Error
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
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
fun TabLayout(viewModel: DiscoverViewModel, scrollState: ScrollState) {
    val tabIndex by viewModel.tabIndex.collectAsState()
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex, modifier = Modifier
            .padding(end = 100.dp)
        ) {
            viewModel.tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { viewModel.updateTabIndex(index) },
                )
            }
        }
        when (tabIndex) {
            0 -> NowPlayingContents(discoverViewModel = viewModel,scrollState)
            1 -> UpcomingFilms(viewModel = viewModel)
        }
    }
}

@Composable
fun LazyRowScrollableItems(
    pagedFilms : Flow<PagingData<FilmDTO>>
){
    
    val lazyPagedFilms = pagedFilms.collectAsLazyPagingItems()

    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ){
        items(lazyPagedFilms){ filmDTO ->
            FilmItem(filmDTO = filmDTO!!)
        }

        lazyPagedFilms.apply {
            when{

                loadState.refresh is LoadState.Loading -> {
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
                    val error = lazyPagedFilms.loadState.refresh as LoadState.Error
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
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
                loadState.append is LoadState.Error -> {
                    val error = lazyPagedFilms.loadState.append as LoadState.Error
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



@Composable
fun FilmItem(
    filmDTO: FilmDTO,
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(12.dp)
    ) {
        
        Card(
            shape = RectangleShape
        ) {
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

@Composable
fun ImageBanner(
    imageURL : String,
    modifier: Modifier = Modifier
){

    SubcomposeAsyncImage(
        model = imageURL,
        contentDescription = "",
        modifier = modifier,
        contentScale = ContentScale.Crop,
        alignment = Alignment.TopCenter
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



