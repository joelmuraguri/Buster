package com.joel.buster.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.joel.buster.BusterAppState
import com.joel.discover.DISCOVER_ROUTE
import com.joel.discover.DiscoverViewModel
import com.joel.discover.discoverComposable
import com.joel.profile.profileComposable
import com.joel.watchlist.watchListComposable

@Composable
fun BusterNavHost(
    appState: BusterAppState,
    startDestination : String = DISCOVER_ROUTE,
    modifier: Modifier = Modifier,
    discoverViewModel: DiscoverViewModel
){

    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ){
        discoverComposable(discoverViewModel = discoverViewModel)
        watchListComposable()
        profileComposable()
    }

}