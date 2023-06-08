package com.joel.buster

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.joel.buster.navigation.BottomBarDestinations
import com.joel.buster.navigation.BusterNavHost
import com.joel.design.components.BusterNavigationBar
import com.joel.design.components.BusterNavigationBarItem
import com.joel.design.components.BusterNavigationRail
import com.joel.design.components.BusterNavigationRailItem
import com.joel.discover.DiscoverViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun BusterApp(
    windowSizeClass: WindowSizeClass,
    appState: BusterAppState = rememberBusterAppState(windowSizeClass = windowSizeClass),
    discoverViewModel: DiscoverViewModel
){

    Scaffold(
        containerColor = Color.Transparent,
        contentColor = MaterialTheme.colorScheme.onBackground,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        bottomBar = {
            if (appState.shouldShowBottomBar){
                BusterBottomBar(
                    destinations = appState.bottomBarDestinationsList,
                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                    currentDestination = appState.currentDestination,
                )
            }
        }

    ) { paddingValues ->

        Row(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .consumedWindowInsets(paddingValues)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal,
                    ),
                ),
        ) {
            if (appState.shouldShowNavRail) {
                BusterNavRail(
                    destinations = appState.bottomBarDestinationsList,
                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                    currentDestination = appState.currentDestination,
                    modifier = Modifier
                        .safeDrawingPadding(),
                )
            }
            Column(Modifier.fillMaxSize()) {
                BusterNavHost(appState = appState, discoverViewModel = discoverViewModel)
            }
        }
    }
}

@Composable
fun BusterNavRail(
    destinations: List<BottomBarDestinations>,
    onNavigateToDestination: (BottomBarDestinations) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
){

    BusterNavigationRail(modifier = modifier) {
        destinations.forEach { destinations ->
            val selected = currentDestination.isBottomBarDestinationInHierarchy(destinations)
            BusterNavigationRailItem(
                selected = selected,
                onClick = { onNavigateToDestination(destinations) },
                icon = {
                    Icon(
                        painter = painterResource(id = destinations.unSelectedIcon),
                        contentDescription = stringResource(id = destinations.iconDescription))
                },
                selectedIcon = {
                    Icon(
                        painter = painterResource(id = destinations.selectedIcon),
                        contentDescription = stringResource(id = destinations.iconDescription))
                },
                label = { Text(stringResource(destinations.iconText)) },
            )
        }
    }
}

@Composable
fun BusterBottomBar(
    destinations: List<BottomBarDestinations>,
    onNavigateToDestination: (BottomBarDestinations) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
){

    BusterNavigationBar(modifier = modifier) {
        destinations.forEach { destinations ->
            val selected = currentDestination.isBottomBarDestinationInHierarchy(destinations)
            BusterNavigationBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destinations) },
                icon = {
                    Icon(
                        painter = painterResource(id = destinations.unSelectedIcon),
                        contentDescription = stringResource(id = destinations.iconDescription))
                },
                selectedIcon = {
                    Icon(
                        painter = painterResource(id = destinations.selectedIcon),
                        contentDescription = stringResource(id = destinations.iconDescription))
                },
                label = { Text(stringResource(destinations.iconText)) },

                )
        }

    }

}

private fun NavDestination?.isBottomBarDestinationInHierarchy(destination: BottomBarDestinations) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false