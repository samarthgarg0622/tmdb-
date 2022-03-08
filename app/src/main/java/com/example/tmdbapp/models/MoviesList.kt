package com.example.tmdbapp.models

import com.google.gson.annotations.SerializedName

data class MoviesList(
    @SerializedName("page")
    val page: Int,
    val results: List<MovieDetails>,
    val total_results: Int,
    val total_pages: Int
)
