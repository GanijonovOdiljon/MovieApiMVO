package com.example.movieappmvi.util

import com.example.movieappmvi.model.MovieEntity
import com.example.movieappmvi.model.ResultMovie

fun ResultMovie.toMovieEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        lan = original_language,
        title = original_title,
        backImage = backdrop_path,
        posterImage = poster_path,
        overView = overView,
        popularity = popularity,
        voteAverage = vote_average,
        voteCount = vote_Count
    )
}

fun MovieEntity.toResultMovie(): ResultMovie {
    return ResultMovie(
        id = id,
        original_language = lan,
        original_title = title,
        backdrop_path = backImage,
        poster_path = posterImage,
        popularity = popularity,
        overView = overView,
        vote_average = voteAverage,
        vote_Count = voteCount

    )
}