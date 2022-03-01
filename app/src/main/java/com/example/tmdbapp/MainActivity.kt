package com.example.tmdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.tmdbapp.Fragments.DetailsPage
import com.example.tmdbapp.Fragments.HomePage
import com.example.tmdbapp.Repository.MovieRepository
import com.example.tmdbapp.api.MovieService
import com.example.tmdbapp.api.RetrofitHelper
import com.example.tmdbapp.models.MovieDetails
import com.example.tmdbapp.viewModel.MainViewModel
import com.example.tmdbapp.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homePageFragment = HomePage(
            onPress = {
                val detailsPage = DetailsPage(it)

                supportFragmentManager.beginTransaction().apply {
                    replace(R.id.fl,detailsPage)
                    commit()
                    addToBackStack("homePageFragment")

                }
            }
        )

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl,homePageFragment)
            commit()
        }

    }
}