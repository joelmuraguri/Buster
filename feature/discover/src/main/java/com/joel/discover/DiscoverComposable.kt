package com.joel.discover

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val DISCOVER_ROUTE = "discover"

fun NavController.navigateToDiscover(navOptions: NavOptions ?= null){
    this.navigate(DISCOVER_ROUTE,navOptions)
}

fun NavGraphBuilder.discoverComposable(discoverViewModel: DiscoverViewModel){
    composable(route = DISCOVER_ROUTE){
        DiscoverScreen(discoverViewModel)
    }
}