package com.example.tmdbapp.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tmdbapp.R
import com.example.tmdbapp.favourites.FavouritesDatabase
import com.example.tmdbapp.models.MovieDetails
import kotlinx.android.synthetic.main.fragment_details_page.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class DetailsPage(private val movieDetails: MovieDetails) : Fragment() {
    lateinit var database: FavouritesDatabase
    var addedToFavourites:Boolean = false
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
        movie_rating.setRating(movieDetails.vote_average.toFloat()/2)
        movie_release_date.setText(movieDetails.release_date)

        database = Room.databaseBuilder(
            requireContext(),
            FavouritesDatabase::class.java,
            "favouritesDB")
            .build()

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


        favourite_button.setOnClickListener {
            addedToFavourites = !addedToFavourites
            updatebutton()

        }
    }

    private fun updatebutton() {
        Toast.makeText(activity,addedToFavourites.toString(),Toast.LENGTH_SHORT).show()
        when(addedToFavourites) {
            true -> {
                favourite_button.setImageResource(R.drawable.ic_baseline_favorite_24)
                GlobalScope.launch {
                    database.favouritesDao().addFavourite(
                        movieDetails
                    )
                }

            }


            false -> {
                favourite_button.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                GlobalScope.launch {
                    database.favouritesDao().removeFromFavourites(
                        movieDetails.title
                    )
                }
            }

        }
    }

}