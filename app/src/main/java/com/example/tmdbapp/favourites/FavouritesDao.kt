package com.example.tmdbapp.favourites

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbapp.models.MovieDetails

@Dao
interface FavouritesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavourite(movieDetails: MovieDetails)

    @Query("SELECT * FROM favourites")
    suspend fun getFavourites() :List<MovieDetails>

    @Query("DELETE FROM favourites WHERE id=:movieTitle")
    suspend fun removeFromFavourites(movieTitle:String)
}