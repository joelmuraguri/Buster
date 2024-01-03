package com.joel.domain

import com.joel.domain.model.film.Film

data class UserSearchResult(
    val films : List<Film> = emptyList()
)
