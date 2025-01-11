package com.irempamukcu.bootcampfinalproject.di

import android.content.Context
import androidx.room.Room
import com.irempamukcu.bootcampfinalproject.data.datasource.FavouritesDataSource
import com.irempamukcu.bootcampfinalproject.data.datasource.MoviesDataSource
import com.irempamukcu.bootcampfinalproject.data.repo.FavouritesRepository
import com.irempamukcu.bootcampfinalproject.data.repo.MoviesRepository
import com.irempamukcu.bootcampfinalproject.retrofit.ApiUtilis
import com.irempamukcu.bootcampfinalproject.retrofit.MoviesDao
import com.irempamukcu.bootcampfinalproject.room.Database
import com.irempamukcu.bootcampfinalproject.room.FavouriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// This module provides dependency injection for various components in the application.
// It uses Dagger Hilt to manage dependencies and their lifecycles.
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    // Provides a singleton instance of MoviesRepository.
    @Provides
    @Singleton
    fun provideMoviesRepository(moviesDataSource: MoviesDataSource): MoviesRepository{
        return MoviesRepository(moviesDataSource)
    }

    // Provides a singleton instance of MoviesDataSource, which interacts with MoviesDao.
    @Provides
    @Singleton
    fun provideMoviesDataSource(moviesDao: MoviesDao): MoviesDataSource{
        return MoviesDataSource(moviesDao)
    }

    // Provides a singleton instance of MoviesDao, which is used to fetch movies data via Retrofit.
    @Provides
    @Singleton
    fun provideMoviesDao() : MoviesDao{
        return ApiUtilis.viewMoviesDao()
    }


    // Provides a singleton instance of FavouritesRepository.
    @Provides
    @Singleton
    fun provideFavouritesRepository(favouritesDataSource: FavouritesDataSource) : FavouritesRepository{
        return FavouritesRepository(favouritesDataSource)
    }

    // Provides a singleton instance of FavouritesDataSource, which interacts with FavouriteDao.
    @Provides
    @Singleton
    fun provideFavouritesDataSource(favouriteDao: FavouriteDao) : FavouritesDataSource{
        return FavouritesDataSource(favouriteDao)
    }

    // Provides a singleton instance of FavouriteDao.
    @Provides
    @Singleton
    fun provideFavouritesDao(@ApplicationContext context: Context) : FavouriteDao{
        val database = Room.databaseBuilder(context,Database::class.java,"favouritedatabase.sqlite")
            .createFromAsset("favouritedatabase.sqlite")
            .build()

        return database.getFavouriteDao()
    }

}