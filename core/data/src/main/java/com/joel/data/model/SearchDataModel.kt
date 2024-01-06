package com.joel.data.model

data class SearchDataModel(
    val title : String,
    val postUrl : String,
    val releaseDate : String,
    val genres : List<String>,
    val filmType : String
)
