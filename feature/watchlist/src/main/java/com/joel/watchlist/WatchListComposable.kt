package com.joel.watchlist

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val WATCHLIST_ROUTE = "watchlist"

fun NavController.navigateToWatchList(navOptions: NavOptions ?= null){
    this.navigate(WATCHLIST_ROUTE,navOptions)
}

fun NavGraphBuilder.watchListComposable(){
    composable(route = WATCHLIST_ROUTE){
        WatchListScreen()
    }
}