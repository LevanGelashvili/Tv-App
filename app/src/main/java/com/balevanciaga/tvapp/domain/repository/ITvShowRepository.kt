package com.balevanciaga.tvapp.domain.repository

import com.balevanciaga.tvapp.domain.model.TvShowBrief
import com.balevanciaga.tvapp.domain.model.TvShowDetails

interface ITvShowRepository {
    suspend fun getPopularShows(page: Int): List<TvShowBrief>
    suspend fun getShowDetails(id: Int): TvShowDetails
    suspend fun getSimilarShows(id: Int): List<TvShowBrief>
}