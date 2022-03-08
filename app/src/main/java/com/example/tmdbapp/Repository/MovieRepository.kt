package com.example.tmdbapp.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdbapp.api.MovieService
import com.example.tmdbapp.favourites.FavouritesDao
import com.example.tmdbapp.models.MovieDetails
import com.example.tmdbapp.models.MoviesList
import javax.inject.Inject

class MovieRepository
@Inject constructor(
    private val movieService: MovieService,
    private val favouritesDao: FavouritesDao
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

    suspend fun getFavourites(id: Int) = favouritesDao.getFavourites(id)

    suspend fun addFavourite(movieDetails: MovieDetails) = favouritesDao.addFavourite(movieDetails)

    suspend fun removeFromFavourites(id: Int) = favouritesDao.removeFromFavourites(id)
}
