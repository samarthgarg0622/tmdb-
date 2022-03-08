package com.example.tmdbapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tmdbapp.R
import com.example.tmdbapp.models.MovieDetails
import com.example.tmdbapp.models.MoviesList
import kotlinx.android.synthetic.main.item_layout.view.*

class HomePageAdapter(
    private val moviesList: MoviesList,
    private val onPress: (MovieDetails) -> Unit
) : RecyclerView.Adapter<HomePageAdapter.HomePageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return HomePageViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomePageViewHolder, position: Int) {
        when (holder) {
            is HomePageViewHolder -> holder.bind(moviesList.results.get(position), onPress)
        }
    }

    override fun getItemCount(): Int {
        Log.d("apiCall", moviesList.results.size.toString())
        return moviesList.results.size
    }

    class HomePageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val movieTitle: TextView = itemView.movieTitle
        val movieRating: TextView = itemView.movieRating
        val movieImage: ImageView = itemView.movieImage

        fun bind(movieDetails: MovieDetails, onPress: (MovieDetails) -> Unit) {
            movieTitle.text = movieDetails.title
            movieRating.text = movieDetails.vote_average.toString()

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load("https://image.tmdb.org/t/p/original${movieDetails.poster_path}")
                .into(movieImage)
            itemView.setOnClickListener {
                onPress(movieDetails)
            }
        }
    }
}
