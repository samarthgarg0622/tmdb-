package com.example.tmdbapp.Fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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


    var addedToFavourites: Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movie_title.setText(movieDetails.title)
        movie_overview.setText(movieDetails.overview)
        movie_rating.setRating(movieDetails.vote_average.toFloat() / 2)
        movie_release_date.setText(movieDetails.release_date)


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
            updatebutton(it)
        }


        Log.d("fav1", "$addedToFavourites")
        updatebutton(addedToFavourites)

        favourite_button.setOnClickListener {
            addedToFavourites = !addedToFavourites
            updatebutton(addedToFavourites)
            mainViewModel.updateDatabase(addedToFavourites, movieDetails)

        }
    }


    private fun updatebutton(addedToFavourites: Boolean) {

        Log.d("fav1", "$addedToFavourites")
        if (addedToFavourites) {
            favourite_button.setImageResource(R.drawable.ic_baseline_favorite_24)

        } else
            favourite_button.setImageResource(R.drawable.ic_baseline_favorite_border_24)

    }


}