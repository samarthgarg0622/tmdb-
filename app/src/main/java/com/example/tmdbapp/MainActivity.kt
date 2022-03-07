package com.example.tmdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbapp.Fragments.DetailsPage
import com.example.tmdbapp.Fragments.HomePage
import com.example.tmdbapp.Repository.MovieRepository
import com.example.tmdbapp.api.MovieService
import com.example.tmdbapp.api.RetrofitHelper
import com.example.tmdbapp.favourites.FavouritesDatabase
import com.example.tmdbapp.viewModel.MainViewModel
import com.example.tmdbapp.viewModel.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        mainViewModel =
//            ViewModelProvider(this, MainViewModelFactory(repository)).get(MainViewModel::class.java)
//        mainViewModel

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