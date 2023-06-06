package com.joel.buster.navigation

import com.joel.buster.R
import com.joel.buster.ui.icons.BusterIcons

enum class BottomBarDestinations(
    val selectedIcon : Int,
    val unSelectedIcon : Int,
    val iconText : Int,
    val iconDescription : Int,
) {
    DISCOVER(
        selectedIcon = BusterIcons.Explore,
        unSelectedIcon = BusterIcons.ExploreBorder,
        iconText = R.string.explore,
        iconDescription = R.string.discover_icon_info_description
    ),
    WATCHLIST(
        selectedIcon = BusterIcons.WatchList,
        unSelectedIcon = BusterIcons.WatchListBorder,
        iconText = R.string.watchlist,
        iconDescription = R.string.watchlist_icon_info_description

    ),
    PROFILE(
        selectedIcon = BusterIcons.Profile,
        unSelectedIcon = BusterIcons.ProfileBorder,
        iconText = R.string.profile,
        iconDescription = R.string.profile_icon_info_description

    )
}