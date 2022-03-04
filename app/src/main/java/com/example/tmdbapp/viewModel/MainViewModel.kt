package com.example.tmdbapp.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.Repository.MovieRepository
import com.example.tmdbapp.models.MovieDetails
import com.example.tmdbapp.models.MoviesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    val repository: MovieRepository,
) : ViewModel() {
    private val _favState = MutableLiveData<Boolean>()
    val favState: LiveData<Boolean> = _favState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovies(1)
        }

    }

    val movies: LiveData<MoviesList>
        get() = repository.movies

    fun checkFavourites(movieDetails: MovieDetails) {
        viewModelScope.launch {
            _favState.postValue(repository.database.favouritesDao().getFavourites(movieDetails.id))
        }
    }

    fun updateDatabase(addedToFavourites: Boolean, movieDetails: MovieDetails) {
        when (addedToFavourites) {
            true ->
                viewModelScope.launch {
                    repository.database.favouritesDao().addFavourite(movieDetails)

                }

            false ->
                viewModelScope.launch {
                    repository.database.favouritesDao().removeFromFavourites(movieDetails.id)
                }
        }


//        return !addedToFavourites
    }
}