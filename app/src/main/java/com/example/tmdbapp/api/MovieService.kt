package com.example.tmdbapp.api

import com.example.tmdbapp.models.MoviesList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "e96bf69e308c87d0ca9c132726381186"

interface MovieService {
    @GET("3/movie/top_rated?api_key=$API_KEY")
    suspend fun getMovies(@Query("page") page: Int): Response<MoviesList>
}
