package com.example.tmdbapp.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdbapp.R
import com.example.tmdbapp.adapters.HomePageAdapter
import com.example.tmdbapp.models.MovieDetails
import com.example.tmdbapp.viewModel.MainViewModel
import kotlinx.android.synthetic.main.fragment_home_page.*

class HomePage(
    private val mainViewModel: MainViewModel,
    private val onPress: (MovieDetails) -> Unit
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.movies.observe(
            viewLifecycleOwner,
            Observer {
                Log.d("apiCall", it.toString())

                val adapter = HomePageAdapter(it, onPress)
                moviesList.adapter = adapter
                moviesList.layoutManager = GridLayoutManager(activity, 3)

                moviesList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                })
            }
        )
    }
}
