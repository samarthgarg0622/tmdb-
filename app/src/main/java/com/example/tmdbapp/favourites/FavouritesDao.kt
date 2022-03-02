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

    @Query("SELECT * FROM favourites WHERE id=:movieId")
    suspend fun getFavourites(movieId:Int) :List<MovieDetails>

    @Query("DELETE FROM favourites WHERE id=:movieId")
    suspend fun removeFromFavourites(movieId:Int)
}