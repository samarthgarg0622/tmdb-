package com.example.tmdbapp.di

import android.content.Context
import androidx.room.Room
import com.example.tmdbapp.favourites.FavouritesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideFavouritesDatabase(@ApplicationContext context: Context): FavouritesDatabase =
        Room.databaseBuilder(
            context,
            FavouritesDatabase::class.java,
            "favourites"
        )
            .build()

    @Singleton
    @Provides
    fun provideFavouritesDao(favouritesDatabase: FavouritesDatabase) =
        favouritesDatabase.favouritesDao()
}
