package com.joel.buster

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.joel.buster.navigation.BottomBarDestinations
import com.joel.discover.DISCOVER_ROUTE
import com.joel.discover.navigateToDiscover
import com.joel.profile.PROFILE_ROUTE
import com.joel.profile.navigateToProfile
import com.joel.watchlist.WATCHLIST_ROUTE
import com.joel.watchlist.navigateToWatchList
import androidx.tracing.trace

@Composable
fun rememberBusterAppState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController()
) : BusterAppState{
    return remember(
        navController, windowSizeClass
    ) {
        BusterAppState(navController, windowSizeClass)
    }

}

@Stable
class BusterAppState(
    val navController : NavHostController,
    val windowSizeClass: WindowSizeClass
) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: BottomBarDestinations?
        @Composable get() = when (currentDestination?.route) {
            DISCOVER_ROUTE -> BottomBarDestinations.DISCOVER
            WATCHLIST_ROUTE -> BottomBarDestinations.WATCHLIST
            PROFILE_ROUTE -> BottomBarDestinations.PROFILE
            else -> null
        }

    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    val shouldShowNavRail: Boolean
        get() = !shouldShowBottomBar

    val bottomBarDestinationsList: List<BottomBarDestinations> = BottomBarDestinations.values().asList()

    fun navigateToTopLevelDestination(bottomBarDestinations: BottomBarDestinations) {
        trace("Navigation: ${bottomBarDestinations.name}") {
            val levelNavOptions = navOptions {
                // Pop up to the start destination of the graph to
                // avoid building up a large stack of destinations
                // on the back stack as users select items
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // re selecting the same item
                launchSingleTop = true
                // Restore state when re selecting a previously selected item
                restoreState = true
            }

            when (bottomBarDestinations) {
                BottomBarDestinations.DISCOVER -> navController.navigateToDiscover(levelNavOptions)
                BottomBarDestinations.WATCHLIST -> navController.navigateToWatchList(levelNavOptions)
                BottomBarDestinations.PROFILE -> navController.navigateToProfile(levelNavOptions)
            }
        }
    }
}