package com.example.tmdbapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.Repository.MovieRepository
import com.example.tmdbapp.models.MovieDetails
import com.example.tmdbapp.models.MoviesList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MovieRepository,
) : ViewModel() {
    private val _favState = MutableLiveData<Boolean>()
    val favState: LiveData<Boolean> = _favState

    var pageNumber: Int = 1
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovies(pageNumber)
        }
    }

    val movies: LiveData<MoviesList>
        get() = repository.movies

    fun checkFavourites(movieDetails: MovieDetails) {
        viewModelScope.launch {
            _favState.postValue(repository.getFavourites(movieDetails.id))
        }
    }

    fun updateDatabase(addedToFavourites: Boolean, movieDetails: MovieDetails) {
        when (addedToFavourites) {
            true ->
                viewModelScope.launch {
                    repository.addFavourite(movieDetails)
                }

            false ->
                viewModelScope.launch {
                    repository.removeFromFavourites(movieDetails.id)
                }
        }

//        return !addedToFavourites
    }
}
