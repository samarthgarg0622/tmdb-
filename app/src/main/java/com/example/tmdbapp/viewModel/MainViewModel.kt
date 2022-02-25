package com.example.tmdbapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tmdbapp.Repository.MovieRepository
import com.example.tmdbapp.models.MovieDetails
import com.example.tmdbapp.models.MoviesList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MovieRepository):ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getMovies(1)
        }

    }
    val movies : LiveData<MoviesList>
    get() = repository.movies
}