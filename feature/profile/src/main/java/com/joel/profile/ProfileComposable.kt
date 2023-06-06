package com.joel.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation

const val PROFILE_ROUTE = "profile"

fun NavController.navigateToProfile(navOptions: NavOptions?= null){
    this.navigate(PROFILE_ROUTE,navOptions)
}

fun NavGraphBuilder.profileComposable(){
    composable(route = PROFILE_ROUTE){
        ProfileScreen()
    }
}