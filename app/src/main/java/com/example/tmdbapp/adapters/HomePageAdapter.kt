package com.example.tmdbapp.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tmdbapp.R
import com.example.tmdbapp.models.MovieDetails
import com.example.tmdbapp.models.MoviesList
import kotlinx.android.synthetic.main.item_layout.view.*

class HomePageAdapter(
    val moviesList: MoviesList,
    private val onPress:(MovieDetails)->Unit
    ):RecyclerView.Adapter<HomePageAdapter.HomePageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomePageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        val viewHolder = HomePageViewHolder(view)
//        view.setOnClickListener {
//            listener.onArticleClicked(articles[viewholder.adapterPosition])
//        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: HomePageViewHolder, position: Int) {
        when(holder){
            is HomePageViewHolder->{
                holder.bind(moviesList.results.get(position),onPress)
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d("apiCall",moviesList.results.size.toString())
        return moviesList.results.size
    }

    class HomePageViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){

        val movieTitle = itemview.movieTitle
        val movieRating = itemview.movieRating
        val movieImage = itemview.movieImage

        fun bind(movieDetails: MovieDetails,onPress:(MovieDetails)->Unit){
            movieTitle.setText(movieDetails.title)
            movieRating.setText(movieDetails.vote_average.toString())

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