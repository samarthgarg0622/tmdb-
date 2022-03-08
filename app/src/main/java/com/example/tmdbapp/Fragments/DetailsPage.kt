package com.example.tmdbapp.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tmdbapp.R
import com.example.tmdbapp.models.MovieDetails
import com.example.tmdbapp.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_details_page.*

class DetailsPage(
    private val mainViewModel: MainViewModel,
    private val movieDetails: MovieDetails
) : Fragment() {

    private var addedToFavourites = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie_title.text = movieDetails.title
        movie_overview.text = movieDetails.overview
        movie_rating.rating = movieDetails.vote_average.toFloat() / 2
        movie_release_date.text = movieDetails.release_date

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(view.context)
            .applyDefaultRequestOptions(requestOptions)
            .load("https://image.tmdb.org/t/p/original${movieDetails.poster_path}")
            .into(movie_poster)

        Glide.with(view.context)
            .applyDefaultRequestOptions(requestOptions)
            .load("https://image.tmdb.org/t/p/original${movieDetails.poster_path}")
            .into(movie_backdrop)

        mainViewModel.checkFavourites(movieDetails)

        mainViewModel.favState.observe(viewLifecycleOwner) {
            updateButton(it)
        }

        Log.d("fav1", "$addedToFavourites")
        updateButton(addedToFavourites)

        favourite_button.setOnClickListener {
            addedToFavourites = !addedToFavourites
            updateButton(addedToFavourites)
            mainViewModel.updateDatabase(addedToFavourites, movieDetails)
        }
    }

    private fun updateButton(addedToFavourites: Boolean) {

        Log.d("fav1", "$addedToFavourites")
        if (addedToFavourites) {
            favourite_button.setImageResource(R.drawable.ic_baseline_favorite_24)
        } else
            favourite_button.setImageResource(R.drawable.ic_baseline_favorite_border_24)
    }
}
