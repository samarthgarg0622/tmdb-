package com.example.tmdbapp.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdbapp.api.MovieService
import com.example.tmdbapp.favourites.FavouritesDatabase
import com.example.tmdbapp.models.MoviesList

class MovieRepository(
    private val movieService: MovieService,
    val database: FavouritesDatabase
) {

    private val movieLiveData = MutableLiveData<MoviesList>()
    val movies: LiveData<MoviesList>
        get() = movieLiveData

    suspend fun getMovies(page: Int): LiveData<MoviesList> {
        val result = movieService.getMovies(page)

        if (result?.body() != null) {
            movieLiveData.postValue(result.body())
        }



        return movies
    }
}