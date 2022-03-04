package com.example.tmdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbapp.Fragments.DetailsPage
import com.example.tmdbapp.Fragments.HomePage
import com.example.tmdbapp.Repository.MovieRepository
import com.example.tmdbapp.api.MovieService
import com.example.tmdbapp.api.RetrofitHelper
import com.example.tmdbapp.favourites.FavouritesDatabase
import com.example.tmdbapp.viewModel.MainViewModel
import com.example.tmdbapp.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lateinit var database: FavouritesDatabase
        lateinit var mainViewModel: MainViewModel
        val movieService = RetrofitHelper.getInstance().create(MovieService::class.java)


        database = FavouritesDatabase.getFavouritesDatabase(this)

        val repository = MovieRepository(movieService, database)

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)


        val homePageFragment = HomePage(
            mainViewModel,
            onPress = {
                val detailsPage = DetailsPage(mainViewModel, it)

                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fl, detailsPage)
                    commit()
                    addToBackStack("homePageFragment")

                }
            }
        )

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl, homePageFragment)
            commit()
        }

    }
}