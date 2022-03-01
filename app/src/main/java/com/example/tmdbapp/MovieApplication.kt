package com.example.tmdbapp

import android.app.Application
import com.example.tmdbapp.Repository.MovieRepository
import com.example.tmdbapp.api.MovieService
import com.example.tmdbapp.api.RetrofitHelper
import com.example.tmdbapp.favourites.FavouritesDatabase

class MovieApplication:Application() {
    lateinit var movieRepository: MovieRepository
    override fun onCreate() {
        super.onCreate()
        initialise()
    }

    private fun initialise() {
        val movieService = RetrofitHelper.getInstance().create(MovieService::class.java)
//        val database = FavouritesDatabase.getFavouritesDatabase(applicationContext)
        movieRepository = MovieRepository(movieService)

    }
}