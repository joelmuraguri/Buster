package com.joel.domain.model

import com.joel.domain.model.film.Film

data class UserSearchResult(
    val films : List<Film> = emptyList()
)
