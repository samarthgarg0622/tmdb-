package com.example.tmdbapp.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tmdbapp.R
import com.example.tmdbapp.Repository.MovieRepository
import com.example.tmdbapp.adapters.HomePageAdapter
import com.example.tmdbapp.api.MovieService
import com.example.tmdbapp.api.RetrofitHelper
import com.example.tmdbapp.favourites.FavouritesDatabase
import com.example.tmdbapp.models.MovieDetails
import com.example.tmdbapp.viewModel.MainViewModel
import com.example.tmdbapp.viewModel.MainViewModelFactory
import kotlinx.android.synthetic.main.fragment_home_page.*

class HomePage (
    private val onPress:(MovieDetails)->Unit

        ): Fragment() {

    lateinit var adapter: HomePageAdapter
    private lateinit var mainViewModel: MainViewModel
    val movieService = RetrofitHelper.getInstance().create(MovieService::class.java)
//    val database = FavouritesDatabase.getFavouritesDatabase(context)
    val repository = MovieRepository(movieService)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)

        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.movies.observe(viewLifecycleOwner, Observer {
            Log.d("apiCall",it.toString())

            adapter = HomePageAdapter(it, onPress)
            moviesList.adapter = adapter
            moviesList.layoutManager = GridLayoutManager(activity,3)

        })
//        repository.getMovies(1)


    }

}