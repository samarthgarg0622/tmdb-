package com.example.tmdbapp.favourites

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tmdbapp.models.MovieDetails

@Database(entities = [MovieDetails::class], version = 1)
abstract class FavouritesDatabase : RoomDatabase() {

    abstract fun favouritesDao() : FavouritesDao

    companion object{
        @Volatile
        private var INSTANCE:FavouritesDatabase? = null
        fun getFavouritesDatabase(context: Context):FavouritesDatabase{
            if(INSTANCE==null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context,
                        FavouritesDatabase::class.java,
                        "favourites")
                        .build()
                }
            }
            return INSTANCE!!
        }
    }
}