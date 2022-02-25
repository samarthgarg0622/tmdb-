package com.example.tmdbapp.models

data class MoviesList(
    val page:Int,
    val results: List<MovieDetails>,
    val total_results:Int,
    val total_pages:Int
)
