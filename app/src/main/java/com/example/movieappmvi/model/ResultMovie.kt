package com.example.movieappmvi.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultMovie(
    val backdrop_path: String,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overView: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_Count: Int
) : Parcelable