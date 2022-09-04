package com.balevanciaga.tvapp.domain.repository

import com.balevanciaga.tvapp.domain.model.TvShowBrief

interface ITvShowRepository {
    suspend fun getPopularShows(page: Int): List<TvShowBrief>
}