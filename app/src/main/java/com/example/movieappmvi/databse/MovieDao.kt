package com.example.movieappmvi.databse

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieappmvi.model.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movieEntity: MovieEntity)

    @Delete
    suspend fun deleteMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM Movies ORDER BY id DESC")
    fun getAllMovies(): Flow<List<MovieEntity>>
}