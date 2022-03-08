package com.example.tmdbapp.favourites

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tmdbapp.models.MovieDetails

@Database(entities = [MovieDetails::class], version = 1)
abstract class FavouritesDatabase : RoomDatabase() {

    abstract fun favouritesDao(): FavouritesDao
}
