package com.joel.domain.model

import com.joel.domain.model.film.filters.ThemeConfig

data class User(
    val darkThemeConfig: ThemeConfig,
    val useDynamicColor: Boolean,
    val shouldHideOnboarding: Boolean,
)
