package com.example.tmdbapp.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tmdbapp.api.MovieService
import com.example.tmdbapp.favourites.FavouritesDatabase
import com.example.tmdbapp.models.MovieDetails
import com.example.tmdbapp.models.MoviesList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(private val movieService: MovieService) {

    private val movieLiveData = MutableLiveData<MoviesList>()
    val movies : LiveData<MoviesList>

    get() = movieLiveData

    suspend fun getMovies(page:Int) : LiveData<MoviesList>{
        val result = movieService.getMovies(page)

        if(result?.body()!=null){
            movieLiveData.postValue(result.body())
        }



//        result.enqueue(object : Callback<MovieDetails>{
//            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
//                val movies = response.body()
//                if(movies!=null){
//                    Log.d("myTag",movies.title)
//                }
//            }
//
//            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
//                Log.d("myTag","Failed")
//            }
//
//        })

        return movies
    }
}