package com.balevanciaga.tvapp.domain.repository

import com.balevanciaga.tvapp.domain.model.TvShow

interface ITvShowRepository {
    suspend fun getPopularShows(): List<TvShow>
    suspend fun getShowsByQuery(query: String): List<TvShow>
}