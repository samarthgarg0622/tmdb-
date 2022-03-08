package com.example.tmdbapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity(tableName = "favourites")
data class MovieDetails @Inject constructor(

    @PrimaryKey
    val id: Int,
    val adult: Boolean,
    val title: String,
    val overview: String,
    val original_language: String,
    val vote_average: Double,
    val poster_path: String,
    val release_date: String,

)
