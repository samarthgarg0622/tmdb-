package com.example.tmdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbapp.Fragments.DetailsPage
import com.example.tmdbapp.Fragments.HomePage
import com.example.tmdbapp.Repository.MovieRepository
import com.example.tmdbapp.api.MovieService
import com.example.tmdbapp.favourites.FavouritesDao
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

        val homePageFragment = HomePage(
            mainViewModel,
            onPress = {


                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fl, DetailsPage(mainViewModel, it))
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